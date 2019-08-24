package cn.alanhe;


import com.intellij.codeInsight.daemon.OutsidersPsiFileSupport;
import com.intellij.ide.bookmarks.Bookmark;
import com.intellij.ide.bookmarks.BookmarksListener;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.VcsException;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class BookMarkXListener implements BookmarksListener {
    private static Logger logger = Logger.getInstance(BookMarkXListener.class);

    private Project project;

    BookMarkXListener(Project project) {
        this.project = project;
    }

    @Override
    public void bookmarkAdded(@NotNull Bookmark b) {
        if (logger.isDebugEnabled()) {
            logger.debug("bookmark added ", b);
        }
        try {
            this.addBookmark(b);
        } catch (VcsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bookmarkRemoved(@NotNull Bookmark b) {
        if (logger.isDebugEnabled()) {
            logger.debug("bookmark removed ", b);
        }
        try {
            this.addBookmark(b);
        } catch (VcsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bookmarkChanged(@NotNull Bookmark b) {
        if (logger.isDebugEnabled()) {
            logger.debug("bookmark changed ", b);
        }
    }

    private void addBookmark(Bookmark bookmark) throws VcsException {
        VirtualFile virtualFile = bookmark.getFile();
        boolean isOutsiderFile = OutsidersPsiFileSupport.isOutsiderFile(virtualFile);

        String path = isOutsiderFile ? OutsidersPsiFileSupport.getOriginalFilePath(virtualFile) : virtualFile.getPath();
        path = path.replace(Objects.requireNonNull(project.getBasePath()), "");
        BookMarkXPersistentStateComponent service = BookMarkXPersistentStateComponent.getInstance();
        String projectName = project.getName();
        int currentLineNumber = bookmark.getLine();
        String annotateAuthor = GitOperationUtil.getAnnotateAuthor(project, virtualFile, currentLineNumber, isOutsiderFile);
        service.addBookMark(new BookmarkXItemState(projectName, path, currentLineNumber, new Date(), annotateAuthor, ""));

        if (service.getSetting().isAutoCopy()) {
            CopyBookMarkX.copyToClipboard(project);
        }
    }

}

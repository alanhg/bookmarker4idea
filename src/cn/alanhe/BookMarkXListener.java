package cn.alanhe;


import com.intellij.codeInsight.daemon.OutsidersPsiFileSupport;
import com.intellij.ide.bookmarks.Bookmark;
import com.intellij.ide.bookmarks.BookmarksListener;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.VcsException;
import com.intellij.openapi.vcs.annotate.AnnotationProvider;
import com.intellij.openapi.vcs.annotate.LineAnnotationAspect;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.vcsUtil.VcsUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class BookMarkXListener implements BookmarksListener {
    private static Logger logger = Logger.getInstance(BookMarkXListener.class);

    private Project project;

    public BookMarkXListener(Project project) {
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

    private void addBookmark(Bookmark b) throws VcsException {
        VirtualFile virtualFile = b.getFile();
        boolean outsiderFile = OutsidersPsiFileSupport.isOutsiderFile(virtualFile);
        String path = outsiderFile ? OutsidersPsiFileSupport.getOriginalFilePath(virtualFile) : virtualFile.getPath();
        path = path.replace(Objects.requireNonNull(project.getBasePath()), "");
        BookMarkXPersistentStateComponent service = BookMarkXPersistentStateComponent.getInstance();
        String projectName = project.getName();
        int currentLineNumber = b.getLine();
        String annotateAuthor = getAnnotateAuthor(virtualFile, currentLineNumber);
        service.addBookMark(new BookmarkXItemState(projectName, path, currentLineNumber, new Date(), annotateAuthor));
    }

    private String getAnnotateAuthor(VirtualFile virtualFile, int currentLineNumber) throws VcsException {
        AbstractVcs abstractVcs = VcsUtil.getVcsFor(project, virtualFile);
        AnnotationProvider annotationProvider = Objects.requireNonNull(abstractVcs).getAnnotationProvider();
        assert annotationProvider != null;
        LineAnnotationAspect aspect = annotationProvider.annotate(virtualFile).getAspects()[2];
        return aspect.getValue(currentLineNumber);
    }
}

package cn.alanhe;


import com.intellij.ide.bookmarks.Bookmark;
import com.intellij.ide.bookmarks.BookmarksListener;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

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
        this.addBookmark(b);
    }

    @Override
    public void bookmarkRemoved(@NotNull Bookmark b) {
        if (logger.isDebugEnabled()) {
            logger.debug("bookmark removed ", b);
        }
        this.addBookmark(b);
    }

    @Override
    public void bookmarkChanged(@NotNull Bookmark b) {
        if (logger.isDebugEnabled()) {
            logger.debug("bookmark changed ", b);
        }
    }

    private void addBookmark(Bookmark b) {
        VirtualFile virtualFile = b.getFile();
        String path = virtualFile.getPath();
        path = path.replace(project.getBasePath(), "");
        BookMarkXPersistentStateComponent service = BookMarkXPersistentStateComponent.getInstance();
        String projectName = project.getName();
        int currentLineNumber = b.getLine();
        service.addBookMark(new BookmarkXItemState(projectName, path, currentLineNumber, new Date()));
    }
}

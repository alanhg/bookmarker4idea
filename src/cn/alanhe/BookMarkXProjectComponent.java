package cn.alanhe;

import com.intellij.ide.bookmarks.BookmarksListener;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;

public class BookMarkXProjectComponent implements ProjectComponent {
    private static final Logger logger = Logger.getInstance(BookMarkXProjectComponent.class);

    private static BookmarksListener BOOKMARKS_LISTENER;

    private Project project;

    public BookMarkXProjectComponent(Project project) {
        this.project = project;
        BOOKMARKS_LISTENER = new BookMarkXListener(project);
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    public void projectOpened() {
        logger.debug("Registering listeners");
        project.getMessageBus().connect().subscribe(BookmarksListener.TOPIC, BOOKMARKS_LISTENER);
    }

    @Override
    public void projectClosed() {
    }
}

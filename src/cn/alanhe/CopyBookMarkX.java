package cn.alanhe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.TextTransferable;
import org.apache.commons.lang.time.DateUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CopyBookMarkX extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        CopyBookMarkX.copyToClipboard(project);
    }

    public static void copyToClipboard(Project project) {
        BookMarkXPersistentStateComponent service = BookMarkXPersistentStateComponent.getInstance();
        StringBuilder text = new StringBuilder();
        List<BookmarkXItemState> bookMarks = service.getBookMarks();
        bookMarks = bookMarks.stream()
                .filter(getBookmarkXItemStateViewScopePredicate(project, service))
                .filter(getBookmarkXItemStateFilterTodayPredicate(service))
                .collect(Collectors.toList());
        for (BookmarkXItemState state : bookMarks) {
            text.append(String.format("【%s】%s,L%d @%s<br>", state.getProjectName(), state.getFilePath(), state.getLineNumber() + 1, state.getAnnotateAuthor()));
        }
        CopyPasteManager.getInstance().setContents(new TextTransferable(text.toString()));
    }

    @NotNull
    private static Predicate<BookmarkXItemState> getBookmarkXItemStateViewScopePredicate(Project project, BookMarkXPersistentStateComponent service) {
        if (ViewScopeEnum.GLOABL.equals(service.getViewScope())) {
            return bookmarkXItemState -> true;
        }
        return bookmarkXItemState -> bookmarkXItemState.getProjectName().equals(Objects.requireNonNull(project).getName());
    }

    @NotNull
    private static Predicate<BookmarkXItemState> getBookmarkXItemStateFilterTodayPredicate(BookMarkXPersistentStateComponent service) {
        if (!service.isOnlyCopyToday()) {
            return bookmarkXItemState -> true;
        }
        return bookmarkXItemState -> DateUtils.isSameDay(new Date(), bookmarkXItemState.getCreatedDate());
    }
}

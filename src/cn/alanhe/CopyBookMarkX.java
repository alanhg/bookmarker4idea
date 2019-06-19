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

    static void copyToClipboard(Project project) {
        BookMarkXPersistentStateComponent service = BookMarkXPersistentStateComponent.getInstance();
        StringBuilder text = new StringBuilder();
        List<BookmarkXItemState> bookMarks = service.getBookMarks();
        final LineSepEnum lineSep = service.getSetting().getLineSep();
        bookMarks = bookMarks.stream()
                .filter(getBookmarkXItemStateViewScopePredicate(project, service.getSetting().getViewScope()))
                .filter(getBookmarkXItemStateFilterTodayPredicate(service.getSetting().isOnlyCopyToday()))
                .collect(Collectors.toList());
        for (BookmarkXItemState state : bookMarks) {
            if (LineSepEnum.PLAIN_TEXT.equals(lineSep)) {
                text.append(String.format("【%s】%s,L%d @%s%n", state.getProjectName(), state.getFilePath(), state.getLineNumber() + 1, state.getAnnotateAuthor()));
                continue;
            }
            text.append(String.format("【%s】%s,L%d @%s%s", state.getProjectName(), state.getFilePath(), state.getLineNumber() + 1, state.getAnnotateAuthor(), lineSep.getSeq()));
        }
        CopyPasteManager.getInstance().setContents(new TextTransferable(text.toString()));
    }

    @NotNull
    private static Predicate<BookmarkXItemState> getBookmarkXItemStateViewScopePredicate(Project project, ViewScopeEnum scopeEnum) {
        if (ViewScopeEnum.GLOABL.equals(scopeEnum)) {
            return bookmarkXItemState -> true;
        }
        return bookmarkXItemState -> bookmarkXItemState.getProjectName().equals(Objects.requireNonNull(project).getName());
    }

    @NotNull
    private static Predicate<BookmarkXItemState> getBookmarkXItemStateFilterTodayPredicate(boolean isOnlyCopyToday) {
        if (!isOnlyCopyToday) {
            return bookmarkXItemState -> true;
        }
        return bookmarkXItemState -> DateUtils.isSameDay(new Date(), bookmarkXItemState.getCreatedDate());
    }
}

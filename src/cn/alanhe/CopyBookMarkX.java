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

        if (bookMarks.isEmpty()) {
            return;
        }

        text.append(getProjectNamesSummary(bookMarks, lineSep));
        text.append(getBookMarksItemsText(bookMarks, lineSep));

        CopyPasteManager.getInstance().setContents(new TextTransferable(text.toString()));

        new BookmarkXNotifier().notify(project, "Copied BookMarks to Clipboard");
    }

    public static String getBookMarksItemsText(List<BookmarkXItemState> bookMarks, LineSepEnum lineSep) {
        StringBuilder text = new StringBuilder();
        for (BookmarkXItemState state : bookMarks) {
            text.append(String.format("【%s】%s:%d @%s%s", state.getProjectName(), state.getFilePath(), state.getLineNumber() + 1, state.getAnnotateAuthor(), lineSep.getSeq()));
        }
        return text.toString();
    }

    @NotNull
    private static Predicate<BookmarkXItemState> getBookmarkXItemStateViewScopePredicate(Project project, ViewScopeEnum scopeEnum) {
        if (ViewScopeEnum.GLOBAL.equals(scopeEnum)) {
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

    public static String getProjectNamesSummary(List<BookmarkXItemState> bookMarks, LineSepEnum lineSep) {
        String collect = "Projects: " + bookMarks.stream().map(BookmarkXItemState::getProjectName).distinct().collect(Collectors.joining(" / "));
        return collect + lineSep.getSeq() + lineSep.getSeq();
    }
}

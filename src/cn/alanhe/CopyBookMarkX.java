package cn.alanhe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.ui.TextTransferable;

import java.util.List;
import java.util.Objects;
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
        if (ViewScopeEnum.PROJECT.equals(service.getViewScope())) {
            bookMarks = bookMarks.stream()
                    .filter(bookmarkXItemState -> bookmarkXItemState.getProjectName().equals(Objects.requireNonNull(project).getName()))
                    .collect(Collectors.toList());
        }
        for (BookmarkXItemState state : bookMarks) {
            text.append(String.format("【%s】%s,L%d @%s<br>", state.getProjectName(), state.getFilePath(), state.getLineNumber() + 1, state.getAnnotateAuthor()));
        }
        CopyPasteManager.getInstance().setContents(new TextTransferable(text.toString()));
    }
}

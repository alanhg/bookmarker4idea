package cn.alanhe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.util.ui.TextTransferable;

import java.util.List;
import java.util.stream.Collectors;

public class CopyBookMarkX extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        BookMarkXPersistentStateComponent service = BookMarkXPersistentStateComponent.getInstance();

        StringBuilder text = new StringBuilder();
        List<BookmarkXItemState> bookMarks = service.getBookMarks();
        if (ViewScopeEnum.PROJECT.equals(service.getViewScope())) {
            bookMarks = bookMarks.stream()
                    .filter(bookmarkXItemState -> bookmarkXItemState.getProjectName().equals(e.getProject().getName()))
                    .collect(Collectors.toList());
        }
        for (BookmarkXItemState state : bookMarks) {
            text.append(String.format("【%s】%s,L%d<br>", state.getProjectName(), state.getFilePath(), state.getLineNumebr() + 1));
        }
        CopyPasteManager.getInstance().setContents(new TextTransferable(text.toString()));
    }
}

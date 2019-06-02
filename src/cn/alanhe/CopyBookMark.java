package cn.alanhe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.util.ui.TextTransferable;

public class CopyBookMark extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        BookMarkPersistentStateComponent service = BookMarkPersistentStateComponent.getInstance();

        StringBuilder text = new StringBuilder();
        for (BookmarkItemState state : service.getBookMarks()) {
            text.append(String.format("【%s】%s,L%d\n", state.getProjectName(), state.getFilePath(), state.getLineNumebr() + 1));
        }
        CopyPasteManager.getInstance().setContents(new TextTransferable(text.toString()));
    }
}

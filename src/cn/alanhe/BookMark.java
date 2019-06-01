package cn.alanhe;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;

public class BookMark extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Document document = editor.getDocument();

        FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
        VirtualFile currentFile = fileDocumentManager.getFile(document);

        if (e.getProject() == null || currentFile == null) {
            return;
        }
        BookMarkService service = ServiceManager.getService(BookMarkService.class);

        String projectName = e.getProject().getName();
        int currentLineNumber = editor.getCaretModel().getLogicalPosition().line;
        String currentFilePath = currentFile.getPath();
        service.addBookMark(new BookmarkItemState(projectName, currentFilePath, currentLineNumber));
    }
}

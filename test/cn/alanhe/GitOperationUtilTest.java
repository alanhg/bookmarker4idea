package test.test.cn.alanhe;

import cn.alanhe.GitOperationUtil;
import org.junit.Assert;
import org.junit.Test;

public class GitOperationUtilTest {


    @Test
    public void should_get_file_path_when_get_path_given_user_data_contain_file_path() {
        String userData = "[HARD_REFERENCE_TO_PSI -> com.intellij.psi.SingleRootFileViewProvider{myVirtualFile=LightVirtualFile: /LineSepEnum.java, content=VirtualFileContent{size=337}}, PERSISTENT_RANGE_MARKERS_KEY -> com.intellij.openapi.editor.impl.DocumentImpl$RMTreeReference@4cf336ab, RANGE_MARKERS_KEY -> com.intellij.openapi.editor.impl.DocumentImpl$RMTreeReference@59c3f175, FREE_THREADED -> false, HARD_REF_TO_DOCUMENT_KEY -> DocumentImpl[LightVirtualFile: /LineSepEnum.java], CHARSET -> UTF-8, FILE_TO_STRONG_REF_KEY -> LightVirtualFile: /LineSepEnum.java, SMART_POINTERS 1681138080 -> com.intellij.psi.impl.smartPointers.SmartPointerTracker@3c13b62b, OutsidersPsiFileSupport.FilePath -> /Users/qhe/Documents/GitHub/ttt/bookmarkex4idea/src/cn/alanhe/LineSepEnum.java, OutsidersPsiFileSupport -> true, editorconfig.cached.pairs -> com.intellij.util.CachedValueImpl@49ebe804]";

        String filePath = GitOperationUtil.getFilePath(userData);

        Assert.assertEquals("/Users/qhe/Documents/GitHub/ttt/bookmarkex4idea/src/cn/alanhe/LineSepEnum.java", filePath);
    }

    @Test
    public void should_get_empty_file_path_when_get_path_given_user_data_not_contain_file_path() {
        String userData = "[HARD_REFERENCE_TO_PSI -> com.intellij.psi.SingleRootFileViewProvider{myVirtualFile=LightVirtualFile: /LineSepEnum.java, content=VirtualFileContent{size=337}}, PERSISTENT_RANGE_MARKERS_KEY -> com.intellij.openapi.editor.impl.DocumentImpl$RMTreeReference@4cf336ab, RANGE_MARKERS_KEY -> com.intellij.openapi.editor.impl.DocumentImpl$RMTreeReference@59c3f175, FREE_THREADED -> false, HARD_REF_TO_DOCUMENT_KEY -> DocumentImpl[LightVirtualFile: /LineSepEnum.java], CHARSET -> UTF-8, FILE_TO_STRONG_REF_KEY -> LightVirtualFile: /LineSepEnum.java, SMART_POINTERS 1681138080 -> com.intellij.psi.impl.smartPointers.SmartPointerTracker@3c13b62b, editorconfig.cached.pairs -> com.intellij.util.CachedValueImpl@49ebe804]";

        String filePath = GitOperationUtil.getFilePath(userData);

        Assert.assertEquals("", filePath);
    }

}

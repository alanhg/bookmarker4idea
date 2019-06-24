package cn.alanhe;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.VcsException;
import com.intellij.openapi.vcs.annotate.AnnotationProvider;
import com.intellij.openapi.vcs.annotate.LineAnnotationAspect;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.vcsUtil.VcsUtil;

import java.util.Objects;

class GitOperationUtil {

    static String getAnnotateAuthor(Project project, VirtualFile file, int currentLineNumber, boolean outsiderFile) throws VcsException {
        if (!outsiderFile) {
            return GitOperationUtil.getAnnotateAuthorByProvider(project, file, currentLineNumber);
        }
        return "";
    }

    private static String getAnnotateAuthorByProvider(Project project, VirtualFile virtualFile, int currentLineNumber) throws VcsException {
        AbstractVcs abstractVcs = VcsUtil.getVcsFor(project, virtualFile);
        AnnotationProvider annotationProvider = Objects.requireNonNull(abstractVcs).getAnnotationProvider();
        assert annotationProvider != null;
        LineAnnotationAspect aspect = annotationProvider.annotate(virtualFile).getAspects()[2];
        return aspect.getValue(currentLineNumber);
    }
}

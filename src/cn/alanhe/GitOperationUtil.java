package cn.alanhe;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.VcsException;
import com.intellij.openapi.vcs.annotate.AnnotationProvider;
import com.intellij.openapi.vcs.annotate.LineAnnotationAspect;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.vcsUtil.VcsUtil;
import git4idea.commands.GitCommand;
import git4idea.commands.GitHandler;
import git4idea.commands.GitLineHandler;

import java.util.Objects;

class GitOperationUtil {

    static String getAnnotateAuthor(Project project, VirtualFile file, int currentLineNumber, boolean outsiderFile) throws VcsException {
        if (outsiderFile) {
            return null;
        }
        return GitOperationUtil.getAnnotateAuthorByProvider(project, file, currentLineNumber);
        //        return getLatestAnnotateAuthor(project, file, currentLineNumber);
    }

    private static String getAnnotateAuthorByProvider(Project project, VirtualFile virtualFile, int currentLineNumber) throws VcsException {
        AbstractVcs abstractVcs = VcsUtil.getVcsFor(project, virtualFile);
        AnnotationProvider annotationProvider = Objects.requireNonNull(abstractVcs).getAnnotationProvider();
        assert annotationProvider != null;
        LineAnnotationAspect aspect = annotationProvider.annotate(virtualFile).getAspects()[2];
        return aspect.getValue(currentLineNumber);
    }

    private static String getLatestAnnotateAuthor(Project project, VirtualFile virtualFile, int currentLineNumber) {
        GitCommand command = GitCommand.BLAME;
        GitHandler gitHandler = new GitLineHandler(project, virtualFile, command);

        return gitHandler.printableCommandLine();
    }

}

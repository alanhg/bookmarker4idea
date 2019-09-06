package cn.alanhe;

import java.util.Date;

public class BookmarkXItemState {
    private String projectName;

    private String filePath;

    private int lineNumber;

    private Date createdDate;

    private String annotateAuthor;

    private String comment;

    public String getProjectName() {
        return projectName;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getAnnotateAuthor() {
        return annotateAuthor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BookmarkXItemState() {
    }

    public BookmarkXItemState(String projectName, String filePath, int lineNumber, Date createdDate, String annotateAuthor, String comment) {
        this.projectName = projectName;
        this.filePath = filePath;
        this.lineNumber = lineNumber;
        this.createdDate = createdDate;
        this.annotateAuthor = annotateAuthor;
        this.comment = comment;
    }
}

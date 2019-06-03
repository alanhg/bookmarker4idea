package cn.alanhe;

import java.util.Date;

public class BookmarkXItemState {
    private String projectName;

    private String filePath;

    private int lineNumebr;

    private Date createdDate;

    public BookmarkXItemState(String projectName, String filePath, int lineNumebr, Date createdDate) {
        this.projectName = projectName;
        this.filePath = filePath;
        this.lineNumebr = lineNumebr;
        this.createdDate = createdDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getLineNumebr() {
        return lineNumebr;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}

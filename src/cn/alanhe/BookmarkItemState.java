package cn.alanhe;

public class BookmarkItemState {
    private String projectName;

    private String filePath;

    private int lineNumebr;

    public BookmarkItemState(String projectName, String filePath, int lineNumebr) {
        this.projectName = projectName;
        this.filePath = filePath;
        this.lineNumebr = lineNumebr;
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
}

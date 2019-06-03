package cn.alanhe;

public class BookmarkXItemState {
    private String projectName;

    private String filePath;

    private int lineNumebr;

    public BookmarkXItemState(String projectName, String filePath, int lineNumber) {
        this.projectName = projectName;
        this.filePath = filePath;
        this.lineNumebr = lineNumber;
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

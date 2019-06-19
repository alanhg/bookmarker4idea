package cn.alanhe;

public class BookMarkXSetting {

    private ViewScopeEnum viewScope = ViewScopeEnum.GLOBAL;

    private LineSepEnum lineSep = LineSepEnum.PLAIN_TEXT;

    private boolean autoCopy = false;

    private boolean onlyCopyToday = false;

    public ViewScopeEnum getViewScope() {
        return this.viewScope;
    }

    public void setViewScope(ViewScopeEnum viewScope) {
        this.viewScope = viewScope;
    }

    public void setAutoCopy(boolean autoCopy) {
        this.autoCopy = autoCopy;
    }

    public boolean isAutoCopy() {
        return autoCopy;
    }

    public boolean isOnlyCopyToday() {
        return onlyCopyToday;
    }

    public void setOnlyCopyToday(boolean onlyCopyToday) {
        this.onlyCopyToday = onlyCopyToday;
    }

    public LineSepEnum getLineSep() {
        return lineSep;
    }

    public void setLineSep(LineSepEnum lineSep) {
        this.lineSep = lineSep;
    }

    public BookMarkXSetting() {
    }
}

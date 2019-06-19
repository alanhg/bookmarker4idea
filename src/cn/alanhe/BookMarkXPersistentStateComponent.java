package cn.alanhe;


import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@State(name = "cn.alanhe.BookMarkXPersistentStateComponent", storages = {@Storage("bookmarker-setting.xml")})
public class BookMarkXPersistentStateComponent implements PersistentStateComponent<BookMarkXPersistentStateComponent> {

    private List<BookmarkXItemState> bookmarkXItemStates = new ArrayList<>();

    private ViewScopeEnum viewScope = ViewScopeEnum.GLOABL;

    private boolean autoCopy = false;

    private boolean onlyCopyToday = false;

    private LineSepEnum lineSep = LineSepEnum.PLAIN_TEXT;

    @Nullable
    @Override
    public BookMarkXPersistentStateComponent getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull BookMarkXPersistentStateComponent state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    @Override
    public void noStateLoaded() {

    }

    void addBookMark(BookmarkXItemState bookmarkXItemState) {
        List<BookmarkXItemState> existBookmarks = this.bookmarkXItemStates.stream()
                .filter(bookmarkXItemState1 ->
                        bookmarkXItemState1.getProjectName().equals(bookmarkXItemState.getProjectName()) &&
                                bookmarkXItemState1.getFilePath().equals(bookmarkXItemState.getFilePath()) &&
                                bookmarkXItemState1.getLineNumber() == (bookmarkXItemState.getLineNumber()))
                .collect(Collectors.toList());
        if (existBookmarks.size() > 0) {
            this.bookmarkXItemStates.remove(existBookmarks.get(0));
            return;
        }
        this.bookmarkXItemStates.add(bookmarkXItemState);
    }

    public List<BookmarkXItemState> getBookMarks() {
        return this.bookmarkXItemStates;
    }

    public ViewScopeEnum getViewScope() {
        return this.viewScope;
    }

    public static BookMarkXPersistentStateComponent getInstance() {
        return ServiceManager.getService(BookMarkXPersistentStateComponent.class);
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
}

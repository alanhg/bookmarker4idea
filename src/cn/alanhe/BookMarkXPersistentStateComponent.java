package cn.alanhe;


import cn.alanhe.settings.BookMarkXSetting;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@State(
    name = "cn.alanhe.BookMarkXPersistentStateComponent",
    storages = {@Storage("bookmarker-setting.xml")}
)
public class BookMarkXPersistentStateComponent implements PersistentStateComponent<BookMarkXState> {

    private BookMarkXState myState = new BookMarkXState();

    public static BookMarkXPersistentStateComponent getInstance() {
        return ServiceManager.getService(BookMarkXPersistentStateComponent.class);
    }

    @Nullable
    @Override
    public BookMarkXState getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull BookMarkXState state) {
        myState = state;
    }

    void addBookMark(BookmarkXItemState bookmarkXItemState) {
        this.myState.addBookMark(bookmarkXItemState);
    }

    public List<BookmarkXItemState> getBookMarks() {
        return this.myState.getBookmarkXItemStates();
    }

    public BookMarkXSetting getSetting() {
        return this.myState.getSetting();
    }
}

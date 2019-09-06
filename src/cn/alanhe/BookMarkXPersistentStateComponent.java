package cn.alanhe;


import cn.alanhe.settings.BookMarkXSetting;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@State(
    name = "cn.alanhe.BookMarkXPersistentStateComponent",
    storages = {@Storage("bookmarker-setting.xml")}
)
public class BookMarkXPersistentStateComponent implements PersistentStateComponent<BookMarkXPersistentStateComponent> {

    public BookMarkXState myState = new BookMarkXState();

    public static BookMarkXPersistentStateComponent getInstance() {
        return ServiceManager.getService(BookMarkXPersistentStateComponent.class);
    }

    @Nullable
    @Override
    public BookMarkXPersistentStateComponent getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull BookMarkXPersistentStateComponent state) {
        XmlSerializerUtil.copyBean(state, this);
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

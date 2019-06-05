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
                                bookmarkXItemState1.getLineNumebr() == (bookmarkXItemState.getLineNumebr()))
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

    public static BookMarkXPersistentStateComponent getInstance() {
        return ServiceManager.getService(BookMarkXPersistentStateComponent.class);
    }
}

package cn.alanhe;


import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@State(name = "cn.alanhe.BookMarkPersistentStateComponent", storages = {@Storage("bookmarker-setting.xml")})
public class BookMarkPersistentStateComponent implements PersistentStateComponent<BookMarkPersistentStateComponent> {

    private List<BookmarkItemState> bookmarkItemStates = new ArrayList<>();

    @Nullable
    @Override
    public BookMarkPersistentStateComponent getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull BookMarkPersistentStateComponent bookMarkServive) {
        this.bookmarkItemStates = bookMarkServive.bookmarkItemStates;
    }

    @Override
    public void noStateLoaded() {

    }


    void addBookMark(BookmarkItemState bookmarkItemState) {
        List<BookmarkItemState> existBookmarks = this.bookmarkItemStates.stream()
                .filter(bookmarkItemState1 -> bookmarkItemState1.equals(bookmarkItemState)).collect(Collectors.toList());
        if (existBookmarks.size() > 0) {
            this.bookmarkItemStates.remove(existBookmarks.get(0));
            return;
        }
        this.bookmarkItemStates.add(bookmarkItemState);
    }

    public List<BookmarkItemState> getBookMarks() {
        return this.bookmarkItemStates;
    }

    public static BookMarkPersistentStateComponent getInstance() {
        return ServiceManager.getService(BookMarkPersistentStateComponent.class);
    }
}

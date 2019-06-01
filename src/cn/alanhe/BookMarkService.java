package cn.alanhe;


import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@State(
        name = "BookMarkSetting",
        storages = {@Storage(
                value = "$APP_CONFIG$/bookmark.xml"
        )}
)
public class BookMarkService implements PersistentStateComponent<BookMarkService> {

    private List<BookmarkItemState> bookmarkItemStates;

    public BookMarkService() {
        this.bookmarkItemStates = new ArrayList<>();
    }

    @Nullable
    @Override
    public BookMarkService getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull BookMarkService bookMarkServive) {
        XmlSerializerUtil.copyBean(bookMarkServive, this);
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

}

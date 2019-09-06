package cn.alanhe;

import cn.alanhe.settings.BookMarkXSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookMarkXState {
    private List<BookmarkXItemState> bookmarkXItemStates = new ArrayList<>();

    private BookMarkXSetting setting = new BookMarkXSetting();

    public List<BookmarkXItemState> getBookmarkXItemStates() {
        return bookmarkXItemStates;
    }

    public void setBookmarkXItemStates(List<BookmarkXItemState> bookmarkXItemStates) {
        this.bookmarkXItemStates = bookmarkXItemStates;
    }

    public BookMarkXSetting getSetting() {
        return setting;
    }

    public void setSetting(BookMarkXSetting setting) {
        this.setting = setting;
    }

    public void addBookMark(BookmarkXItemState bookmarkXItemState) {
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

}

package test.test.cn.alanhe;

import cn.alanhe.BookmarkXItemState;
import cn.alanhe.CopyBookMarkX;
import cn.alanhe.LineSepEnum;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CopyBookMarkXTest {

    @Test
    public void should_return_bookmarks_item_text_when_linesep_is_html() {
        List<BookmarkXItemState> bookmarks = Arrays.asList(
                new BookmarkXItemState("bookmarkex4idea", "src/cn/alanhe/BookmarkXItemState.java", 11, new Date(), "alan")
        );
        LineSepEnum lineSep = LineSepEnum.HTML;

        String bookMarksItemsText = CopyBookMarkX.getBookMarksItemsText(bookmarks, lineSep);
        String expected = "【bookmarkex4idea】src/cn/alanhe/BookmarkXItemState.java:12 @alan<br>";

        Assert.assertEquals(expected, bookMarksItemsText);
    }
}

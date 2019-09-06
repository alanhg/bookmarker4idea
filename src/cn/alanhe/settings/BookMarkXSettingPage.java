package cn.alanhe.settings;

import cn.alanhe.BookMarkXPersistentStateComponent;
import cn.alanhe.LineSepEnum;
import cn.alanhe.Version;
import cn.alanhe.ViewScopeEnum;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class BookMarkXSettingPage implements Configurable {

    private JPanel panel;

    private JRadioButton projectScopeBtn;
    private JRadioButton allScopeBtn;

    private JCheckBox autoCopyCheckBox;
    private JCheckBox onlyCopyTodayCheckBox;

    private JRadioButton lineSepHtmlRadioBtn;
    private JRadioButton lineSepPlainTextRadioBtn;

    public BookMarkXSettingPage() {
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return Version.PLUGIN_NAME;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return panel;
    }

    @Override
    public boolean isModified() {
        return isModified(BookMarkXPersistentStateComponent.getInstance().getSetting());
    }

    @Override
    public void apply() {
        BookMarkXSetting setting = BookMarkXPersistentStateComponent.getInstance().getSetting();
        setting.setViewScope(this.projectScopeBtn.isSelected() ? ViewScopeEnum.PROJECT : ViewScopeEnum.GLOBAL);
        setting.setLineSep(this.lineSepHtmlRadioBtn.isSelected() ? LineSepEnum.HTML : LineSepEnum.PLAIN_TEXT);
        setting.setAutoCopy(this.autoCopyCheckBox.isSelected());
        setting.setOnlyCopyToday(this.onlyCopyTodayCheckBox.isSelected());
    }

    @Override
    public void reset() {
        BookMarkXSetting setting = BookMarkXPersistentStateComponent.getInstance().getSetting();
        if (ViewScopeEnum.GLOBAL.equals(setting.getViewScope())) {
            this.allScopeBtn.setSelected(true);
        } else {
            this.projectScopeBtn.setSelected(true);
        }
        if (LineSepEnum.HTML.equals(setting.getLineSep())) {
            this.lineSepHtmlRadioBtn.setSelected(true);
        } else {
            this.lineSepPlainTextRadioBtn.setSelected(true);
        }
        this.autoCopyCheckBox.setSelected(setting.isAutoCopy());
        this.onlyCopyTodayCheckBox.setSelected(setting.isOnlyCopyToday());
    }

    public boolean isModified(BookMarkXSetting setting) {
        if (allScopeBtn.isSelected() && !ViewScopeEnum.GLOBAL.equals(setting.getViewScope())) {
            return true;
        }
        if (projectScopeBtn.isSelected() && !ViewScopeEnum.PROJECT.equals(setting.getViewScope())) {
            return true;
        }

        if (autoCopyCheckBox.isSelected() != setting.isAutoCopy()) {
            return true;
        }

        if (onlyCopyTodayCheckBox.isSelected() != setting.isOnlyCopyToday()) {
            return true;
        }

        if (lineSepHtmlRadioBtn.isSelected() && !LineSepEnum.HTML.equals(setting.getLineSep())) {
            return true;
        }

        if (lineSepPlainTextRadioBtn.isSelected() && !LineSepEnum.PLAIN_TEXT.equals(setting.getLineSep())) {
            return true;
        }

        return false;
    }

}

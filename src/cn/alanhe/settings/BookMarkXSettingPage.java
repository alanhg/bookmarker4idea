package cn.alanhe.settings;

import cn.alanhe.BookMarkXPersistentStateComponent;
import cn.alanhe.LineSepEnum;
import cn.alanhe.ViewScopeEnum;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
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

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "BookMarkX";
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
    public void apply() throws ConfigurationException {
        BookMarkXPersistentStateComponent instance = BookMarkXPersistentStateComponent.getInstance();
        getData(instance.getSetting());
        instance.applyState();
    }

    @Override
    public void reset() {
        setData(BookMarkXPersistentStateComponent.getInstance().getSetting());
    }

    public void getData(BookMarkXSetting data) {
        if (projectScopeBtn.isSelected()) {
            data.setViewScope(ViewScopeEnum.PROJECT);
        } else {
            data.setViewScope(ViewScopeEnum.GLOBAL);
        }

        data.setAutoCopy(autoCopyCheckBox.isSelected());
        data.setOnlyCopyToday(onlyCopyTodayCheckBox.isSelected());

        if (lineSepHtmlRadioBtn.isSelected()) {
            data.setLineSep(LineSepEnum.HTML);
        }
        if (lineSepPlainTextRadioBtn.isSelected()) {
            data.setLineSep(LineSepEnum.PLAIN_TEXT);
        }
    }


    private void setData(BookMarkXSetting data) {
        if (ViewScopeEnum.PROJECT.equals(data.getViewScope())) {
            projectScopeBtn.setSelected(true);
        } else {
            allScopeBtn.setSelected(true);
        }

        autoCopyCheckBox.setSelected(data.isAutoCopy());
        onlyCopyTodayCheckBox.setSelected(data.isOnlyCopyToday());

        if (LineSepEnum.HTML.equals(data.getLineSep())) {
            lineSepHtmlRadioBtn.setSelected(true);
        }

        if (LineSepEnum.PLAIN_TEXT.equals(data.getLineSep())) {
            lineSepPlainTextRadioBtn.setSelected(true);
        }
    }

    public boolean isModified(BookMarkXSetting data) {
        if (allScopeBtn.isSelected() && !ViewScopeEnum.GLOBAL.equals(data.getViewScope())) {
            return true;
        }
        if (projectScopeBtn.isSelected() && !ViewScopeEnum.PROJECT.equals(data.getViewScope())) {
            return true;
        }

        if (autoCopyCheckBox.isSelected() != data.isAutoCopy()) {
            return true;
        }

        if (onlyCopyTodayCheckBox.isSelected() != data.isOnlyCopyToday()) {
            return true;
        }

        if (lineSepHtmlRadioBtn.isSelected() && !LineSepEnum.HTML.equals(data.getLineSep())) {
            return true;
        }

        if (lineSepPlainTextRadioBtn.isSelected() && !LineSepEnum.PLAIN_TEXT.equals(data.getLineSep())) {
            return true;
        }

        return false;
    }

}

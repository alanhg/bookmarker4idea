package cn.alanhe;

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
        return isModified(BookMarkXPersistentStateComponent.getInstance());
    }

    @Override
    public void apply() throws ConfigurationException {
        BookMarkXPersistentStateComponent instance = BookMarkXPersistentStateComponent.getInstance();
        getData(instance);
    }

    public void getData(BookMarkXPersistentStateComponent data) {
        if (projectScopeBtn.isSelected()) {
            data.setViewScope(ViewScopeEnum.PROJECT);
        } else {
            data.setViewScope(ViewScopeEnum.GLOABL);
        }
        data.setAutoCopy(autoCopyCheckBox.isSelected());
        data.setOnlyCopyToday(onlyCopyTodayCheckBox.isSelected());
    }


    public boolean isModified(BookMarkXPersistentStateComponent data) {
        if (ViewScopeEnum.PROJECT.equals(data.getViewScope()) && allScopeBtn.isSelected()) {
            return true;
        }
        if (ViewScopeEnum.GLOABL.equals(data.getViewScope()) && projectScopeBtn.isSelected()) {
            return true;
        }
        if (autoCopyCheckBox.isSelected() != data.isAutoCopy()) {
            return true;
        }
        if (onlyCopyTodayCheckBox.isSelected() != data.isOnlyCopyToday()) {
            return true;
        }
        return false;
    }

    @Override
    public void reset() {
        setData(BookMarkXPersistentStateComponent.getInstance());
    }

    private void setData(BookMarkXPersistentStateComponent data) {
        if (ViewScopeEnum.PROJECT.equals(data.getViewScope())) {
            projectScopeBtn.setSelected(true);
        } else {
            allScopeBtn.setSelected(true);
        }
        autoCopyCheckBox.setSelected(data.isAutoCopy());
        onlyCopyTodayCheckBox.setSelected(data.isOnlyCopyToday());
    }

}

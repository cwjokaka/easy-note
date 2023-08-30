package com.okaka.easystock.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * 配置界面的UI
 * @author okaka
 * @date 2023-08-29
 */
public class ConfigUI implements Configurable {
    private JPanel mainPanel;
    private JLabel stockCodeLabel;
    private JTextField stockCodeTextField;
    private JPanel stockCodePanel;

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Config";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return this.mainPanel;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    /**
     * 点击ok或apply按钮时触发此方法
     */
    @Override
    public void apply() throws ConfigurationException {
//        List<String> gidList = DataSetting.getInstance().getGids();
//        gidList.clear();
//        String[] gids = stockCodeTextField.getText().trim().split(",");
//        for (String gid : gids) {
//            gidList.add(gid.trim());
//        }
//        // 刷新数据
//        consoleUI.addRows(gidList);
    }

}

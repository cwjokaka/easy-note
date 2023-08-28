package com.okaka.easystock.factory;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.okaka.easystock.Config;
import com.okaka.easystock.ui.SettingUI;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * 插件的配置页面设置
 */
public class SettingFactory implements SearchableConfigurable {

    private final SettingUI settingUI = new SettingUI();

    @Override
    public @NotNull String getId() {
        return "test.id";
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "test-config";
    }

    @Override
    public @Nullable JComponent createComponent() {
        // 配置页面
        return settingUI.getComponent();
    }

    /**
     * 设置是否已修改
     */
    @Override
    public boolean isModified() {
        return true;
    }

    /**
     * 点击ok或apply时触发此方法
     */
    @Override
    public void apply() throws ConfigurationException {
        String url = settingUI.getUrlTextField().getText();
        // 设置文本信息
        try {
            File file = new File(url);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(0);

            byte[] bytes = new byte[1024 * 1024];
            int readSize = randomAccessFile.read(bytes);

            byte[] copy = new byte[readSize];
            System.arraycopy(bytes, 0, copy, 0, readSize);

            String str = new String(copy, StandardCharsets.UTF_8);

            // 设置内容
            Config.readUI.getTextContent().setText(str);

        } catch (Exception ignore) {
            System.out.println(ignore);
        }
    }
}

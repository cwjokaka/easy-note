package com.okaka.easystock.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import com.okaka.easystock.domain.vo.StockData;
import com.okaka.easystock.infrastructure.DataStorage;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
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

    private MainViewUI mainViewUI;

    public ConfigUI(MainViewUI mainViewUI) {
        this.mainViewUI = mainViewUI;
    }

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
        System.out.println("确认");
        List<String> stockCodes = DataStorage.getInstance().getStockCodes();
        System.out.println("旧数据" + stockCodes.toString());
        stockCodes.clear();
        String[] stockCodesParam = stockCodeTextField.getText().trim().split(",");
        System.out.println(Arrays.toString(stockCodesParam));
        for (String stockCode : stockCodesParam) {
            stockCodes.add(stockCode.trim());
        }
        // 刷新数据
        mainViewUI.updateStocks(stockCodes);
    }

}

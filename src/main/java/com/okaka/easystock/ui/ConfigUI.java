package com.okaka.easystock.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import com.okaka.easystock.domain.vo.StockData;
import com.okaka.easystock.infrastructure.DataStorage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    public ConfigUI() {
        this.mainViewUI = MainViewUI.getInstance();
        initListener();
    }

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
        return true;
    }

    /**
     * 点击ok或apply按钮时触发此方法
     */
    @Override
    public void apply() {
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

    @Override
    public void cancel() {
        System.out.println("cancel!!");
    }

    private void initListener() {
        System.out.println("ConfigUI初始化键盘事件...");
        this.stockCodeTextField.addKeyListener(new KeyTextListener());
    }

    private class KeyTextListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
//            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//                System.out.println("add JTextField");
//                stockCodePanel.add(new JTextField());
//                System.out.println("add JTextField finish!");

//                stockCodePanel.add(new )
//                Container parent = getParent();
//                int numComponents = parent.getComponentCount();
//                Component[] components = new Component[numComponents + 1];
//                for (int i = 0; i < numComponents; i++) {
//                    components[i] = parent.getComponent(i);
//                }
//                components[numComponents] = new TextField(20);
//                parent.removeAll();
//                for (Component component : components) {
//                    parent.add(component);
//                }
//                parent.revalidate();
//                parent.repaint();
//                components[numComponents].requestFocus();
//            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                System.out.println("add JTextField");
                stockCodePanel.add(new JTextField());
                System.out.println("add JTextField finish!");
                stockCodePanel.revalidate();
                stockCodePanel.repaint();

            }
        }
    }

}

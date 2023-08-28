package com.okaka.easynote.ui;

import javax.swing.*;
import java.io.File;

/**
 * @author okaka
 * @date 2023-08-25
 */
public class SettingUI {
    private JTextField urlTextField;
    private JButton urlBtn;
    private JPanel mainPanel;
    private JLabel urlLabel;
    private JPanel settingPanel;

    public SettingUI() {
        // 给按钮添加一个选择文件的事件
        urlBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(settingPanel);
            File file = fileChooser.getSelectedFile();
            urlTextField.setText(file.getPath());
        });
    }

    public JComponent getComponent() {
        return mainPanel;
    }

    public JTextField getUrlTextField() {
        return urlTextField;
    }


}

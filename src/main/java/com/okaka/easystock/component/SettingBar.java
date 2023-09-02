package com.okaka.easystock.component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ReflectionUtil;
import com.okaka.easystock.ui.ConfigUI;
import org.jetbrains.annotations.NotNull;

/**
 * @author okaka
 * @date 2023-09-01
 */
public class SettingBar extends DumbAwareAction {

    private ViewBars panel;

    public SettingBar(ViewBars panel) {
        super("配置股票", "Click to setting", IconLoader.getIcon("/icons/config.svg", ReflectionUtil.getGrandCallerClass().getClassLoader()));
        this.panel = panel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        System.out.println("config actionPerformed!");
        Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        ShowSettingsUtil.getInstance().editConfigurable(project, new ConfigUI());
//        ShowSettingsUtil.getInstance().editConfigurable(panel.getProject(), new ConfigUI(panel.getMainViewUI()));
    }

}

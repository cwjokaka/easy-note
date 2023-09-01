package com.okaka.easystock.component;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.JBSplitter;
import com.okaka.easystock.ui.MainViewUI;

/**
 *
 * @author okaka
 * @date 2023-09-01
 */
public class ViewBars extends SimpleToolWindowPanel {

    private Project project;
    private MainViewUI mainViewUI;

    public ViewBars(Project project) {
        // 横向布局
        super(false, true);
        this.project = project;
        mainViewUI = new MainViewUI();

        // 设置窗体侧边栏按钮
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new SettingBar(this));
        group.add(new RefreshBar(this));

        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("bar", group, false);
        toolbar.setTargetComponent(this);
        setToolbar(toolbar.getComponent());

        // 添加分割线
        JBSplitter splitter = new JBSplitter(false);
        splitter.setSplitterProportionKey("main.splitter.key");
        // 分割线右侧填充上股票指数展示面板
        splitter.setFirstComponent(mainViewUI.getPanel());
        splitter.setProportion(0.3f);
        setContent(splitter);
    }

    public Project getProject() {
        return project;
    }

    public MainViewUI getMainViewUI() {
        return mainViewUI;
    }


}

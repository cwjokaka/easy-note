package com.okaka.easystock.factory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.okaka.easystock.Config;
import com.okaka.easystock.ui.ReadUI;
import org.jetbrains.annotations.NotNull;

/**
 * 为了把自己实现的列表窗体放到侧边栏中，
 * 需要创建一个实现了 ToolWindowFactory 的接口，并把实现类配置到 plugin.xml 中
 */
public class ReadFactory implements ToolWindowFactory {

    private final ReadUI readUI = new ReadUI();

    /**
     * 当用户打开工具窗口时，createToolWindowContent方法将被调用
     */
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 获取内容工厂的实例
        ContentFactory contentFactory = ContentFactory.getInstance();
        // 获取 ToolWindow 显示的内容
        Content content = contentFactory.createContent(readUI.getComponent(), "", false);
        // 设置 ToolWindow 显示的内容
        toolWindow.getContentManager().addContent(content);
        // 全局使用
        Config.readUI = readUI;
    }

}

package com.okaka.easynote.factory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.okaka.easynote.Config;
import com.okaka.easynote.ui.ReadUI;
import org.jetbrains.annotations.NotNull;

/**
 * 为了把我们自己实现的阅读窗体放到整个 IDEA 右侧侧边栏中，
 * 我们需要创建一个实现了 ToolWindowFactory 的接口，并把实现类配置到 plugin.xml 中
 */
public class ReadFactory implements ToolWindowFactory {

    private final ReadUI readUI = new ReadUI();

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

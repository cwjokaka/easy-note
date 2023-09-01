package com.okaka.easystock.component;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import com.okaka.easystock.infrastructure.DataStorage;
import org.jetbrains.annotations.NotNull;

/**
 * @author okaka
 * @date 2023-09-01
 */
public class RefreshBar extends DumbAwareAction {

    private ViewBars panel;

    public RefreshBar(ViewBars panel) {
        super("刷新指数", "Click to refresh", IconLoader.getIcon("/icons/refresh.svg"));
        this.panel = panel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        panel.getMainViewUI().updateStocks(DataStorage.getInstance().getStockCodes());
    }

}

package com.okaka.easystock.infrastructure;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;
import com.intellij.testFramework.ApplicationExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 持久化组件
 * @author okaka
 * @date 2023-08-30
 */
@State(name = "DataStorage", storages = @Storage("plugin.xml"))
public class DataStorage implements PersistentStateComponent<DataState> {

    private DataState state = new DataState();

    public static DataStorage getInstance() {
        return ApplicationManager.getApplication().getService(DataStorage.class);
    }

    @Nullable
    @Override
    public DataState getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull DataState state) {
        this.state = state;
    }

    /**
     * 获取所有本地股票代码
     * @return 股票代码列表
     */
    public List<String> getStockCodes(){
        return state.getStockCodes();
    }

}
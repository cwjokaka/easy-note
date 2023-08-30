package com.okaka.easystock.infrastructure;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 持久化数据
 * @author okaka
 * @date 2023-08-30
 */
@State(name = "DataStorage", storages = @Storage("plugin.xml"))
public class DataStorage implements PersistentStateComponent<DataState> {

    private DataState state = new DataState();

    public static DataStorage getInstance() {
        return ServiceManager.getService(DataStorage.class);
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
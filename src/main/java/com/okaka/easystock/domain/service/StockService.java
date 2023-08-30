package com.okaka.easystock.domain.service;

import com.okaka.easystock.domain.vo.StockData;

import java.util.Collection;
import java.util.List;

public interface StockService {

    /**
     * 根据股票代码获取股票数据
     * @param stockCodes 股票代码列表
     * @return 股票数据列表
     */
    List<StockData> fetchStockDataList(Collection<String> stockCodes);

}

package com.okaka.easystock.domain.service.impl;

import com.okaka.easystock.domain.service.StockService;
import com.okaka.easystock.domain.vo.StockData;
import com.okaka.easystock.esb.stock.StockFetcher;
import com.okaka.easystock.esb.stock.TencentStockFetcher;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author okaka
 * @date 2023-08-30
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockServiceImpl implements StockService {

    final StockFetcher stockFetcher = new TencentStockFetcher();

    @Override
    public List<StockData> fetchStockDataList(Collection<String> stockCodes) {
        log.info("开始查询股票信息...代码:{}", stockCodes);
        System.out.println("开始查询股票信息...代码:" + stockCodes.toString());
        return stockFetcher.fetch(stockCodes);
    }

}

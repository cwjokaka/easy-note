package com.okaka.easystock.domain.service.impl;

import com.okaka.easystock.domain.service.StockService;
import com.okaka.easystock.domain.vo.StockData;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author okaka
 * @date 2023-08-30
 */
@Slf4j
public class StockServiceImpl implements StockService {

    @Override
    public List<StockData> fetchStockDataList(Collection<String> stockCodes) {
        log.info("开始查询股票信息...代码:{}", stockCodes);
        return Collections.emptyList();
    }

}

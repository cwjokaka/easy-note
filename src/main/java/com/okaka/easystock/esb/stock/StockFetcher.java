package com.okaka.easystock.esb.stock;

import com.okaka.easystock.domain.vo.StockData;

import java.util.Collection;
import java.util.List;

public interface StockFetcher {

    List<StockData> fetch(Collection<String> stockCodes);

}

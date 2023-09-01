package com.okaka.easystock.domain.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author okaka
 * @date 2023-08-30
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockData {

    /**
     * 股票代码
     */
    String code;

    /**
     * 股票名称
     */
    String name;

    /**
     * 当前价格
     */
    BigDecimal currentPrice;

    /**
     * 涨跌
     */
    BigDecimal increase;

    /**
     * 涨幅
     */
    BigDecimal increasePer;

    /**
     * 最高价
     */
    BigDecimal maxPrice;

    /**
     * 最低价
     */
    BigDecimal minPrice;

    /**
     * 最后更新时间
     */
    LocalDateTime lastUpdateTime;

}

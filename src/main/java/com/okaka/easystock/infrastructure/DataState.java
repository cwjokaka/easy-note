package com.okaka.easystock.infrastructure;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地数据实体类
 * @author okaka
 * @date 2023-08-30
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataState {

    /**
     * 股票代码列表
     */
    List<String> stockCodes = new ArrayList<>();

}
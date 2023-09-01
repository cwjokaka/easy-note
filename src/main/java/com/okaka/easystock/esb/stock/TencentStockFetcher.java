package com.okaka.easystock.esb.stock;

import cn.hutool.Hutool;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.okaka.easystock.domain.vo.StockData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 腾讯股票抓取
 * @author okaka
 * @date 2023-08-31
 */
@Slf4j
public class TencentStockFetcher implements StockFetcher {

    private static final String URL = "http://qt.gtimg.cn/q=";

    @Override
    public List<StockData> fetch(Collection<String> stockCodes) {
        if (CollectionUtil.isEmpty(stockCodes)) {
            return Collections.emptyList();
        }
        String params = String.join(",", stockCodes);
        try (HttpResponse response = HttpRequest.get(URL + params).execute()) {
            if (response.getStatus() != 200) {
                log.warn("TencentStockFetcher获取股票数据失败, 返回码:{}", response.getStatus());
                return Collections.emptyList();
            }
            String body = response.body();
            return parse(body);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return Collections.emptyList();
    }

    private List<StockData> parse(String body) {
        if (StringUtils.isBlank(body)) {
            return Collections.emptyList();
        }
        /*
        返回示例：
        v_sh600585="1~海螺水泥~600585~26.46~25.68~25.80~124474~86099~38375~26.46~19~26.45~61~26.44~78~26.43~62~26.42~14~26.47~176~26.48~196~26.49~342~26.50~830~26.51~296~~20230901103533~0.78~3.04~26.50~25.80~26.46/124474/326538937~124474~32654~0.31~11.41~~26.50~25.80~2.73~1058.32~1402.20~0.77~28.25~23.11~2.00~-1606~26.23~10.84~8.95~~~1.38~32653.8937~0.0000~0~ ~GP-A~2.16~1.42~5.59~6.76~5.30~30.97~22.60~3.04~-1.08~9.61~3999702579~5299302579~-77.43~-3.15~3999702579~~~-12.15~-0.08~~CNY~0~___D__F__N";
        v_sz002572="51~索菲亚~002572~20.25~19.04~19.50~131498~84575~46922~20.24~20~20.23~9~20.22~11~20.21~16~20.20~28~20.25~149~20.27~18~20.28~20~20.29~16~20.30~75~~20230901103530~1.21~6.36~20.42~19.42~20.25/131498/262015244~131498~26202~2.06~16.04~~20.42~19.42~5.25~129.36~184.75~3.27~20.94~17.14~3.16~-194~19.93~18.49~17.36~~~1.48~26201.5244~0.0000~0~ ~GP-A~15.95~16.78~3.44~20.40~9.00~23.10~12.76~10.05~6.86~21.84~638794338~912370038~-53.59~20.01~638794338~~~18.67~0.05~~CNY~0~";
         */
        System.out.println(body);
        String[] stockStrings = body.split(";");
        List<StockData> result = new ArrayList<>();
        for (String stockString : stockStrings) {
            String stockCode = stockString.substring(stockString.indexOf("_") + 1, stockString.indexOf("="));
            String dataStr = stockString.substring(stockString.indexOf("=") + 2, stockString.length() - 2);
            String[] dataList = dataStr.split("~");
            StockData stockData = new StockData();
            stockData.setCode(stockCode);
            stockData.setName(dataList[1]);
            stockData.setCurrentPrice(new BigDecimal(dataList[3]));
            stockData.setIncrease(new BigDecimal(dataList[31]));
            stockData.setIncreasePer(new BigDecimal(dataList[32]));
            stockData.setLastUpdateTime(LocalDateTime.parse(dataList[30], DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            stockData.setMaxPrice(new BigDecimal(dataList[33]));
            stockData.setMinPrice(new BigDecimal(dataList[34]));
            result.add(stockData);
        }
        return result;
    }

}

package com.okaka.easystock.esb.stock;

import cn.hutool.Hutool;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.okaka.easystock.domain.vo.StockData;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author okaka
 * @date 2023-08-31
 */
@Slf4j
public class TencentStockFetcher implements StockFetcher {

    private final String URL = "http://qt.gtimg.cn/q=";

    @Override
    public List<StockData> fetch(Collection<String> stockCodes) {
        String params = String.join(",", stockCodes);
        try (HttpResponse response = HttpRequest.get(URL + params).execute()) {
            if (response.getStatus() == 200) {
                String body = response.body();

            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    private void parse(String body) {
        /*
        返回示例：
        v_sh600585="1~海螺水泥~600585~25.68~26.03~25.95~156527~78158~78369~25.68~1099~25.67~1287~25.66~858~25.65~188~25.64~1356~25.69~14~25.70~200~25.72~830~25.73~195~25.74~68~~20230831155929~-0.35~-1.34~26.28~25.52~25.68/156527/404489389~156527~40449~0.39~11.07~~26.28~25.52~2.92~1027.12~1360.86~0.75~28.63~23.43~0.69~3481~25.84~10.52~8.69~~~1.36~40448.9389~0.0000~0~ ~GP-A~-0.85~-0.43~5.76~6.76~5.30~30.97~22.60~0.16~-4.89~7.22~3999702579~5299302579~57.11~-7.06~3999702579~~~-15.64~-0.08~~CNY~0~___D__F__N";
         */
        String[] lines = result.split("\n");
        for (String line : lines) {
            String code = line.substring(line.indexOf("_") + 1, line.indexOf("="));
            String dataStr = line.substring(line.indexOf("=") + 2, line.length() - 2);
            String[] values = dataStr.split("~");
            StockBean bean = new StockBean(code, codeMap);
            bean.setName(values[1]);
            bean.setNow(values[3]);
            bean.setChange(values[31]);
            bean.setChangePercent(values[32]);
            bean.setTime(values[30]);
            bean.setMax(values[33]);//33
            bean.setMin(values[34]);//34

            BigDecimal now = new BigDecimal(values[3]);
            String costPriceStr = bean.getCostPrise();
            if (StringUtils.isNotEmpty(costPriceStr)) {
                BigDecimal costPriceDec = new BigDecimal(costPriceStr);
                BigDecimal incomeDiff = now.add(costPriceDec.negate());
                if (costPriceDec.compareTo(BigDecimal.ZERO) <= 0) {
                    bean.setIncomePercent("0");
                } else {
                    BigDecimal incomePercentDec = incomeDiff.divide(costPriceDec, 5, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.TEN)
                            .multiply(BigDecimal.TEN)
                            .setScale(3, RoundingMode.HALF_UP);
                    bean.setIncomePercent(incomePercentDec.toString());
                }

                String bondStr = bean.getBonds();
                if (StringUtils.isNotEmpty(bondStr)) {
                    BigDecimal bondDec = new BigDecimal(bondStr);
                    BigDecimal incomeDec = incomeDiff.multiply(bondDec)
                            .setScale(2, RoundingMode.HALF_UP);
                    bean.setIncome(incomeDec.toString());
                }
            }

            updateData(bean);
        }
    }

}

package com.okaka.easystock.ui;

import com.okaka.easystock.domain.service.StockService;
import com.okaka.easystock.domain.service.impl.StockServiceImpl;
import com.okaka.easystock.domain.vo.StockData;
import com.okaka.easystock.infrastructure.DataStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 主视图UI，主要负责渲染股票数据
 * @author okaka
 * @date 2023-08-29
 */
public class MainViewUI {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel chosePanel;
    private JTable stockTable;

    // 查询数据服务
    private final StockService stockService = new StockServiceImpl();

    private final DefaultTableModel stockTableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"股票", "代码", "最新", "涨跌", "涨幅"}
    );

    public MainViewUI() {
        // 初始化表格
        stockTable.setModel(stockTableModel);
        // 查询股票
        List<StockData> stockList = stockService.fetchStockDataList(DataStorage.getInstance().getStockCodes());
        // 更新表格
        updateStockTable(stockList);
        // 初始化界面事件
        initUIEventListener();
    }

    public void updateStockTable(List<StockData> stockDataList) {
        // 清空旧数据
        for (int i = 0; i < stockTableModel.getRowCount(); i++) {
            stockTableModel.removeRow(0);
        }

        // 添加
        for (StockData stockData : stockDataList) {
            stockTableModel.addRow(new String[]{
                    stockData.getName(),
                    stockData.getCode(),
                    stockData.getCurrentPrice().toString(),
                    stockData.getIncrease().toString(),
                    stockData.getIncreasePer().toString()
            });
            stockTable.setModel(stockTableModel);
        }
    }

    /**
     * 初始化UI事件
     */
    private void initUIEventListener() {
        // 鼠标事件
        stockTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = stockTable.getSelectedRow();
                Object stockCode = stockTable.getValueAt(row, 1);
//                GoPicture goPicture = stock.queryGidGoPicture(value.toString());
//                try {
//                    // 分钟K线
//                    picMin.setSize(545, 300);
//                    picMin.setIcon(new ImageIcon(new URL(goPicture.getMinurl())));
//
//                    // 当日K线
//                    picDay.setSize(545, 300);
//                    picDay.setIcon(new ImageIcon(new URL(goPicture.getDayurl())));
//                } catch (MalformedURLException m) {
//                    m.printStackTrace();
//                }
            }
        });
    }

}

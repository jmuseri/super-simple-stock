package com.jpmorgan.stock.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.jpmorgan.stock.dao.StockDao;
import com.jpmorgan.stock.model.Stock;

/**
 * JP Morgan - Super Simple Stock Market
 * In memory implementation of {@code StockDao}
 * @author Jonathan Museri
 */
public class InMemoryStockDao implements StockDao {

  private Map<String, Stock> stockMap = new HashMap<String, Stock>();

  /**
   * @inheritDoc
   */
  public synchronized void addStock(Stock stock) {
    stockMap.put(stock.getSymbol(), stock);
  }

  /**
   * @inheritDoc
   */
  public Stock getStock(String symbol) {
    return stockMap.get(symbol);
  }

}

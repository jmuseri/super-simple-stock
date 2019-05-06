package com.jpmorgan.stock.dao;

import com.jpmorgan.stock.model.Stock;

/**
 * Interface for {@code Stock} database implementation.
 * @author Jonathan Museri
 */
public interface StockDao {

  /**
   * Add new {@code Stock} item to the db.
   * @param stock
   */
  void addStock(Stock stock);

  /**
   * Get {@code Stock} by stock symbol.
   * @param symbol
   * @return
   */
  Stock getStock(String symbol);

}

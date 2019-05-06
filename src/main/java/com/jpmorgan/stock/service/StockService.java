package com.jpmorgan.stock.service;

import java.util.List;

import com.jpmorgan.stock.exception.SuperSimpleStockMarketException;
import com.jpmorgan.stock.model.Stock;
import com.jpmorgan.stock.model.Trade;

/**
 * JP Morgan - Super Simple Stock Market
 * Stock service for {@code Stock}s
 * 
 * @author Jonathan Museri
 */
public interface StockService {

	/**
	 * Add a {@code Stock}
	 * 
	 * @param stock
	 */
	public void addStock(Stock stock);

	/**
	 * Get a {@code Stock}
	 * 
	 * @param symbol
	 * @return
	 */
	public Stock getStock(String symbol);

	/**
	 * Calculate the dividend yield for an {@code Stock} and price
	 * 
	 * @param stock
	 * @param price
	 * @return
	 * @throws SuperSimpleStockMarketException 
	 */
	public double calculateDividendYield(String symbol, double price) throws SuperSimpleStockMarketException;

	/**
	 * Calculate the P/E ration for an {@code Stock} and price
	 * 
	 * @param stock
	 * @param price
	 * @return
	 */
	public double calculatePERatio(String symbol, double price)  throws SuperSimpleStockMarketException;

	/**
	 * Calculate the volume weighted stock price based on a list of
	 * {@code Trades}
	 * 
	 * @param trades
	 * @return
	 */
	public double calculateVolumeWeightedStockPrice(List<Trade> trades);

	/**
	 * Calculate the GBCE for a list of {@code Trades}
	 * 
	 * @param trades
	 * @return
	 */
	public double calculateGBCE(List<Trade> trades);
}

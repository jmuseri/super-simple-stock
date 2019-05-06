package com.jpmorgan.stock.service;

import java.util.List;

import com.jpmorgan.stock.exception.SuperSimpleStockMarketException;
import com.jpmorgan.stock.model.Trade;
import com.jpmorgan.stock.model.TradeType;

/**
 * JP Morgan - Super Simple Stock Market
 * Trade service for {@code Trade}s
 * 
 * @author Jonathan Museri
 */
public interface TradeService {

	/**
	 * Record a {@code Trade}
	 * 
	 * @param trade
	 * @throws SuperSimpleStockMarketException 
	 */
	public void recordTrade(String stockSymbol, int quantity, TradeType type, double price) throws SuperSimpleStockMarketException;

	/**
	 * Get a list of {@code Trade}s for {@code Stock} within the last x minutes
	 * 
	 * @param stock
	 * @param numberOfMinutes
	 * @return
	 */
	public List<Trade> getTrades(String stockSymbol, int numberOfMinutes);

	/**
	 * Get all {@code Trade}s
	 * 
	 * @return
	 */
	public List<Trade> getAllTrades();
}

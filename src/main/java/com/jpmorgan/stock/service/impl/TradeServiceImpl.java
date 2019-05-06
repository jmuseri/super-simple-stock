package com.jpmorgan.stock.service.impl;

import java.util.Calendar;
import java.util.List;

import com.jpmorgan.stock.dao.TradeDao;
import com.jpmorgan.stock.dao.impl.InMemoryTradeDao;
import com.jpmorgan.stock.exception.SuperSimpleStockMarketException;
import com.jpmorgan.stock.model.Stock;
import com.jpmorgan.stock.model.Trade;
import com.jpmorgan.stock.model.TradeType;
import com.jpmorgan.stock.service.TradeService;

/**
 * JP Morgan - Super Simple Stock Market
 * Implementation of {@code TradeService}
 * @author Jonathan Museri
 */
public class TradeServiceImpl implements TradeService {

  
  private StockServiceImpl stockService = StockServiceImpl.getInstance();
  
  private TradeDao tradeDao = new InMemoryTradeDao();

//  Singleton avoided in this first version.   
//  public static TradeServiceImpl getInstance() {
//	    if (instance == null) {
//	      instance = new TradeServiceImpl();
//	    }
//	    return instance;
//	  }
//	  
//	  private TradeServiceImpl() {
//			super();
//		}
  
  
  /**
   * @throws SuperSimpleStockMarketException 
 * @inheritDoc
   */
	public void recordTrade(String stockSymbol, int quantity, TradeType type, double price) throws SuperSimpleStockMarketException {

		Stock stock = stockService.getStock(stockSymbol);
		if (stock == null) {
			throw new SuperSimpleStockMarketException("Stock not found");
		}
		tradeDao.addTrade(new Trade(stock, Calendar.getInstance().getTime(), quantity, type, price));
    
  }

  /**
   * @inheritDoc
   */
  public List<Trade> getTrades(String stockSymbol, int minutes) {
    return tradeDao.getTrades(stockSymbol, minutes);
  }

  /**
   * @inheritDoc
   */
  public List<Trade> getAllTrades() {
    return tradeDao.getAllTrades();
  }

}

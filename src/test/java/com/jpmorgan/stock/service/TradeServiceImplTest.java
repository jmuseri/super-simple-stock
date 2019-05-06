package com.jpmorgan.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stock.exception.SuperSimpleStockMarketException;
import com.jpmorgan.stock.model.Stock;
import com.jpmorgan.stock.model.StockType;
import com.jpmorgan.stock.model.Trade;
import com.jpmorgan.stock.model.TradeType;
import com.jpmorgan.stock.service.impl.StockServiceImpl;
import com.jpmorgan.stock.service.impl.TradeServiceImpl;

/**
 * Tests for {@code TradeService}
 * 
 * @author Jonathan Museri
 */
public class TradeServiceImplTest {

	private TradeService tradeService;
	private StockService stockService;
	private Stock stock1;

	@Before
	public void setup() {
		tradeService = new TradeServiceImpl();
		stockService = StockServiceImpl.getInstance();
		stock1 = new Stock("TEST", StockType.COMMON, 1, 0, 1);
		stockService.addStock(stock1);
		
	}

	@Test
	public void testRecordTrade() {

		try {
			tradeService.recordTrade(stock1.getSymbol(), 1, TradeType.BUY, 1.0);
		} catch (SuperSimpleStockMarketException e) {
			e.printStackTrace();
		}
		List<Trade> result = tradeService.getTrades(stock1.getSymbol(), 15);
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetAllTradesInLast15Minutes() {
		try {
			tradeService.recordTrade(stock1.getSymbol(), 1, TradeType.BUY, 1.0);
			tradeService.recordTrade(stock1.getSymbol(), 1, TradeType.BUY, 1.0);
		} catch (SuperSimpleStockMarketException e) {
			e.printStackTrace();
		}

		List<Trade> result = tradeService.getTrades(stock1.getSymbol(), 15);
		assertNotNull(result);
		assertEquals(2, result.size());

	}

	@Test
	public void testGetAllTrades() {
		try {
			tradeService.recordTrade(stock1.getSymbol(), 1, TradeType.BUY, 1.0);
			tradeService.recordTrade(stock1.getSymbol(), 1, TradeType.BUY, 1.0);
			tradeService.recordTrade(stock1.getSymbol(), 1, TradeType.BUY, 1.0);
		} catch (SuperSimpleStockMarketException e) {
			e.printStackTrace();
		}

		List<Trade> result = tradeService.getAllTrades();
		assertEquals(3, result.size());
	}

}

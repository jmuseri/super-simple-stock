package com.jpmorgan.stock.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jpmorgan.stock.exception.SuperSimpleStockMarketException;
import com.jpmorgan.stock.model.Stock;
import com.jpmorgan.stock.model.StockType;
import com.jpmorgan.stock.model.Trade;
import com.jpmorgan.stock.model.TradeType;
import com.jpmorgan.stock.service.impl.StockServiceImpl;

/**
 * Tests for {@code StockService}
 * 
 * @author Jonathan Museri
 */
public class StockServiceImplTest {

	private StockService stockService;
	private Stock stock1;
	private Stock stock2;
	private Stock stock3;

	@Before
	public void setup() {
		stockService = StockServiceImpl.getInstance();
		stock1 = new Stock("TEST", StockType.COMMON, 4, 0, 10);
		stock2 = new Stock("TEST2", StockType.PREFERRED, 3, 2, 1);
		stockService.addStock(stock1);
		stockService.addStock(stock2);
	}

	@Test
	public void testAddAndGetStock() {
		stockService.addStock(stock1);
		Stock result = stockService.getStock(stock1.getSymbol());
		assertEquals(stock1, result);
	}

	@Test
	public void testCalculateDividendYield() {
		try {
			assertEquals(1.6, stockService.calculateDividendYield(stock1.getSymbol(), 2.5), 0);
		} catch (SuperSimpleStockMarketException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCalculateDividendYieldPrefered() {
		try {
			assertEquals(0.8, stockService.calculateDividendYield(stock2.getSymbol(), 2.5), 0);
		} catch (SuperSimpleStockMarketException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCalculateDividendYieldNegativePrice() {

		boolean flag = false;
		try {
			stockService.calculateDividendYield(stock1.getSymbol(), -2.5);
		} catch (SuperSimpleStockMarketException e) {
			flag = true;
		}
		assertTrue(flag);
	}

	@Test
	public void testCalculatePERatio() {
		try {
			assertEquals(0.63, stockService.calculatePERatio(stock1.getSymbol(), 2.5), 0);
		} catch (SuperSimpleStockMarketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		List<Trade> trades = new ArrayList<Trade>();
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 3));
		assertEquals(1.9, stockService.calculateVolumeWeightedStockPrice(trades), 0);
	}

	@Test
	public void testCalculateGBCE() {
		List<Trade> trades = new ArrayList<Trade>();
		trades.add(new Trade(stock1, Calendar.getInstance().getTime(), 1, TradeType.BUY, 2));
		trades.add(new Trade(stock2, Calendar.getInstance().getTime(), 3, TradeType.BUY, 1.5));
		trades.add(new Trade(stock3, Calendar.getInstance().getTime(), 1, TradeType.BUY, 3));
		assertEquals(2.08, stockService.calculateGBCE(trades), 0);
	}
}

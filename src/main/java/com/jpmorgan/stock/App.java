package com.jpmorgan.stock;

import java.util.List;
import java.util.Scanner;

import com.jpmorgan.stock.exception.SuperSimpleStockMarketException;
import com.jpmorgan.stock.model.Stock;
import com.jpmorgan.stock.model.StockType;
import com.jpmorgan.stock.model.Trade;
import com.jpmorgan.stock.model.TradeType;
import com.jpmorgan.stock.service.StockService;
import com.jpmorgan.stock.service.TradeService;
import com.jpmorgan.stock.service.impl.StockServiceImpl;
import com.jpmorgan.stock.service.impl.TradeServiceImpl;

/**
 * JP Morgan - Super Simple Stock Market
 * Main app to run the service as a standalone application.
 * 
 * @author Jonathan Museri
 */
public class App {

	private static StockService stockService = StockServiceImpl.getInstance();
	private static TradeService tradeService = new TradeServiceImpl();

	private static Scanner scanner;

	public static void main(String[] args) {
		initStocks();
		printMenu();

		scanner = new Scanner(System.in);
		String choice = null;
		while (true) {
			choice = scanner.nextLine();
			if ("q".equals(choice)) {
				scanner.close();
				System.exit(0);
			} else {
				try {
					int option = Integer.parseInt(choice);
					String stockSymbol;
					double priceFromUser;

					switch (option) {
					case 1:
						stockSymbol = getStockFromUser();
						priceFromUser = getStockPriceFromUser();
						calculateDividendYield(stockSymbol, priceFromUser);
						break;
					case 2:
						stockSymbol = getStockFromUser();
						priceFromUser = getStockPriceFromUser();
						calculatePERatio(stockSymbol, priceFromUser);
						break;
					case 3:
						stockSymbol = getStockFromUser();
						int quantityFromUser = getQuantityFromUser();
						TradeType tradeTypeFromUser = getTradeType();
						priceFromUser = getStockPriceFromUser();
						recordTrade(stockSymbol, quantityFromUser, tradeTypeFromUser, priceFromUser);
						break;
					case 4:
						stockSymbol = getStockFromUser();
						calculateVolumeWeightedStockPrice(stockSymbol);
						break;
					case 5:
						calculateGBCE();
						break;
					default:
						break;
					}
				} catch (NumberFormatException e) {
					printResult("Invalid Option");
				} catch (SuperSimpleStockMarketException e1) {
					printResult(e1.getMessage());
				}
				System.out.println("");
				printMenu();
			}
		}
	}

	private static String getStockFromUser() throws SuperSimpleStockMarketException {
		System.out.println("Please input stock symbol");
		String stockSymbol = scanner.nextLine();
		return stockSymbol;
	}

	private static double getStockPriceFromUser() throws SuperSimpleStockMarketException {
		System.out.println("Please input stock price");
		String stockPrice = scanner.nextLine();
		try {
			double result = Double.parseDouble(stockPrice);
			return result;
		} catch (NumberFormatException e) {
			throw new SuperSimpleStockMarketException("Invalid price: Not a number");
		}
	}

	private static TradeType getTradeType() throws SuperSimpleStockMarketException {
		System.out.println("Please input trade type (BUY/SELL)");
		String type = scanner.nextLine();
		try {
			return TradeType.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new SuperSimpleStockMarketException("Invalid trade type: Must be BUY or SELL");
		}
	}

	private static int getQuantityFromUser() throws SuperSimpleStockMarketException {
		System.out.println("Please input quantity");
		String quantity = scanner.nextLine();
		try {
			int result = Integer.parseInt(quantity);
			if (result <= 0) {
				throw new SuperSimpleStockMarketException("Invalid quantity: Must be greated than 0");
			}
			return result;
		} catch (NumberFormatException e) {
			throw new SuperSimpleStockMarketException("Invalid quantity: Not a number");
		}
	}

	private static void printMenu() {
		System.out.println("JPMorgan - Super simple stock market");
		System.out.println("1: Calculate dividend yield for stock");
		System.out.println("2: Calculate P/E ratio for stock");
		System.out.println("3: Record a trade for stock");
		System.out.println("4: Calculate Volume Weighted Stock Price for stock");
		System.out.println("5: Calculate GBCE All Share Index");
		System.out.println("q: Quit");
	}

	private static void calculateDividendYield(String stock, double price) throws SuperSimpleStockMarketException {
		double result = stockService.calculateDividendYield(stock, price);
		printResult("Dividend Yield: " + result);
	}

	private static void calculatePERatio(String stock, double price)  throws SuperSimpleStockMarketException{
		double result = stockService.calculatePERatio(stock, price);
		printResult("PE Ratio: " + result);
	}

	private static void calculateVolumeWeightedStockPrice(String stockSymbol) {
		List<Trade> trades = tradeService.getTrades(stockSymbol, 5);
		if (trades == null || trades.isEmpty()) {
			printResult("Volume Weighted Stock Price: No trades");
		} else {
			double result = stockService.calculateVolumeWeightedStockPrice(trades);
			printResult("Volume Weighted Stock Price: " + result);
		}
	}

	private static void recordTrade(String stockSymbol, int quantity, TradeType type, double price) throws SuperSimpleStockMarketException {
		tradeService.recordTrade(stockSymbol,quantity, type, price);
		printResult("Trade recorded");
	}

	private static void calculateGBCE() {
		List<Trade> allTrades = tradeService.getAllTrades();
		if (allTrades == null || allTrades.isEmpty()) {
			printResult("Unable to calculate GBCE: No trades");
		} else {
			printResult("GBCE: " + stockService.calculateGBCE(allTrades));
		}
	}

	private static void initStocks() {
		stockService.addStock(new Stock("TEA", StockType.COMMON, 0, 0, 100));
		stockService.addStock(new Stock("POP", StockType.COMMON, 8, 0, 100));
		stockService.addStock(new Stock("ALE", StockType.COMMON, 23, 0, 60));
		stockService.addStock(new Stock("GIN", StockType.PREFERRED, 8, 2, 100));
		stockService.addStock(new Stock("JOE", StockType.COMMON, 13, 0, 250));
	}

	private static void printResult(String result) {
		System.out.println("-------------------------------------");
		System.out.println(result);
		System.out.println("-------------------------------------");
	}
}

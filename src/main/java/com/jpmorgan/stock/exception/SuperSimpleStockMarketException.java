package com.jpmorgan.stock.exception;

/**
 * JP Morgan - Super Simple Stock Market
 * Custom exception for any super simple stock errors.
 * @author Jonathan Museri
 */
public class SuperSimpleStockMarketException extends Exception {

  private static final long serialVersionUID = 1L;

  public SuperSimpleStockMarketException(String message) {
    super(message);
  }

}

package com.jpmorgan.stock.validation;

import com.jpmorgan.stock.exception.SuperSimpleStockMarketException;

//TODO: Improve validation management.
public class ValidationHelper {
	private static ValidationHelper instance;

	public void validatePrice(double price) throws SuperSimpleStockMarketException {
		if (!(price >= 0))
			throw new SuperSimpleStockMarketException("Invalid price: Must be greated than 0");
	}

	public static ValidationHelper getInstance() {
		if (instance == null)
			instance = new ValidationHelper();
		return instance;
	}

}

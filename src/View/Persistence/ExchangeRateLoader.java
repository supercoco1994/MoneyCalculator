package View.Persistence;

import Model.Currency;
import Model.ExchangeRate;

public interface ExchangeRateLoader {

    public ExchangeRate load(Currency currencyFrom, Currency currencyTo);
}

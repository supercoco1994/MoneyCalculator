package View.Process;

import Model.ExchangeRate;
import Model.Money;

public class Exchanger {

    private final double quantity;
    private final ExchangeRate exchangeRate;

    public Exchanger(double quantity, ExchangeRate exchangeRate) {
        this.quantity = quantity;
        this.exchangeRate = exchangeRate;
    }

    public Money getMoney() {
        return new Money(getExchange(), exchangeRate.getCurrencyTo());
    }

    private double getExchange() {
        return quantity * exchangeRate.getRate();
    }
}

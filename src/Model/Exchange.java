package Model;

public class Exchange {

    private final Money money;
    private final Currency currencyTo;

    public Exchange(Money money, Currency currencyTo) {
        this.money = money;
        this.currencyTo = currencyTo;
    }

    public Money getMoney() {
        return money;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

}

package Control;

import Model.CurrencySet;
import Model.Exchange;
import Model.ExchangeRate;
import Model.Money;
import View.Persistence.DatabaseExchangeRateLoader;
import View.Process.Exchanger;
import View.UI.ExchangeDialog;
import View.UI.MoneyDisplay;
import java.sql.Connection;
import java.sql.SQLException;

public class ExchangeCommand {

    private final CurrencySet currencySet;
    private ExchangeDialog exchangeDialog;
    private final Connection connection;

    public ExchangeCommand(CurrencySet currencySet, Connection connection) {
        this.currencySet = currencySet;
        this.connection = connection;
    }

    public void exec(ExchangeDialog exchangeDialog, MoneyDisplay moneyPanel) throws SQLException {
        Exchange exchange = readExchange(exchangeDialog);
        ExchangeRate exchangeRate = readExchangeRate(exchange);
        Money money = readMoney(exchange, exchangeRate);
        displayMoney(moneyPanel, money);
    }

    private Exchange readExchange(ExchangeDialog exchangeDialog) {
        return exchangeDialog.getExchange();
    }

    private ExchangeRate readExchangeRate(Exchange exchange) throws SQLException {
        return new DatabaseExchangeRateLoader(connection).load(exchange.getMoney().getCurrency(), exchange.getCurrencyTo());
    }

    private Money readMoney(Exchange exchange, ExchangeRate exchangeRate) {
        return new Exchanger(exchange.getMoney().getQuantity(), exchangeRate).getMoney();
    }

    private void displayMoney(MoneyDisplay moneyPanel, Money money) {
        moneyPanel.display(money);
    }
}

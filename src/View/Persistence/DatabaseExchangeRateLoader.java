package View.Persistence;

import Model.Currency;
import Model.ExchangeRate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExchangeRateLoader implements ExchangeRateLoader {

    private final Connection connection;

    public DatabaseExchangeRateLoader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ExchangeRate load(Currency currencyFrom, Currency currencyTo) {
        if (currencyFrom.getCode().equals(currencyTo.getCode())) {
            return new ExchangeRate(currencyFrom, currencyTo, 1.0d);
        }
        try {
            return processQuery(connection.createStatement().executeQuery(
                    "Select CURRENCYFROM,CURRENCYTO,RATE"
                    + " FROM EXCHANGE_RATE"
                    + " WHERE CURRENCYFROM = '" + currencyFrom.getCode() + "'"
                    + " AND CURRENCYTO = '" + currencyTo.getCode() + "'"
                    + " AND ALTA = (SELECT MAX(ALTA) FROM EXCHANGE_RATE)"),
                    currencyFrom, currencyTo);
        } catch (SQLException ex) {
            return null;
        }
    }

    private ExchangeRate processQuery(ResultSet resultSet, Currency currencyFrom, Currency currencyTo) throws SQLException {
        ExchangeRate exchangeRate = null;
        while (resultSet.next()) {
            exchangeRate = processExchangeRate(resultSet, currencyFrom, currencyTo);
        }
        return exchangeRate;
    }

    private ExchangeRate processExchangeRate(ResultSet resultSet, Currency currencyFrom, Currency currencyTo) throws SQLException {
        return new ExchangeRate(
                currencyFrom,
                currencyTo,
                resultSet.getDouble("RATE"));
    }

}

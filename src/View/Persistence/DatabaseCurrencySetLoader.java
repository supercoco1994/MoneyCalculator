package View.Persistence;

import Model.Currency;
import Model.CurrencySet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseCurrencySetLoader implements CurrencySetLoader {

    private final Connection connection;

    public DatabaseCurrencySetLoader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public CurrencySet load() {
        try {
            return processQuery(connection.createStatement().executeQuery(
                    "select * FROM CURRENCY"));
        } catch (SQLException ex) {
            return null;
        }
    }

    private CurrencySet processQuery(ResultSet resultSet) throws SQLException {
        ArrayList<Currency> currencySet = new ArrayList<>();
        while (resultSet.next()) {
            currencySet.add(processCurrency(resultSet));
        }
        return new CurrencySet(currencySet);
    }

    private Currency processCurrency(ResultSet resultSet) throws SQLException {
        return new Currency(
                resultSet.getString("code"),
                resultSet.getString("name"),
                resultSet.getString("symbol"));
    }
}

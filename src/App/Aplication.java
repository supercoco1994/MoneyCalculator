package App;

import Control.ExchangeCommand;
import Model.CurrencySet;
import Swing.ApplicationFrame;
import View.Persistence.DatabaseCurrencySetLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Aplication {

    private static CurrencySet currencySet;
    private static ExchangeCommand exchangeCommand;

    public static void main(String[] args) throws SQLException {
        Connection connection = createOracleConnection("192.168.56.101:1521:ORCL");

        currencySet = new DatabaseCurrencySetLoader(connection).load();
        exchangeCommand = new ExchangeCommand(currencySet, connection);
        new ApplicationFrame(currencySet, exchangeCommand);

    }

    private static Connection createOracleConnection(String dbPath) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        return DriverManager.getConnection("jdbc:oracle:thin:@" + dbPath, "system", "orcl");
    }
}

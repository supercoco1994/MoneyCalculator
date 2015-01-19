package Swing;

import Control.ExchangeCommand;
import Model.CurrencySet;
import View.UI.ExchangeDialog;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationFrame extends JFrame {

    private final CurrencySet currencySet;
    private final ExchangeCommand exchangeCommand;
    private ExchangeDialog exchangeDialog;
    private MoneyPanel moneyPanel;
    private ActionListener actionListener;

    public ApplicationFrame(CurrencySet currencySet, ExchangeCommand exchangeCommand) {
        this.currencySet = currencySet;
        this.exchangeCommand = exchangeCommand;
        this.setTitle("Money Calculator");
        this.setLayout(new BorderLayout());
        this.setSize(350, 250);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(createExchangeDialogPanel(currencySet), NORTH);
        this.getContentPane().add(createMoneyPanel(), CENTER);
        this.getContentPane().add(createCalculateButton(), SOUTH);
        this.setVisible(true);

    }

    private JButton createCalculateButton() {
        JButton button = new JButton("Calcular");
        button.addActionListener((ActionEvent e) -> {
            try {
                exchangeCommand.exec(exchangeDialog, moneyPanel);
            } catch (SQLException ex) {
                Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return button;
    }

    private JPanel createMoneyPanel() {
        this.moneyPanel = new MoneyPanel();
        return this.moneyPanel;
    }

    private JPanel createExchangeDialogPanel(CurrencySet currencySet) {
        ExchangeDialogPanel exchangeDialogPanel = new ExchangeDialogPanel(currencySet);
        exchangeDialog = exchangeDialogPanel;
        return exchangeDialogPanel;
    }

    public ExchangeDialog getExchangeDialog() {
        return exchangeDialog;
    }

}

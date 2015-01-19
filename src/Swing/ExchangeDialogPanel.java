package Swing;

import Model.Currency;
import Model.CurrencySet;
import Model.Exchange;
import Model.Money;
import View.UI.ExchangeDialog;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

class ExchangeDialogPanel extends JPanel implements ExchangeDialog {

    private final CurrencySet currencySet;
    private double quantity;
    private Currency currencyFrom;
    private Currency currencyTo;

    public ExchangeDialogPanel(CurrencySet currencySet) {
        this.currencySet = currencySet;
        this.currencyFrom = currencySet.getCurrencySet().get(0);
        this.currencyTo = currencySet.getCurrencySet().get(0);
        this.setLayout(new BorderLayout());
        this.add(createCurrencyFromComboBox(), NORTH);
        this.add(createQuantityField(), CENTER);
        this.add(createCurrencyToComboBox(), SOUTH);
        this.setVisible(true);
    }

    @Override
    public Exchange getExchange() {
        return new Exchange(new Money(quantity, currencyFrom), currencyTo);
    }

    private JTextField createQuantityField() {
        JTextField quantityField = new JTextField();
        quantityField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (quantityField.getText().compareTo("Introduce the desired quantity you want to convert...") == 0) {
                    quantityField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if ("".equals(quantityField.getText())) {
                    quantityField.setText("Introduce the desired quantity you want to convert...");
                }
                quantity = Double.parseDouble(quantityField.getText());
            }
        });
        return quantityField;
    }

    private JComboBox<Currency> createCurrencyFromComboBox() {
        JComboBox<Currency> currencyFromComboBox;
        currencyFromComboBox = new JComboBox<>(currencySet.toArray());

        currencyFromComboBox.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                currencyFrom = currencyFromComboBox.getItemAt(currencyFromComboBox.getSelectedIndex());
            }
        });
        return currencyFromComboBox;
    }

    private JComboBox<Currency> createCurrencyToComboBox() {
        JComboBox<Currency> currencyToComboBox;
        currencyToComboBox = new JComboBox<>(currencySet.toArray());

        currencyToComboBox.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() != ItemEvent.SELECTED) {
                currencyTo = currencyToComboBox.getItemAt(currencyToComboBox.getSelectedIndex());
            }
        });
        return currencyToComboBox;
    }

}

package Swing;

import Model.Money;
import View.UI.MoneyDisplay;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MoneyPanel extends JPanel implements MoneyDisplay {

    private Money money;
    private JTextField resultField;

    public MoneyPanel() {
        this.setLayout(new FlowLayout());
        this.add(createResultField(20));
        this.setVisible(true);
    }

    private JTextField createResultField(int width) {
        this.resultField = new JTextField(width);
        this.resultField.setEditable(false);
        return resultField;
    }

    @Override
    public void display(Money money) {
        resultField.setText(money.getQuantity() + "  " + money.getCurrency().getCode() + "(" + money.getCurrency().getSymbol() + ")");
    }

}

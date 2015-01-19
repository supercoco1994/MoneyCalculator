package Model;

import java.util.ArrayList;

public class CurrencySet {

    private final ArrayList<Currency> currencySet;

    public CurrencySet(ArrayList<Currency> currencySet) {
        this.currencySet = currencySet;
    }

    public ArrayList<Currency> getCurrencySet() {
        return currencySet;
    }

    public void add() {
    }

    public void remove() {
    }

    public Currency search() {
        return null;
    }

    public Currency[] toArray() {
        Currency[] currencyArray = new Currency[currencySet.size()];
        int i = 0;
        for (Currency currency : currencySet) {
            currencyArray[i++] = currency;
        }
        return currencyArray;
    }

}

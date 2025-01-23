package com.refactoring.chapter1.format;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormat {
    public static String usd(int amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(amount / 100.0);
    }
}

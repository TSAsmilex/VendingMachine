package com.tsystems;

import java.util.ArrayList;
import java.util.Collections;


public enum CoinEuro { // En céntimos
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500);

    private final int value;

    private CoinEuro(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public static CoinEuro fromValue(int value) {
        for (CoinEuro e : CoinEuro.values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }


    public static ArrayList<CoinEuro> fromString (String coinsString) {
        // Monedas separadas por comas o individualmente
        coinsString.trim();
        String[] coinsArray = coinsString.split(",");

        ArrayList<CoinEuro> coins = new ArrayList<CoinEuro>();

        for (String coin : coinsArray) {
            coins.add(CoinEuro.fromValue(Integer.parseInt(coin.trim())));
        }

        return coins;
    }


    public static int sum (ArrayList<CoinEuro> coins) {
        int total = 0;
        for (CoinEuro coin: coins) {
            total += coin.getValue();
        }

        return total;
    }


    public static ArrayList<CoinEuro> change (int total) {
        ArrayList<Integer> acceptedValues = new ArrayList<>();

        for (CoinEuro coin : CoinEuro.values()) {
            acceptedValues.add(coin.getValue());
        }

        ArrayList<CoinEuro> change = new ArrayList<CoinEuro>();
        int remaining = total;

        while (remaining > 0) {
            int max = Collections.max(acceptedValues);

            if (max <= remaining) {
                change.add(CoinEuro.fromValue(max));
                remaining -= max;
            }
            else {
                acceptedValues.remove(Integer.valueOf(max));
            }
        }

        return change;
    }
}

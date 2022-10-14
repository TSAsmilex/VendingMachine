package com.tsystems;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class CoinEuroTest {


    @Test
    public void givenCoinEuro_whenBuyingProduct_thenGettingNonNegativeExchange() {
        ArrayList<CoinEuro> change = CoinEuro.change(100);
        assertTrue(change.size() > 0);
    }


    @Test
    public void given600Cents_whenChangingCoins_thenReturnedMoneyAddsUpTo600() {
        ArrayList<CoinEuro> change = CoinEuro.change(600);
        assertTrue(CoinEuro.sum(change) == 600);
    }

    @Test
    public void testSum() {
        ArrayList<CoinEuro> coins = new ArrayList<>();
        coins.add(CoinEuro.fromValue(100));
        coins.add(CoinEuro.fromValue(200));
        assertTrue(CoinEuro.sum(coins) == 300);
    }
}

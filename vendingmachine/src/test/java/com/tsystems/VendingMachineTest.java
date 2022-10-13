package com.tsystems;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class VendingMachineTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void givenVendingMachine_whenBuyProductExactPrice_thenReturnNoChange() {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();

        coins.add(CoinEuro.TWO_EURO);

        var change = vendingMachine.buyProduct(Product.PLUMBUS, coins);

        assertTrue(change.isEmpty());
    }

    @Test
    public void givenVendingMachine_whenBuyProductMoreThanPrice_thenReturnChange() {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();

        coins.add(CoinEuro.TWO_EURO);
        coins.add(CoinEuro.TWO_EURO);

        var change = vendingMachine.buyProduct(Product.PLUMBUS, coins);

        assertTrue(change.size() == 1);
        assertTrue(change.get(0) == CoinEuro.TWO_EURO);
    }

    @Test
    public void givenVendingMachine_whenBuyProductMoreThanPriceNoExactChange_thenReturnChange() {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();

        coins.add(CoinEuro.TWO_EURO);
        coins.add(CoinEuro.TWO_EURO);
        coins.add(CoinEuro.TWO_EURO);
        int expectedReturn = 400;

        ArrayList<CoinEuro> change = vendingMachine.buyProduct(Product.PLUMBUS, coins);
        assertTrue(change.size() > 1);
        assertTrue(CoinEuro.sum(change) == expectedReturn);
    }

    @Test
    public void givenVendingMachine_whenNoStock_thenThrowException() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setStock(Product.PLUMBUS, 0);
        ArrayList<CoinEuro> coins = new ArrayList<>();

        assertThrows(ArithmeticException.class, () ->
            vendingMachine.buy(Product.PlUMBUS, coins)
        );
    }
}

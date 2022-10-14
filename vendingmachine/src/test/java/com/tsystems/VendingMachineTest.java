package com.tsystems;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class VendingMachineTest
{
    @Test
    public void givenVendingMachine_whenBuyProductExactPrice_thenReturnNoChange() throws Exception {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();

        coins.add(CoinEuro.TWO_HUNDRED);

        ArrayList<CoinEuro> change = vendingMachine.buy(Product.PLUMBUS, coins);

        assertTrue(change.isEmpty());
    }

    @Test
    public void givenVendingMachine_whenBuyProductMoreThanPrice_thenReturnChange() throws Exception {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();

        coins.add(CoinEuro.TWO_HUNDRED);
        coins.add(CoinEuro.TWO_HUNDRED);

        ArrayList<CoinEuro> change = vendingMachine.buy(Product.PLUMBUS, coins);

        assertTrue(change.size() == 1);
        assertTrue(change.get(0) == CoinEuro.TWO_HUNDRED);
    }

    @Test
    public void givenVendingMachine_whenBuyProductMoreThanPriceNoExactChange_thenReturnChange() throws Exception {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();

        coins.add(CoinEuro.TWO_HUNDRED);
        coins.add(CoinEuro.TWO_HUNDRED);
        coins.add(CoinEuro.TWO_HUNDRED);
        int expectedReturn = 400;

        ArrayList<CoinEuro> change = vendingMachine.buy(Product.PLUMBUS, coins);
        assertTrue(change.size() > 1);
        assertTrue(CoinEuro.sum(change) == expectedReturn);
    }

    @Test(expected = Exception.class)
    public void givenVendingMachine_whenNoStock_thenThrowException() throws Exception {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setStock(Product.PLUMBUS, 0);
        ArrayList<CoinEuro> coins = new ArrayList<>();
        vendingMachine.buy(Product.PLUMBUS, coins);
    }

    @Test(expected = ArithmeticException.class)
    public void givenVendingMachine_whenNotEnoughMoney_thenThrowException() throws Exception {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();
        coins.add(CoinEuro.ONE);
        vendingMachine.buy(Product.PLUMBUS, coins);
    }

    @Test
    public void whenBuyingProduct_thenDecreaseInventory() {
        VendingMachine vendingMachine = new VendingMachine();
        ArrayList<CoinEuro> coins = new ArrayList<>();
        coins.add(CoinEuro.TWO_HUNDRED);

        int oldStock = vendingMachine.getStock(Product.PLUMBUS);
        vendingMachine.buy(Product.PLUMBUS, coins);
        int newStock = vendingMachine.getStock(Product.PLUMBUS);

        assertTrue(oldStock - newStock == 1);
    }

    // ─────────────────────────────────────────────────────────────────────────────


    @Test
    public void givenVendingMachine_whenConsultingPrice_thenPriceNonNegative() {
        VendingMachine vendingMachine = new VendingMachine();
        int price = vendingMachine.getPrice(Product.PLUMBUS);
        assertTrue(price >= 0);
    }

    @Test
    public void givenVendingMachine_whenConsultingStock_thenStockNonNegative() {
        VendingMachine vendingMachine = new VendingMachine();
        int stock = vendingMachine.getStock(Product.WUMPA);
        assertTrue(stock > 0);
    }

    // ─────────────────────────────────────────────────────────────────────────────


    @Test
    public void whenAddingNewInventory_thenCheckStockIsCorrect () {
        VendingMachine vendingMachine = new VendingMachine();

        var oldStock = vendingMachine.getStock(Product.PLUMBUS);
        var newStock = 5;
        vendingMachine.addInventory(new ProductInventory(Product.PLUMBUS, newStock, 100));
        var currentStock = vendingMachine.getStock(Product.PLUMBUS);

        assertEquals(oldStock + newStock, currentStock);

    }
}

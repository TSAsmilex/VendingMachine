package com.tsystems;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProductInventoryTest {
    @Test
    public void givenProductInventory_whenBuyingProductWithStock_thenDecreaseStock() {
        ProductInventory productInventory = new ProductInventory(Product.PLUMBUS, 2, 100);
        assertTrue(productInventory.decreaseStock());
    }

    @Test
    public void givenProductInventoryWithNoStock_whenDecreaseStock_thenReturnFalse() {
        ProductInventory productInventory = new ProductInventory(Product.PLUMBUS, 0, 100);
        assertTrue(!productInventory.decreaseStock());
    }

    @Test
    public void testIsAvailable() {

    }
}

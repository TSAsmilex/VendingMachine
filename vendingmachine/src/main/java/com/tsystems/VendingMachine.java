package com.tsystems;

import java.util.ArrayList;

public class VendingMachine {
    private ArrayList<ProductInventory> products = new ArrayList<>();


    public VendingMachine() {
        products.add(new ProductInventory(Product.PLUMBUS, 3, 200));
        products.add(new ProductInventory(Product.WUMPA, 2, 100));
    }


    public ArrayList<CoinEuro> buy (Product product, ArrayList<CoinEuro> coins) throws ArithmeticException {
        var productInv = products.stream()
            .filter(p -> p.getProduct() == product)
            .findFirst()
            .get();

        if (!productInv.decreaseStock()) {
            throw new ArithmeticException("No hay suficiente stock para este producto");
        }

        var paid = CoinEuro.sum(coins);

        if (paid < productInv.getPrice()) {
            throw new ArithmeticException("No hay suficiente dinero para pagar el producto");
        }

        return CoinEuro.change(paid - productInv.getPrice());
    }


    public boolean setStock(Product product, int stock) {
        var possibleProduct = products.stream()
            .filter(p -> p.getProduct() == product)
            .findFirst();

        if (!possibleProduct.isPresent()) {
            return false;
        }

        var productInv = possibleProduct.get();

        productInv.setStock(stock);
        return true;
    }

    public int getStock(Product product) {
        ProductInventory productInv = products.stream().filter(p -> p.getProduct() == product)
        .findFirst()
        .get();

        return productInv.getStock();
    }


    public int getPrice(Product product) {
        ProductInventory productInv = products.stream()
            .filter(p -> p.getProduct() == product)
            .findFirst()
            .get();

        return productInv.getPrice();
    }
}

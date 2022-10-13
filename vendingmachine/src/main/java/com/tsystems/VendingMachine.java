import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {
    private ArrayList<ProductInventory> products = new ArrayList<>();

    public VendingMachine() {
        products.add(new ProductInventory(Product.PLUMBUS, 3, 200));
        products.add(new ProductInventory(Product.WUMPA, 2, 100));
    }

    public ArrayList<CoinEuro> buy (Product product, ArrayList<CoinEuro> coins) {
        var productInv = products.stream()
            .filter(p -> p.getProduct() == product)
            .findFirst()
            .get();

        var paid = CoinEuro.sum(coins);
        ArrayList<CoinEuro> change = new ArrayList<>();

        if (paid < productInv.getPrice()) {
            // FIXME excepción
        }
        else if (paid > productInv.getPrice()) {
            int difference = paid - productInv.getPrice();
            change.add(CoinEuro.fromValue(difference));
            // FIXME problema: monedas de 10 y monedas de 15.
        }

        if (productInv.decreaseStock()) {
            // FIXME exception?
        }

        return change;
    }

    public boolean setStock(Product product, int stock) {
        var possibleProduct = products.stream()
            .filter(p -> p.getProduct() == product)
            .findFirst();

        if (!possibleProduct.isPresent()) {
            return false;
        }

        var productInv = possibleProduct.get();

        productInv.setQuantity(stock);
        return true;
    }
}

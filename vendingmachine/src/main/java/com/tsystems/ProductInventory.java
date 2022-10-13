public class ProductInventory {
    private Product product;
    private int quantity;
    private int price;

    public ProductInventory(Product product, int quantity, int price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public boolean decreaseStock() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }
}

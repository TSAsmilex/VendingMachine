public enum Product {
    PLUMBUS("Plumbus"),
    WUMPA("Wumpa");

    private final String name;

    private Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Product fromName(String name) {
        for (Product p : Product.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}

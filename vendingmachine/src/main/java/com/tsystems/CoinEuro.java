import java.util.ArrayList;


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
}

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

enum MenuOptions {
    NOOP(0),
    BUY(1),
    EXIT(2);

    private final int value;

    private MenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuOptions fromValue(int value) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }

        throw new InputMismatchException("Invalid option");
    }

    public static void printMenu() {
        System.out.println("   1) Comprar");
        System.out.println("   2) Salir");
    }
}


public class App {


    public static void main(String[] args) throws Exception {
        System.out.print("¿¿¿A quién no le va a gustar un buen baptisterio???");

        var       option = MenuOptions.NOOP;
        Scanner   scan   = new Scanner(System.in);
        Exception error  = null;

        do {
            clearScreen();

            if (error != null) {
                System.err.println("Se ha producido un error. Motivo:\t" + error.getMessage());
            }

            error  = null;
            option = MenuOptions.NOOP;

            System.out.println("\n¡Hola! ¿Qué quieres hacer?");
            MenuOptions.printMenu();

            error = null;

            try {
                System.out.print("> ");
                option = MenuOptions.fromValue(scan.nextInt());
                scan.nextLine();
            }
            catch (InputMismatchException e) {
                error = new InputMismatchException("Introduce un comando válido.");
                scan.nextLine();
            }

            switch (option) {
                case BUY -> {

                }
                case EXIT   -> System.out.println("¡Hasta luego!");
                case NOOP   -> {}
            }
        } while (option != MenuOptions.EXIT);

        scan.close();
    }


    private static void book (Scanner scan) throws Exception, DateTimeException {
        int round     = 0;
        int remaining = baptisterio.PRICE;
        ArrayList<CoinEuro> coins = new ArrayList<CoinEuro>();


        do {
            clearScreen();
            System.out.println("Debes abonar " + baptisterio.PRICE + " céntimos.");
            System.out.println("Introduce las monedas en el sistema. Puedes separarlas por comas o introducirlas poco a poco");
            System.out.print(remaining + " céntimos restantes. \n> ");

            String coinsString = scan.nextLine();
            var coinsRound = CoinEuro.fromString(coinsString);
            coins.addAll(coinsRound);

            round      = CoinEuro.sum(coinsRound);
            remaining -= round;

        } while (round <= remaining);

        if (remaining < 0) {
            System.out.println("\nTe has pasado " + Math.abs(remaining) + " céntimos.\n(Te imaginas que te he dado el cambio porque esto es un programa cutre).");
            coins.remove(CoinEuro.fromValue(Math.abs(remaining)));
        }

        System.out.println("Su pago se ha procesado correctamente");

        Thread.sleep(2000);
    }


    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

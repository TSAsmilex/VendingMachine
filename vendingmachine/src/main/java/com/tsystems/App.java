package com.tsystems;

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
    static VendingMachine vendingMachine = new VendingMachine();

    public static void main(String[] args) throws Exception {
        vendingMachine.addInventory(new ProductInventory(Product.NAPIA_COLA, 5, 300));

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
                    try {
                        buyProduct(scan);
                    }
                    catch (ArithmeticException e) {
                        error = e;
                    }
                    catch (Exception e) {
                        error = e;
                    }
                }
                case EXIT   -> System.out.println("¡Hasta luego!");
                case NOOP   -> {}
            }
        } while (option != MenuOptions.EXIT);

        scan.close();
    }


    private static void buyProduct (Scanner scan) throws Exception, InputMismatchException {
        System.out.println("¿Qué producto quieres comprar?");
        System.out.println("   1) Plumbus");
        System.out.println("   2) Wumpa");
        System.out.println("   3) Napia Cola");

        Product product = null;

        do {
            try {
                System.out.print("> ");
                product = Product.fromValue(scan.nextInt());
                scan.nextLine();
            }
            catch (InputMismatchException e) {
                throw new InputMismatchException("Introduce un comando válido.");
            }
        } while (product == null);


        int round     = 0;
        int price     = vendingMachine.getPrice(product);
        int remaining = price;

        ArrayList<CoinEuro> coins = new ArrayList<CoinEuro>();

        do {
            clearScreen();
            System.out.println("Debes abonar " + price + " céntimos.");
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

        Thread.sleep(3000);
    }


    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

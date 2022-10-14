package com.tsystems;

import java.util.InputMismatchException;

public enum Product {
    PLUMBUS(1),
    WUMPA(2),
    NAPIA_COLA(3);

    private final int value;

    private Product(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Product fromValue(int value) {
        for (Product option : Product.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }

        throw new InputMismatchException("Invalid option");
    }
}

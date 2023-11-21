package com.github.angel.raa.modules.models;

import jakarta.validation.constraints.NotBlank;

//public enum Category {
//    @NotBlank(message = "Category name must not be blank")
//    Fruit("fruit")
//    , Vegetable, Meat, Seafood, Snack, Other
//}

public enum Category {
    FRUIT("Fruit"),
    VEGETABLE("Vegetable"),
    MEAT("Meat"),
    SEAFOOD("Seafood"),
    SNACK("Snack"),
    OTHER("Other");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (category.getValue().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}
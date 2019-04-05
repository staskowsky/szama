package pl.szama.product;

import lombok.Getter;
import lombok.Setter;

public enum Category {
    VEGETABLE("Warzywa"),
    FRUIT("Owoce"),
    MEAT("Mięso"),
    DAIRY("Nabiał"),
    WHEAT("Produkty zbożowe"),
    EGG("Jaja");

    @Getter
    @Setter
    private String name;

    Category(String name) {
        this.name = name;
    }
}

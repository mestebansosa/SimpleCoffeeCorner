package com.charlene.model;

public final class Item {
    public static enum ITEM_TYPE {
        BEVERAGE, SNACK, EXTRA, BONUS
    }

    public static enum ITEM_NAME {
        COFFEE_SMALL, COFFEE_MEDIUM, COFFEE_LARGE,
        BACON_ROLL, ORANGE_JUICE, EXTRA_MILK, FOAMED_MILK,
        SPECIAL_ROAST_COFFEE, BEVERAGE_FREE, EXTRA_FREE
    }

    private final String name;
    private final ITEM_TYPE type;
    private Double value;

    public Item(String name, ITEM_TYPE type, Double value) {
        super();
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public ITEM_TYPE getType() {
        return type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}

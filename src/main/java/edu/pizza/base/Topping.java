package edu.pizza.base;

import java.util.Objects;

public class Topping {
    private String name;
    private double price;

    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topping topping = (Topping) o;
        return Double.compare(topping.price, price) == 0 &&
                name.equals(topping.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}

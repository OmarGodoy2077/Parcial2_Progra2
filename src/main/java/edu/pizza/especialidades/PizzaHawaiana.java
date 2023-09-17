package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaHawaiana extends Pizza {

    public PizzaHawaiana() {
        super("Pizza Hawaiana");
        agregarIngredientes();
    }

    private void agregarIngredientes() {
        super.addTopping(new Topping("Jamón", 7.5));
        super.addTopping(new Topping("Piña", 5.5));
    }
}

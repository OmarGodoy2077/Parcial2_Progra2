package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaChilesNogada extends Pizza {

    public PizzaChilesNogada() {
        super("Pizza Chiles en Nogada");
        agregarIngredientes();
    }

    private void agregarIngredientes() {
        super.addTopping(new Topping("Chile Poblano", 8.5));
        super.addTopping(new Topping("Nogada", 10.0));
        super.addTopping(new Topping("Granada", 9.5));
    }
}

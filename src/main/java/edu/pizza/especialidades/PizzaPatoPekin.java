package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaPatoPekin extends Pizza {

    public PizzaPatoPekin() {
        super("Pizza Pato Pekin");
        agregarIngredientes();
    }

    private void agregarIngredientes() {
        super.addTopping(new Topping("Pato", 12.0));
        super.addTopping(new Topping("Salsa Hoisin", 6.5));
        super.addTopping(new Topping("Cebollines", 5.5));
    }
}

package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaCocoCamaron extends Pizza {

    public PizzaCocoCamaron() {
        super("Pizza Pizza Coco Camaron ");
        agregarIngredientes();
    }

    private void agregarIngredientes() {
        super.addTopping(new Topping("Camarones", 14.5));
        super.addTopping(new Topping("Coco Rallado", 8.0));
    }
}

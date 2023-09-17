package edu.pizza.base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private List<Topping> toppings;

    public Pizza(String name) {
        this.name = name;
        this.toppings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return "Pizza " + name + " con los siguientes ingredientes: " + toppings;
    }

    public void prepare() {
        List<String> steps = new ArrayList<>();
        steps.add("Preparando la base de la pizza...");
        steps.add("Agregando salsa...");
        for (Topping topping : toppings) {
            steps.add("Agregando " + topping.getName() + "...");
        }
        steps.add("Horneando pizza...");
        steps.add("¡La pizza está lista!");

        Timer timer = new Timer(3000, null);
        timer.addActionListener(new ActionListener() {
            int stepIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (stepIndex < steps.size()) {
                    // Display the message as a non-modal dialog
                    JOptionPane pane = new JOptionPane(steps.get(stepIndex), JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = pane.createDialog(null, "Pizza Preparation");
                    dialog.setModal(false);
                    dialog.setVisible(true);

                    // Create another Timer to close the message after 2.5 seconds
                    Timer closeTimer = new Timer(2500, e2 -> dialog.dispose());
                    closeTimer.setRepeats(false);
                    closeTimer.start();

                    stepIndex++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
}
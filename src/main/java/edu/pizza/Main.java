package edu.pizza;

import edu.formularios.frmPizza;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("frmPizza");

        frame.setContentPane(new frmPizza().getJpanelPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setSize(450, 550);


        frame.setResizable(false);

        frame.setVisible(true);
    }
}

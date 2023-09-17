package edu.formularios;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;
import edu.pizza.especialidades.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class frmPizza {
    private JPanel jpanelPrincipal;
    private JComboBox<Pizza> comboBoxPizzaEspecial;
    private JComboBox<Topping> comboBoxToppings;
    private JTextField txtPizza;
    private JButton btnAddIngrediente;
    private JLabel lblTotal;
    private JList<Topping> lista1;
    private JButton btnPreparar;
    private JRadioButton radioPequena;
    private JRadioButton radioMediana;
    private JRadioButton radioGrande;
    private ButtonGroup grupoTamanos = new ButtonGroup();

    private Pizza pizzaActual = null;

    public frmPizza() {
        initComponents();
        setupListeners();
    }

    private void initComponents() {
        radioPequena.addActionListener(e -> actualizarTotal());
        radioMediana.addActionListener(e -> actualizarTotal());
        radioGrande.addActionListener(e -> actualizarTotal());
        grupoTamanos.add(radioPequena);
        grupoTamanos.add(radioMediana);
        grupoTamanos.add(radioGrande);

        cargarPizzas();
        cargarToppings();
        actualizarTotal();

        lista1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println("Doble clic registrado"); // Verificar si el evento de doble clic se está capturando

                    Topping selectedTopping = lista1.getSelectedValue();
                    System.out.println("Topping seleccionado: " + selectedTopping); // Imprimir el topping seleccionado

                    if (selectedTopping != null) {
                        pizzaActual.removeTopping(selectedTopping);

                        // Verificar si el topping fue eliminado de la lista
                        if (!pizzaActual.getToppings().contains(selectedTopping)) {
                            System.out.println("El topping fue eliminado correctamente de la pizza");
                        } else {
                            System.out.println("El topping no fue eliminado de la pizza");
                        }

                        mostrarIngredientes(pizzaActual);
                        actualizarTotal();
                    }
                }
            }
        });
    }

    private void setupListeners() {
        comboBoxPizzaEspecial.addActionListener(e -> {
            pizzaActual = (Pizza) comboBoxPizzaEspecial.getSelectedItem();
            mostrarIngredientes(pizzaActual);
            actualizarTotal();
        });

        btnAddIngrediente.addActionListener(e -> {
            Topping toppingSeleccionado = (Topping) comboBoxToppings.getSelectedItem();
            pizzaActual.addTopping(toppingSeleccionado);
            mostrarIngredientes(pizzaActual);
            actualizarTotal();
        });


        btnPreparar.addActionListener(e -> {
            if (txtPizza.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese el nombre de la pizza.");
                return;
            }
            if (!radioPequena.isSelected() && !radioMediana.isSelected() && !radioGrande.isSelected()) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el tamaño de la pizza.");
                return;
            }

            if (pizzaActual != null) {
                Thread preparationThread = new Thread(() -> pizzaActual.prepare());
                preparationThread.start();

                // Show the first message (assuming it's about starting the preparation)
                JOptionPane pane1 = new JOptionPane("Comenzando...", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog1 = pane1.createDialog(null, "Preparacion de pizza");
                dialog1.setModal(false);
                dialog1.setVisible(true);

                // Create a Timer to close the first message and show the second message after 3 seconds
                Timer sequentialTimer = new Timer(3000, e1 -> {
                    dialog1.dispose();

                    // Show the second message "Your pizza is being prepared!"
                    JOptionPane pane2 = new JOptionPane("Tu Pizza esta siendo preparada", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog2 = pane2.createDialog(null, "Pizza Preparation");
                    dialog2.setModal(false);
                    dialog2.setVisible(true);

                    // Create another Timer to close the second message after 3 seconds
                    Timer closeTimer = new Timer(3000, e2 -> dialog2.dispose());
                    closeTimer.setRepeats(false);
                    closeTimer.start();
                });
                sequentialTimer.setRepeats(false);
                sequentialTimer.start();

            }
        });
    }

    private void mostrarIngredientes(Pizza pizza) {
        DefaultListModel<Topping> modeloLista = new DefaultListModel<>();
        for (Topping topping : pizza.getToppings()) {
            modeloLista.addElement(topping);
        }
        lista1.setModel(modeloLista);
    }

    private void actualizarTotal() {
        double precioBase = 0.0;
        if (radioPequena.isSelected()) {
            precioBase = 5.0;
        } else if (radioMediana.isSelected()) {
            precioBase = 7.0;
        } else if (radioGrande.isSelected()) {
            precioBase = 10.0;
        }

        double costoIngredientes = 0.0;
        if (pizzaActual != null) {
            for (Topping topping : pizzaActual.getToppings()) {
                costoIngredientes += topping.getPrice();
            }

        }
        double total = precioBase + costoIngredientes;
        lblTotal.setText("Precio Total: $" + String.format("%.2f", total));
    }

    private void cargarPizzas() {
        Pizza[] pizzas = {
                new ByYourselft(),
                new PizzaHawaiana(),
                new PizzaPatoPekin(),
                new PizzaChilesNogada(),
                new PizzaCocoCamaron()
        };
        comboBoxPizzaEspecial.setModel(new DefaultComboBoxModel<>(pizzas));
        pizzaActual = pizzas[0];
        mostrarIngredientes(pizzaActual);
    }

    private void cargarToppings() {
        Topping[] toppings = {
                new Topping("Champiñones", 5.1),
                new Topping("Tomate", 3.6),
                new Topping("Cebolla", 6.5),
                new Topping("Salchicha", 10.5),
                new Topping("Calamares", 11.55),

        };
        comboBoxToppings.setModel(new DefaultComboBoxModel<>(toppings));
    }

    public JPanel getJpanelPrincipal() {
        return jpanelPrincipal;
    }


    private void createUIComponents() {
        txtPizza = new JTextField();
        btnAddIngrediente = new JButton();

        jpanelPrincipal = new JPanel();

    }
}

package com.yulisa.centrodeadopcion.presentacion;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Único JFrame de la aplicación. Las pantallas son JPanel que se intercambian con CardLayout. */
public class VentanaPrincipal extends JFrame {

    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenedor = new JPanel(tarjetas);

    public VentanaPrincipal() {
        super("Centro de Adopción");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(720, 520));
        setContentPane(contenedor);
    }

    void agregarPantalla(Pantalla pantalla, VistaBase vista) {
        contenedor.add(vista, pantalla.name());
    }

    void mostrarPantalla(Pantalla pantalla) {
        tarjetas.show(contenedor, pantalla.name());
    }
}

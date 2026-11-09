package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** Pantalla inicial (antes MainWindow). Ya no abre sesión: solo lleva al login de cada rol. */
public class SeleccionRolPanel extends VistaBase {

    public SeleccionRolPanel(MenuController controller) {
        JButton btnIngresarComoCliente = new JButton("Soy cliente");
        JButton btnIngresarComoAdmin = new JButton("Soy administrador");
        btnIngresarComoCliente.addActionListener(e -> controller.irA(Pantalla.LOGIN_CLIENTE));
        btnIngresarComoAdmin.addActionListener(e -> controller.irA(Pantalla.LOGIN_ADMIN));

        JPanel encabezado = new JPanel(new BorderLayout(0, 6));
        encabezado.add(titulo("Bienvenido al sistema de adopción"), BorderLayout.NORTH);
        encabezado.add(new JLabel("Escoja su rol", SwingConstants.CENTER), BorderLayout.SOUTH);

        add(encabezado, BorderLayout.NORTH);
        add(columnaDeBotones(btnIngresarComoCliente, btnIngresarComoAdmin), BorderLayout.CENTER);
    }
}

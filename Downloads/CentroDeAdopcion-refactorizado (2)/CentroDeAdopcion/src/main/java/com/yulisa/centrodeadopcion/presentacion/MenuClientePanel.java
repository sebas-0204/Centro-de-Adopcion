package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import javax.swing.JButton;

public class MenuClientePanel extends VistaBase {

    public MenuClientePanel(MenuController controller) {
        JButton btnVerAnimales = new JButton("Ver animales");
        JButton btnIrAAdopcion = new JButton("Adoptar");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");

        btnVerAnimales.addActionListener(e -> controller.irA(Pantalla.LISTADO_ANIMALES));
        btnIrAAdopcion.addActionListener(e -> controller.irA(Pantalla.ADOPCION));
        btnCerrarSesion.addActionListener(e -> controller.cerrarSesion());

        add(titulo("Panel del cliente"), BorderLayout.NORTH);
        add(columnaDeBotones(btnVerAnimales, btnIrAAdopcion, btnCerrarSesion), BorderLayout.CENTER);
    }
}

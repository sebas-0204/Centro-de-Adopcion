package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import javax.swing.JButton;

public class MenuAdminPanel extends VistaBase {

    public MenuAdminPanel(MenuController controller) {
        JButton btnRegistrarAnimal = new JButton("Registrar animal");
        JButton btnVerAnimales = new JButton("Ver animales");
        JButton btnRegistrarTrabajador = new JButton("Registrar trabajador");
        JButton btnVerTrabajadores = new JButton("Ver trabajadores");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");

        btnRegistrarAnimal.addActionListener(e -> controller.irA(Pantalla.REGISTRO_ANIMAL));
        btnVerAnimales.addActionListener(e -> controller.irA(Pantalla.LISTADO_ANIMALES));
        btnRegistrarTrabajador.addActionListener(e -> controller.irA(Pantalla.REGISTRO_TRABAJADOR));
        btnVerTrabajadores.addActionListener(e -> controller.irA(Pantalla.LISTADO_TRABAJADORES));
        btnCerrarSesion.addActionListener(e -> controller.cerrarSesion());

        add(titulo("Panel de administración"), BorderLayout.NORTH);
        add(columnaDeBotones(btnRegistrarAnimal, btnVerAnimales,
                btnRegistrarTrabajador, btnVerTrabajadores, btnCerrarSesion), BorderLayout.CENTER);
    }
}

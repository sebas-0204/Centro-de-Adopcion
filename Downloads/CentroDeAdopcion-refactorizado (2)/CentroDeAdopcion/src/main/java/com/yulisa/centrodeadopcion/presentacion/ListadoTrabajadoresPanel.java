package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ListadoTrabajadoresPanel extends VistaBase {

    private final TrabajadorController controller;
    private final TrabajadorTableModel modelo = new TrabajadorTableModel();

    public ListadoTrabajadoresPanel(TrabajadorController controller) {
        this.controller = controller;
        JTable tblTrabajadores = new JTable(modelo);
        tblTrabajadores.setAutoCreateRowSorter(true);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> controller.volverAlMenu());

        add(titulo("Personal del centro"), BorderLayout.NORTH);
        add(new JScrollPane(tblTrabajadores), BorderLayout.CENTER);
        add(filaDeBotones(btnVolver), BorderLayout.SOUTH);
    }

    @Override
    protected void alMostrar() {
        modelo.setTrabajadores(controller.listar());
    }
}

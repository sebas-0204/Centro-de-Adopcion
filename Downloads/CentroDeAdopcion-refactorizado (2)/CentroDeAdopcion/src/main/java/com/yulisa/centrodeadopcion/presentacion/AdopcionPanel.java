package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/** Animales disponibles (antes VistaAdoptar). Adopta por el id de la fila, no por el nombre. */
public class AdopcionPanel extends VistaBase {

    private final AnimalesController controller;
    private final AnimalTableModel modelo = new AnimalTableModel();
    private final JTable tblAnimalesDisponibles = new JTable(modelo);

    public AdopcionPanel(AnimalesController controller) {
        this.controller = controller;
        tblAnimalesDisponibles.setAutoCreateRowSorter(true);
        tblAnimalesDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton btnAdoptar = new JButton("Adoptar");
        JButton btnVolver = new JButton("Volver");
        btnAdoptar.addActionListener(e -> {
            if (controller.adoptar(idSeleccionado(), this)) {
                recargar();
            }
        });
        btnVolver.addActionListener(e -> controller.volverAlMenu());

        add(titulo("Animales disponibles para adopción"), BorderLayout.NORTH);
        add(new JScrollPane(tblAnimalesDisponibles), BorderLayout.CENTER);
        add(filaDeBotones(btnAdoptar, btnVolver), BorderLayout.SOUTH);
    }

    @Override
    protected void alMostrar() {
        recargar();
    }

    private void recargar() {
        modelo.setAnimales(controller.listarDisponibles());
    }

    /** null si no hay fila seleccionada; el servicio responde con el mensaje adecuado. */
    private UUID idSeleccionado() {
        int fila = tblAnimalesDisponibles.getSelectedRow();
        if (fila < 0) {
            return null;
        }
        return modelo.getAnimal(tblAnimalesDisponibles.convertRowIndexToModel(fila)).id();
    }
}

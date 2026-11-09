package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.AnimalDTO;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/** Todos los animales (antes VistaAnimales). Al seleccionar uno se ven sus cuidados. */
public class ListadoAnimalesPanel extends VistaBase {

    private final AnimalesController controller;
    private final AnimalTableModel modelo = new AnimalTableModel();
    private final JTable tblAnimales = new JTable(modelo);
    private final JTextArea txaCuidados = new JTextArea(3, 40);

    public ListadoAnimalesPanel(AnimalesController controller) {
        this.controller = controller;
        tblAnimales.setAutoCreateRowSorter(true);
        tblAnimales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAnimales.getSelectionModel().addListSelectionListener(e -> mostrarCuidados());
        txaCuidados.setEditable(false);
        txaCuidados.setLineWrap(true);
        txaCuidados.setWrapStyleWord(true);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> controller.volverAlMenu());

        JPanel sur = new JPanel(new BorderLayout(0, 8));
        sur.add(new JScrollPane(txaCuidados), BorderLayout.CENTER);
        sur.add(filaDeBotones(btnVolver), BorderLayout.SOUTH);

        add(titulo("Animales del centro"), BorderLayout.NORTH);
        add(new JScrollPane(tblAnimales), BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);
    }

    @Override
    protected void alMostrar() {
        modelo.setAnimales(controller.listarTodos());
        txaCuidados.setText("Seleccione un animal para ver sus cuidados.");
    }

    private void mostrarCuidados() {
        int fila = tblAnimales.getSelectedRow();
        if (fila >= 0) {
            AnimalDTO a = modelo.getAnimal(tblAnimales.convertRowIndexToModel(fila));
            String descripcion = a.descripcion().isBlank() ? "" : a.descripcion() + "\n";
            txaCuidados.setText(descripcion + "Cuidados: " + a.descripcionCuidados());
        }
    }
}

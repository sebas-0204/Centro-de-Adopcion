package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.RegistroAnimalDTO;
import com.yulisa.centrodeadopcion.modelo.TipoAnimal;
import java.awt.BorderLayout;
import java.awt.Component;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

/**
 * Alta de animal (antes VistaRegistroAnimales).
 * El combo se llena con TipoAnimal.values(): una especie nueva aparece sola.
 * La fecha usa JSpinner (no texto libre) y ya no existe el combo "estado de adopción".
 */
public class RegistroAnimalPanel extends VistaBase {

    private final JComboBox<TipoAnimal> cmbTipoAnimal = new JComboBox<>();
    private final JTextField txtNombre = new JTextField(20);
    private final JTextField txtEdad = new JTextField(5);
    private final JTextField txtRaza = new JTextField(15);
    private final JSpinner spnFechaIngreso = new JSpinner(new SpinnerDateModel());
    private final JTextArea txaDescripcion = new JTextArea(3, 20);
    private final JCheckBox chkEntrenado = new JCheckBox("Entrenado");
    private final JCheckBox chkProtector = new JCheckBox("Protector");
    private final JCheckBox chkTieneDiscapacidad = new JCheckBox("Tiene alguna discapacidad");
    private final JTextField txtCuidadosEspeciales = new JTextField(20);

    public RegistroAnimalPanel(RegistroAnimalController controller) {
        DefaultComboBoxModel<TipoAnimal> tipos = new DefaultComboBoxModel<>();
        tipos.addElement(null);
        for (TipoAnimal tipo : TipoAnimal.values()) {
            tipos.addElement(tipo);
        }
        cmbTipoAnimal.setModel(tipos);
        cmbTipoAnimal.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice,
                                                          boolean seleccionado, boolean foco) {
                Object texto = (valor == null) ? "— Seleccione —" : valor;
                return super.getListCellRendererComponent(lista, texto, indice, seleccionado, foco);
            }
        });
        cmbTipoAnimal.addActionListener(e -> actualizarCamposPorEspecie());
        spnFechaIngreso.setEditor(new JSpinner.DateEditor(spnFechaIngreso, "dd/MM/yyyy"));
        txaDescripcion.setLineWrap(true);
        txaDescripcion.setWrapStyleWord(true);

        JButton btnRegistrar = new JButton("Registrar");
        JButton btnVolver = new JButton("Volver");
        btnRegistrar.addActionListener(e -> {
            if (controller.registrar(obtenerDatos(), this)) {
                limpiar();
            }
        });
        btnVolver.addActionListener(e -> controller.volverAlMenu());

        add(titulo("Registro de animal"), BorderLayout.NORTH);
        add(new Formulario()
                .campo("Tipo:", cmbTipoAnimal)
                .campo("Nombre:", txtNombre)
                .campo("Edad (años):", txtEdad)
                .campo("Raza:", txtRaza)
                .campo("Fecha de ingreso:", spnFechaIngreso)
                .campo("Descripción:", new JScrollPane(txaDescripcion))
                .fila(chkEntrenado)
                .fila(chkProtector)
                .fila(chkTieneDiscapacidad)
                .campo("Cuidados especiales:", txtCuidadosEspeciales), BorderLayout.CENTER);
        add(filaDeBotones(btnRegistrar, btnVolver), BorderLayout.SOUTH);
        actualizarCamposPorEspecie();
    }

    @Override
    protected void alMostrar() {
        limpiar();
    }

    private RegistroAnimalDTO obtenerDatos() {
        Date fecha = (Date) spnFechaIngreso.getValue();
        LocalDate fechaIngreso = (fecha == null) ? null
                : fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new RegistroAnimalDTO(
                (TipoAnimal) cmbTipoAnimal.getSelectedItem(),
                txtNombre.getText(),
                txtEdad.getText(),
                txtRaza.getText(),
                fechaIngreso,
                txaDescripcion.getText(),
                chkEntrenado.isSelected(),
                chkProtector.isSelected(),
                chkTieneDiscapacidad.isSelected(),
                txtCuidadosEspeciales.getText());
    }

    /** Habilita solo los campos que usa la especie elegida. */
    private void actualizarCamposPorEspecie() {
        TipoAnimal tipo = (TipoAnimal) cmbTipoAnimal.getSelectedItem();
        boolean esPerro = tipo == TipoAnimal.PERRO;
        boolean esGato = tipo == TipoAnimal.GATO;
        chkEntrenado.setEnabled(esPerro);
        chkProtector.setEnabled(esPerro);
        chkTieneDiscapacidad.setEnabled(esPerro);
        txtCuidadosEspeciales.setEnabled(esGato);
        if (!esPerro) {
            chkEntrenado.setSelected(false);
            chkProtector.setSelected(false);
            chkTieneDiscapacidad.setSelected(false);
        }
        if (!esGato) {
            txtCuidadosEspeciales.setText("");
        }
    }

    private void limpiar() {
        cmbTipoAnimal.setSelectedIndex(0);
        txtNombre.setText("");
        txtEdad.setText("");
        txtRaza.setText("");
        spnFechaIngreso.setValue(new Date());
        txaDescripcion.setText("");
        actualizarCamposPorEspecie();
    }
}

package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Paso 2 del registro (antes VistaFormularioInscripcion).
 * Los combos vacío/SÍ/NO pasan a JCheckBox: no hay opción vacía que se guarde como NO en silencio.
 */
public class InscripcionPanel extends VistaBase {

    private final JLabel lblCuenta = new JLabel();
    private final JTextField txtNombre = new JTextField(20);
    private final JTextField txtEdad = new JTextField(5);
    private final JTextField txtDocumento = new JTextField(15);
    private final JTextField txtContacto = new JTextField(20);
    private final JCheckBox chkIngresosPropios = new JCheckBox("Cuento con ingresos propios");
    private final JCheckBox chkViviendaPropia = new JCheckBox("Poseo vivienda propia");
    private final JCheckBox chkTuvoMascotas = new JCheckBox("He tenido mascotas antes");
    private final RegistroClienteController controller;

    public InscripcionPanel(RegistroClienteController controller) {
        this.controller = controller;
        JButton btnFinalizar = new JButton("Finalizar inscripción");
        JButton btnCancelar = new JButton("Cancelar");

        btnFinalizar.addActionListener(e -> {
            if (controller.finalizarInscripcion(
                    txtNombre.getText(), txtEdad.getText(), txtDocumento.getText(), txtContacto.getText(),
                    chkIngresosPropios.isSelected(), chkViviendaPropia.isSelected(),
                    chkTuvoMascotas.isSelected(), this)) {
                limpiar();
            }
        });
        btnCancelar.addActionListener(e -> {
            limpiar();
            controller.cancelar();
        });

        add(titulo("Formulario de inscripción"), BorderLayout.NORTH);
        add(new Formulario()
                .campo("Cuenta:", lblCuenta)
                .campo("Nombre:", txtNombre)
                .campo("Edad:", txtEdad)
                .campo("Documento:", txtDocumento)
                .campo("Contacto:", txtContacto)
                .fila(chkIngresosPropios)
                .fila(chkViviendaPropia)
                .fila(chkTuvoMascotas), BorderLayout.CENTER);
        add(filaDeBotones(btnFinalizar, btnCancelar), BorderLayout.SOUTH);
    }

    @Override
    protected void alMostrar() {
        limpiar();
        lblCuenta.setText(controller.hayCuentaPendiente() ? controller.getUsuarioPendiente() : "(sin cuenta)");
    }

    private void limpiar() {
        txtNombre.setText("");
        txtEdad.setText("");
        txtDocumento.setText("");
        txtContacto.setText("");
        chkIngresosPropios.setSelected(false);
        chkViviendaPropia.setSelected(false);
        chkTuvoMascotas.setSelected(false);
    }
}

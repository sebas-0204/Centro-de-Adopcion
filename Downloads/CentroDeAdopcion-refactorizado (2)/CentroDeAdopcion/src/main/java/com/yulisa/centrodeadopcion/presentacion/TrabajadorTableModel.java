package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.TrabajadorDTO;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TrabajadorTableModel extends AbstractTableModel {

    private static final String[] COLUMNAS = {"Nombre", "Edad", "Documento", "Contacto", "Cargo"};

    private List<TrabajadorDTO> filas = List.of();

    public void setTrabajadores(List<TrabajadorDTO> trabajadores) {
        this.filas = List.copyOf(trabajadores);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public String getColumnName(int columna) {
        return COLUMNAS[columna];
    }

    @Override
    public Class<?> getColumnClass(int columna) {
        return columna == 1 ? Integer.class : String.class;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        TrabajadorDTO t = filas.get(fila);
        return switch (columna) {
            case 0 -> t.nombre();
            case 1 -> t.edad();
            case 2 -> t.documento();
            case 3 -> t.contacto();
            case 4 -> t.cargo();
            default -> throw new IndexOutOfBoundsException(columna);
        };
    }
}

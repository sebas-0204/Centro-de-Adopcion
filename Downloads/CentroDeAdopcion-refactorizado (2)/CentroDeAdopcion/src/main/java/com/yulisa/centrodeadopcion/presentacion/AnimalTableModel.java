package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.AnimalDTO;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/** Tabla de animales tipada: cada fila es un AnimalDTO, así se recupera el id al seleccionar. */
public class AnimalTableModel extends AbstractTableModel {

    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String[] COLUMNAS = {"Nombre", "Tipo", "Raza", "Edad", "Ingreso", "Adoptado"};

    private List<AnimalDTO> filas = List.of();

    public void setAnimales(List<AnimalDTO> animales) {
        this.filas = List.copyOf(animales);
        fireTableDataChanged();
    }

    public AnimalDTO getAnimal(int filaModelo) {
        return filas.get(filaModelo);
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
        return columna == 3 ? Integer.class : String.class;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        AnimalDTO a = filas.get(fila);
        return switch (columna) {
            case 0 -> a.nombre();
            case 1 -> a.tipo();
            case 2 -> a.raza();
            case 3 -> a.edad();
            case 4 -> a.fechaIngreso().format(FECHA);
            case 5 -> a.adoptado() ? "Sí" : "No";
            default -> throw new IndexOutOfBoundsException(columna);
        };
    }
}

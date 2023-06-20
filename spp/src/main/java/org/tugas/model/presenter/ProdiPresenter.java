package org.tugas.model.presenter;

import org.tugas.model.entity.Prodi;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProdiPresenter extends AbstractTableModel {
    private final List<Prodi> prodiList;
    private final String[] columns = {"Prodi", "Price"};

    public ProdiPresenter(List<Prodi> prodiList) {
        this.prodiList = prodiList;
    }

    @Override
    public int getRowCount() {
        return prodiList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodi prodi = prodiList.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> prodi.getProdi();
            case 1 -> prodi.getPrice();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
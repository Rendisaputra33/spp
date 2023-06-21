package org.tugas.model.presenter;

import org.tugas.model.entity.Transaction;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class TransactionPresenter extends AbstractTableModel {
    private final List<Transaction> transactionList;
    private final String[] columns = {"NIM", "Nama", "Prodi", "Kode Transaksi", "Semester", "Jumlah", "Tanggal Bayar"};

    public TransactionPresenter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public int getRowCount() {
        return transactionList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction transaction = transactionList.get(rowIndex);

        switch (columnIndex) {
            case 0 -> {
                return transaction.getNim();
            }
            case 1 -> {
                return transaction.getNama();
            }
            case 2 -> {
                return transaction.getProdi();
            }
            case 3 -> {
                return transaction.getCodeTransaction();
            }
            case 4 -> {
                return transaction.getSemester();
            }
            case 5 -> {
                return transaction.getAmount();
            }
            case 6 -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                return dateFormat.format(transaction.getPaidOfAt());
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}

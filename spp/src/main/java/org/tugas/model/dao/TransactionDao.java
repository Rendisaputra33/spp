package org.tugas.model.dao;

import org.tugas.contract.Dao;
import org.tugas.model.entity.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao implements Dao<Transaction> {
    final Connection connection;

    public TransactionDao(Connection connection) {
        this.connection = connection;
    }

    public List<Transaction> getTransactions() {
        List<Transaction> list = new ArrayList<Transaction>();

        try {
            var stmt = connection.prepareStatement("SELECT p.*, m.nama, m.prodi FROM pembayaran p JOIN mahasiswa m on m.nim = p.nim");
            toList(list, stmt.executeQuery());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return list;
    }

    @Override
    public void toList(List<Transaction> list, ResultSet result) throws SQLException {
        while (result.next()) {
            list.add(Transaction.builder()
                    .prodi(result.getString("prodi"))
                    .nim(result.getString("nim"))
                    .amount(result.getDouble("amount"))
                    .nama(result.getString("nama"))
                    .codeTransaction(result.getString("kode_transaksi"))
                    .semester(result.getInt("semester"))
                    .paidOfAt(result.getDate("paid_of_)at"))
                    .build());
        }
    }
}

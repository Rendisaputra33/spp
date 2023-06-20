package org.tugas.model.dao;

import org.tugas.contract.Dao;
import org.tugas.model.entity.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransactionDao implements Dao<Transaction> {
    final Connection connection;

    public TransactionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void toList(List<Transaction> list, ResultSet result) throws SQLException {

    }
}

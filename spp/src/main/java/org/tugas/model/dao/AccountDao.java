package org.tugas.model.dao;

import org.tugas.contract.Dao;
import org.tugas.model.entity.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDao implements Dao<Account> {
    final Connection connection;

    public AccountDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void toList(List<Account> list, ResultSet result) throws SQLException {
        
    }
}

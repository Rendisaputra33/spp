package org.tugas.model.dao;

import org.tugas.contract.Dao;
import org.tugas.model.entity.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao implements Dao<Account> {
    final Connection connection;

    public AccountDao(Connection connection) {
        this.connection = connection;
    }

    public List<Account> getAccounts() {
        var list = new ArrayList<Account>();

        try {
            var stmt = connection.prepareStatement("SELECT * FROM akun");
            toList(list, stmt.executeQuery());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return list;
    }

    public Account getAccount(String username) {
        var list = new ArrayList<Account>();

        try {
            var stmt = connection.prepareStatement("SELECT * FROM akun WHERE username = ?");
            stmt.setString(1, username);
            toList(list, stmt.executeQuery());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return list.size() < 1 ? null : list.get(0);
    }

    public boolean deleteAccount(Integer id) {

        try {
            var stmt = connection.prepareStatement("DELETE FROM akun WHERE id  = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public void toList(List<Account> list, ResultSet result) throws SQLException {
        while (result.next()) {
            list.add(Account.builder()
                    .id(result.getInt("id"))
                    .username(result.getString("username"))
                    .password(result.getString("password"))
                    .build());
        }
    }
}

package org.tugas.model.dao;

import org.tugas.contract.Dao;
import org.tugas.model.entity.Prodi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdiDao implements Dao<Prodi> {
    final Connection connection;

    public ProdiDao(Connection connection) {
        this.connection = connection;
    }

    public List<Prodi> getListProdi() {
        List<Prodi> list = new ArrayList<Prodi>();

        try {
            var stmt = connection.prepareStatement("SELECT * FROM prodi");
            toList(list, stmt.executeQuery());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return list;
    }

    public Prodi getProdi(String prodi) {
        List<Prodi> list = new ArrayList<Prodi>();

        try {
            var stmt = connection.prepareStatement("SELECT * FROM prodi WHERE prodi = ?");
            stmt.setString(1, prodi);
            toList(list, stmt.executeQuery());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return list.size() < 1 ? null : list.get(0);
    }

    public boolean createProdi(Prodi prodi) {
        try {
            var stmt = connection.prepareStatement("INSERT INTO prodi (prodi, price) VALUES (?, ?)");
            stmt.setString(1, prodi.getProdi());
            stmt.setDouble(2, prodi.getPrice());
            return stmt.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    public boolean deleteProdi(String prodi) {
        try {
            var stmt = connection.prepareStatement("DELETE FROM prodi WHERE prodi = ?");
            stmt.setString(1, prodi);
            return stmt.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public void toList(List<Prodi> list, ResultSet result) throws SQLException {
        while (result.next()) {
            Prodi.builder()
                    .prodi(result.getString("prodi"))
                    .price(result.getDouble("price"))
                    .build();
        }
    }
}

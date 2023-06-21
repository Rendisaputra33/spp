package org.tugas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spp", "root", "");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }

        return connection;
    }
}

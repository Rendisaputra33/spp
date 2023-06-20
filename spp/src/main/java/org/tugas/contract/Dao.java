package org.tugas.contract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    void toList(List<T> list, ResultSet result) throws SQLException;
}

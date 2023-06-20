package org.tugas.model.dao;

import org.tugas.contract.Dao;
import org.tugas.model.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {
    private final Connection connection;

    public StudentDao(Connection connection) {
        this.connection = connection;
    }

    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<Student>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM mahasiswa");
            ResultSet result = stmt.executeQuery();
            toList(studentList, result);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return studentList;
    }

    public boolean createStudent(Student student) {
        try {
            var stmt = connection.prepareStatement("INSERT INTO mahasiswa (nim, nama, prodi, semester) VALUES(?, ?, ?, ?)");
            stmt.setString(1, student.getNim());
            stmt.setString(2, student.getNama());
            stmt.setString(3, student.getProdi());
            stmt.setInt(4, student.getSemester());
            return stmt.executeUpdate() > 0;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return false;
    }

    @Override
    public void toList(List<Student> list, ResultSet result) throws SQLException {
        while (result.next()) {
            list.add(Student.builder()
                    .nama(result.getString("nama"))
                    .id(result.getInt("id"))
                    .prodi(result.getString("prodi"))
                    .semester(result.getInt("semester"))
                    .build());
        }
    }
}

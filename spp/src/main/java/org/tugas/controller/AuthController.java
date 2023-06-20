package org.tugas.controller;

import org.tugas.contract.Controller;
import org.tugas.model.dao.AccountDao;
import org.tugas.utils.Database;
import org.tugas.views.Login;
import org.tugas.views.View;

import javax.swing.*;

public class AuthController implements Controller {
    final AccountDao accountDao = new AccountDao(Database.getConnection());
    final Login view;

    public AuthController(Login view) {
        this.view = view;
    }

    @Override
    public void registerEvents() {
        view.getBtnLogin().addActionListener(e -> {
            var username = view.getUsername().getText();
            var password = view.getPassword().getText();

            var account = accountDao.getAccount(username);

            if (account == null) {
                JOptionPane.showMessageDialog(view, "Username/password salah");
                return;
            }

            if (account.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(view, "Login berhasil");
                view.dispose();
                var mahasiswa = new View();
                mahasiswa.setResizable(false);
                mahasiswa.setLocationRelativeTo(null);
                mahasiswa.setVisible(true);
            }
        });
    }
}

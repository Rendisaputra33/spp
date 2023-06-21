package org.tugas.controller;

import org.tugas.contract.Controller;
import org.tugas.model.dao.StudentDao;
import org.tugas.model.dao.TransactionDao;
import org.tugas.model.entity.Student;
import org.tugas.model.entity.Transaction;
import org.tugas.utils.Database;
import org.tugas.utils.DateUtil;
import org.tugas.utils.StringUtil;
import org.tugas.views.PembayaranView;
import org.tugas.views.TambahPembayaranView;

import java.sql.Date;

public class AddTransactionController implements Controller {
    final TransactionDao transactionDao = new TransactionDao(Database.getConnection());
    final StudentDao studentDao = new StudentDao(Database.getConnection());
    final TambahPembayaranView view;
    final Transaction transaction = Transaction.builder().build();

    public AddTransactionController(TambahPembayaranView view) {
        this.view = view;
    }

    @Override
    public void registerEvents() {
        load();

        view.getBack().addActionListener(e -> {
            view.dispose();
            var dest = new PembayaranView();
            dest.setVisible(true);
        });

        view.getSimpan().addActionListener(e -> {
            transaction.setAmount(Double.valueOf(view.getTotal().getText()));
            transactionDao.createTransaction(transaction);
            view.dispose();
            new PembayaranView().setVisible(true);
        });

        view.getMahasiswa().addActionListener(e -> {
            var student = (Student) view.getMahasiswa().getSelectedItem();
            view.getNim().setText(student.getNim());
            view.getSemester().setText(student.getSemester().toString());
            transaction.setNim(student.getNim());
            transaction.setSemester(student.getSemester());
        });
    }

    void load() {
        transaction.setCodeTransaction(StringUtil.random(5).toUpperCase());
        transaction.setPaidOfAt(new Date(new java.util.Date().getTime()));
        view.getTanggal().setText("TANGGAL : " + DateUtil.getNow());
        view.getKode().setText("KODE PEMBAYARAN : " + transaction.getCodeTransaction());
        view.getMahasiswa().removeAllItems();
        studentDao.getStudents().forEach(student -> {
            view.getMahasiswa().addItem(student);
        });
    }
}

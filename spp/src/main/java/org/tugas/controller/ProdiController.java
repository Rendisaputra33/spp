package org.tugas.controller;

import org.tugas.contract.Controller;
import org.tugas.model.dao.ProdiDao;
import org.tugas.model.entity.Prodi;
import org.tugas.model.presenter.ProdiPresenter;
import org.tugas.utils.Database;
import org.tugas.views.ProdiView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProdiController implements Controller {
    final ProdiDao prodiDao = new ProdiDao(Database.getConnection());
    final ProdiView view;
    final Prodi prodi = Prodi.builder().build();

    public ProdiController(ProdiView view) {
        this.view = view;
    }

    @Override
    public void registerEvents() {
        load();

        GeneralController.toProdi(view.getProdi(), view);
        GeneralController.toStudent(view.getMahasiswa(), view);
        GeneralController.toTransaction(view.getPembayaran(), view);
        GeneralController.toReport(view.getLaporan(), view);

        view.getSave().addActionListener(e -> {
            var name = view.getProdiInp().getText();
            var ukt = view.getUkt().getText();

            prodi.setProdi(name);
            prodi.setPrice(Double.valueOf(ukt));

            if (prodiDao.createProdi(prodi)) {
                JOptionPane.showMessageDialog(view, "Sukses menambah");
                load();
                resetInput();
            } else {
                JOptionPane.showMessageDialog(view, "Terjadi kesalahan");
            }
        });

        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var row = view.getTable().getSelectedRow();
                var model = (ProdiPresenter) view.getTable().getModel();
                var data = model.getProdi(row);
                prodi.setPrice(data.getPrice());
                prodi.setProdi(data.getProdi());
                mapEntityToInput(data);
            }
        });

        view.getSave().addActionListener(e -> {
            mapInputToEntity();
            if (prodiDao.createProdi(prodi)) {
                JOptionPane.showMessageDialog(view, "Berhasil menyimpan");
                load();
            } else {
                JOptionPane.showMessageDialog(view, "Gagal menyimpan");
            }
        });

        view.getEdit().addActionListener(e -> {
            mapInputToEntity();
            if (prodiDao.updateProdi(prodi)) {
                JOptionPane.showMessageDialog(view, "Berhasil menyimpan");
                load();
            }else {
                JOptionPane.showMessageDialog(view, "Gagal menyimpan");
            }
        });

        view.getHapus().addActionListener(e -> {
            if (prodiDao.deleteProdi(prodi.getProdi())) {
                JOptionPane.showMessageDialog(view, "Berhasil menghapus");
                load();
            }else {
                JOptionPane.showMessageDialog(view, "Gagal menghapus");
            }
        });
    }

    void resetInput() {
        view.getProdiInp().setText("");
        view.getUkt().setText("");
        prodi.setProdi(null);
        prodi.setPrice(null);
    }

    void mapInputToEntity() {
        String name = view.getProdiInp().getText();
        String ukt = view.getUkt().getText();

        prodi.setPrice(Double.valueOf(ukt));
        prodi.setProdi(name);
    }

    void mapEntityToInput(Prodi prodi) {
        view.getProdiInp().setText(prodi.getProdi());
        view.getUkt().setText(prodi.getPrice().toString());
    }

    void load() {
        var data = prodiDao.getListProdi();
        var model = new ProdiPresenter(data);
        view.getTable().setModel(model);
    }
}

package org.tugas.controller;

import org.tugas.contract.Controller;
import org.tugas.model.dao.ProdiDao;
import org.tugas.model.entity.Prodi;
import org.tugas.model.presenter.ProdiPresenter;
import org.tugas.utils.Database;
import org.tugas.views.ProdiView;

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

        view.getSave().addActionListener(e -> {
            var name = view.getProdiInp().getText();
            var ukt = view.getUkt().getText();

            prodi.setProdi(name);
            prodi.setPrice(Double.valueOf(ukt));
        });
    }

    void load() {
        var data = prodiDao.getListProdi();
        var model = new ProdiPresenter(data);
        view.getTable().setModel(model);
    }
}

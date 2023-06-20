package org.tugas.controller;

import org.tugas.contract.Controller;
import org.tugas.model.dao.TransactionDao;
import org.tugas.model.presenter.TransactionPresenter;
import org.tugas.utils.Database;
import org.tugas.views.PembayaranView;

public class TransactionController implements Controller {
    final TransactionDao transactionDao = new TransactionDao(Database.getConnection());
    final PembayaranView view;

    public TransactionController(PembayaranView view) {
        this.view = view;
    }

    @Override
    public void registerEvents() {
        load();

        view.getTambah().addActionListener(e -> {});
    }


    void load() {
        var data = transactionDao.getTransactions();
        var model = new TransactionPresenter(data);
        view.getTable().setModel(model);
    }
}

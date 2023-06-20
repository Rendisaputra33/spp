package org.tugas.controller;

import org.tugas.contract.Controller;
import org.tugas.model.dao.TransactionDao;
import org.tugas.utils.Database;

public class TransactionController implements Controller {
    final TransactionDao transactionDao = new TransactionDao(Database.getConnection());

    @Override
    public void registerEvents() {

    }
}

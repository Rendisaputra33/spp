package org.tugas.controller;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.tugas.views.Laporan;
import org.tugas.views.PembayaranView;
import org.tugas.views.ProdiView;
import org.tugas.views.View;

public class GeneralController {
    public static void toProdi(JButton button, JFrame frame) {
        button.addActionListener(e -> {
            frame.dispose();
            var dest = new ProdiView();
            dest.setVisible(true);
        });
    }

    public static void toTransaction(JButton button, JFrame frame) {
        button.addActionListener(e -> {
            frame.dispose();
            var dest = new PembayaranView();
            dest.setVisible(true);
        });
    }

    public static void toStudent(JButton button, JFrame frame) {
        button.addActionListener(e -> {
            frame.dispose();
            var dest = new View();
            dest.setVisible(true);
        });
    }

    public static void toReport(JButton button, JFrame frame) {
        button.addActionListener(e -> {
            frame.dispose();
            var dest = new Laporan();
            dest.setVisible(true);
        });
    }
}

package org.example.uaptest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class BookingListFrame extends JFrame {

    public BookingListFrame(String role) {
        setTitle("List Booking");
        setSize(750, 400);
        setLocationRelativeTo(null);

        List<BookingModel> list = BookingStorage.load();

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Nama", "HP", "Tanggal", "Jam", "Lapangan"}, 0
        );

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.getTableHeader().setBackground(new Color(225, 232, 225));
        table.getTableHeader().setForeground(Color.WHITE);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (BookingModel b : list) {
                model.addRow(new Object[]{
                        b.getNama(),
                        b.getHp(),
                        b.getTanggal().format(formatter), // FIX
                        b.getJam(),
                        b.getLapangan()
                });
            }
        };
        refresh.run();

        JButton sort = new JButton("Sort Tanggal");
        JButton edit = new JButton("Edit");
        JButton del  = new JButton("Hapus");

        sort.addActionListener(e -> {
            list.sort(Comparator.comparing(BookingModel::getTanggal));
            refresh.run();
        });

        edit.addActionListener(e -> {
            if (!role.equals("ADMIN")) return;
            int r = table.getSelectedRow();
            if (r >= 0) {
                new BookingFormFrame(list, list.get(r), r).setVisible(true);
            }
        });

        del.addActionListener(e -> {
            if (!role.equals("ADMIN")) return;
            int r = table.getSelectedRow();
            if (r >= 0) {
                list.remove(r);
                BookingStorage.save(list);
                refresh.run();
            }
        });

        JPanel top = new JPanel();
        top.add(sort);

        if (role.equals("ADMIN")) {
            top.add(edit);
            top.add(del);
        }

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}

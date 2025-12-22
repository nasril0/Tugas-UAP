package org.example.uaptest;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookingFormFrame extends JFrame {

    public BookingFormFrame(List<BookingModel> list, BookingModel data, int index) {

        Color GREEN = new Color(7, 248, 7);
        Color LIGHT = new Color(245,255,245);

        setTitle(data == null ? "Tambah Booking" : "Edit Booking");
        setSize(380,330);
        setLocationRelativeTo(null);

        JTextField nama = new JTextField();
        JTextField hp   = new JTextField();
        JTextField tgl  = new JTextField();

        String[] JAM_SLOT = {
                "08:00 - 09:00",
                "09:00 - 10:00",
                "10:00 - 11:00",
                "11:00 - 12:00",
                "13:00 - 14:00",
                "14:00 - 15:00",
                "15:00 - 16:00",
                "16:00 - 17:00",
                "19:00 - 20:00",
                "20:00 - 21:00",
                "21:00 - 22:00"
        };
        JComboBox<String> jam = new JComboBox<>(JAM_SLOT);

        JComboBox<String> lap = new JComboBox<>(
                new String[]{"Lapangan A", "Lapangan B"}
        );

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (data != null) {
            nama.setText(data.getNama());
            hp.setText(data.getHp());
            tgl.setText(data.getTanggal().format(f));
            jam.setSelectedItem(data.getJam());
            lap.setSelectedItem(data.getLapangan());
        }

        JButton save = new JButton(data == null ? "Simpan" : "Update");
        save.setBackground(GREEN);
        save.setForeground(Color.black);

        save.addActionListener(e -> {

            if (nama.getText().trim().isEmpty() ||
                    hp.getText().trim().isEmpty() ||
                    tgl.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Semua field wajib diisi",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (!hp.getText().matches("\\d+")) {
                JOptionPane.showMessageDialog(
                        this,
                        "No HP harus berupa angka",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {
                BookingModel b = new BookingModel(
                        nama.getText(),
                        hp.getText(),
                        LocalDate.parse(tgl.getText(), f),
                        jam.getSelectedItem().toString(),
                        lap.getSelectedItem().toString()
                );

                if (data == null) list.add(b);
                else list.set(index, b);

                BookingStorage.save(list);
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tanggal harus format dd-MM-yyyy",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        JPanel p = new JPanel(new GridLayout(6,2,10,10));
        p.setBackground(LIGHT);
        p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        p.add(new JLabel("Nama"));
        p.add(nama);

        p.add(new JLabel("No HP"));
        p.add(hp);

        p.add(new JLabel("Tanggal"));
        p.add(tgl);

        p.add(new JLabel("Jam"));
        p.add(jam);

        p.add(new JLabel("Lapangan"));
        p.add(lap);

        p.add(new JLabel());
        p.add(save);

        add(p);
    }
}

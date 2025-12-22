package org.example.uaptest;

import javax.swing.*;
import java.util.List;

public class BookingReportFrame extends JFrame {

    public BookingReportFrame() {
        setTitle("Laporan Booking");
        setSize(300,200);
        setLocationRelativeTo(null);

        List<BookingModel> list = BookingStorage.load();

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText(
                "LAPORAN BOOKING\n" +
                        "====================\n" +
                        "Total Booking: " + list.size()
        );

        add(area);
    }
}

package org.example.uaptest;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {

    Color GREEN = new Color(63, 234, 143);
    Color LIGHT = new Color(245,255,245);

    public DashboardFrame(String role) {
        setTitle("Dashboard");
        setSize(400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        List<BookingModel> list = BookingStorage.load();

        JPanel panel = new JPanel(new GridLayout(0,1,15,15));
        panel.setBackground(LIGHT);
        panel.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));

        JLabel title = new JLabel(
                role.equals("ADMIN") ? "DASHBOARD ADMIN" : "DASHBOARD CUSTOMER",
                JLabel.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(GREEN);

        JButton input = new JButton("Input Booking");
        JButton listBtn = new JButton("List Booking");
        JButton report = new JButton("Laporan");
        JButton logout = new JButton("Logout");

        for (JButton b : new JButton[]{input, listBtn, report}) {
            b.setBackground(GREEN);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Arial", Font.BOLD, 13));
        }
        logout.setBackground(Color.RED);
        logout.setForeground(Color.WHITE);

        input.addActionListener(e ->
                new BookingFormFrame(list, null, -1).setVisible(true)
        );

        listBtn.addActionListener(e ->
                new BookingListFrame(role).setVisible(true)
        );

        report.addActionListener(e ->
                new BookingReportFrame().setVisible(true)
        );

        logout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        panel.add(title);
        panel.add(input);
        panel.add(listBtn);

        if (role.equals("ADMIN")) {
            panel.add(report);
        }

        panel.add(logout);

        add(panel);
    }
}

package org.example.uaptest;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    Color GREEN = new Color(113, 212, 214);
    Color LIGHT = new Color(245,255,245);

    public LoginFrame() {
        setTitle("Football Arena Login");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel main = new JPanel(new GridBagLayout());
        main.setBackground(LIGHT);

        JPanel box = new JPanel(new GridLayout(7,1,10,10));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));

        JLabel title = new JLabel("FOOTBALL ARENA", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(GREEN);

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();

        JButton admin = new JButton("Login Admin");
        JButton guest = new JButton("Login Customer");

        admin.setBackground(GREEN);
        admin.setForeground(Color.WHITE);
        guest.setBackground(Color.DARK_GRAY);
        guest.setForeground(Color.WHITE);

        admin.addActionListener(e -> {
            if (user.getText().equals("admin") &&
                    new String(pass.getPassword()).equals("admin123")) {
                new DashboardFrame("ADMIN").setVisible(true);
                dispose();
            } else JOptionPane.showMessageDialog(this,"Login gagal");
        });

        guest.addActionListener(e -> {
            new DashboardFrame("CUSTOMER").setVisible(true);
            dispose();
        });

        box.add(title);
        box.add(new JLabel("Username"));
        box.add(user);
        box.add(new JLabel("Password"));
        box.add(pass);
        box.add(admin);
        box.add(guest);

        main.add(box);
        add(main);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}

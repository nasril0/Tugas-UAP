package org.example.uaptest;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BookingStorage {

    private static final String FILE = "football_data.txt";
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static List<BookingModel> load() {
        List<BookingModel> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(";");
                list.add(new BookingModel(
                        d[0], d[1],
                        LocalDate.parse(d[2], FORMAT),
                        d[3], d[4]
                ));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal membaca data");
        }
        return list;
    }

    public static void save(List<BookingModel> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (BookingModel b : list) {
                bw.write(
                        b.getNama() + ";" +
                                b.getHp() + ";" +
                                b.getTanggal().format(FORMAT) + ";" +
                                b.getJam() + ";" +
                                b.getLapangan()
                );
                bw.newLine();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data");
        }
    }
}

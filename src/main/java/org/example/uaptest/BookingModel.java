package org.example.uaptest;

import java.time.LocalDate;

public class BookingModel {
    private String nama, hp, jam, lapangan;
    private LocalDate tanggal;

    public BookingModel(String nama, String hp, LocalDate tanggal, String jam, String lapangan) {
        this.nama = nama;
        this.hp = hp;
        this.tanggal = tanggal;
        this.jam = jam;
        this.lapangan = lapangan;
    }

    public String getNama() { return nama; }
    public String getHp() { return hp; }
    public LocalDate getTanggal() { return tanggal; }
    public String getJam() { return jam; }
    public String getLapangan() { return lapangan; }
}

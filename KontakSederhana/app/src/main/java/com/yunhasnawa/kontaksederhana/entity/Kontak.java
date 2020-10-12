package com.yunhasnawa.kontaksederhana.entity;

public class Kontak // Nama tabel: kontak
{
    private int id; // Nama kolom id
    private String nama; // Nama kolom nama
    private String nomorTelepon; // Nama kolom nomor_telepon

    public Kontak()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
}

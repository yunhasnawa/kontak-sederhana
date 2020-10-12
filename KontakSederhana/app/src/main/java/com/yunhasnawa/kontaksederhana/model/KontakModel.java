package com.yunhasnawa.kontaksederhana.model;

import android.content.Context;
import android.database.Cursor;

import com.yunhasnawa.kontaksederhana.entity.Kontak;
import com.yunhasnawa.kontaksederhana.helper.DbHelper;

import java.util.ArrayList;

public class KontakModel // Untuk meletakkan operasi baca/tulis ke tabel kontak
{
    private Context context;
    private DbHelper db;

    public KontakModel(Context context)
    {
        this.context = context;
        this.db = new DbHelper(this.context);
    }

    public void tambahKontak(Kontak k)
    {
        String nama = k.getNama();
        String nomorTelepon = k.getNomorTelepon();

        // Rangkai ke dalam SQL
        // Contoh: INSERT INTO kontak (nama, nomor_telepon) VALUES ('Yoppy', '085-123-456')
        String sql = "INSERT INTO kontak (nama, nomor_telepon) VALUES ('" + nama + "', '" + nomorTelepon + "')";

        // Langsung lempar ke dbHelper
        this.db.tulisData(sql);
    }

    public void hapusKontak(Kontak k)
    {
        // Buat SQL untuk hapus
        String sql = "DELETE FROM kontak WHERE id = '" + k.getId() + "'";

        // Panggil db.tulisData(sql)
        db.tulisData(sql);
    }

    public void perbaruiKontak(Kontak k)
    {
        int id = k.getId();
        String nama = k.getNama();
        String nomorTelepon = k.getNomorTelepon();

        // Buat SQL untuk update
        String sql = "UPDATE kontak SET nama = '" + nama + "', nomor_telepon = '" + nomorTelepon + "' WHERE id = '" + id + "'";

        // Tulis ke DB
        db.tulisData(sql);
    }

    public ArrayList<Kontak> ambilSemuaKontak()
    {
        String sql = "SELECT * FROM kontak";

        Cursor c = this.db.bacaData(sql);

        // Ubah cursor menjadi ArrayList
        ArrayList<Kontak> hasil = new ArrayList<>();

        if(c.getCount() < 1)
            return hasil;

        c.moveToFirst(); // Pasang di baris ke-1

        do {
            // Mengambil data dari kolom-kolom di tabel sesuai URUTANNYA.
            // Urutannya --> Db Helper saat kita membuat tabel kontak
            int id = c.getInt(0); // Kolom ke-0 INTEGER
            String nama = c.getString(1); // Kolom ke-1 VARCHAR
            String nomorTelepon = c.getString(2); // Kolom ke-2 VARCHAR

            Kontak k = new Kontak();
            k.setId(id);
            k.setNama(nama);
            k.setNomorTelepon(nomorTelepon);

            // Setiap kontak yang didapat dimasukkan ke ArrayList hasil;
            hasil.add(k);
        }
        while(c.moveToNext());

        return hasil;
    }

    // untuk mencari kontak berdasarkan ID
    public Kontak cariBerdasarkanId(int id)
    {
        String sql = "SELECT * FROM kontak WHERE id = '" + id + "'";

        Cursor c = this.db.bacaData(sql);

        if(c.getCount() > 0)
        {
            c.moveToFirst();

            String nama = c.getString(1);
            String nomorTelepon = c.getString(2);

            Kontak k = new Kontak();
            k.setId(id);
            k.setNama(nama);
            k.setNomorTelepon(nomorTelepon);

            return k;
        }

        return null;
    }
}

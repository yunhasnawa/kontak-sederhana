package com.yunhasnawa.kontaksederhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yunhasnawa.kontaksederhana.entity.Kontak;
import com.yunhasnawa.kontaksederhana.model.KontakModel;

public class TambahKontakActivity extends AppCompatActivity
{
    private EditText edtNama, edtNomorTelepon;
    private Button btnHapus;

    private Kontak kontakTerpilih;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kontak);

        this.edtNama = this.findViewById(R.id.edt_nama);
        this.edtNomorTelepon = this.findViewById(R.id.edt_nomor_telepon);
        this.btnHapus = this.findViewById(R.id.btn_hapus);

        // Cek apakah ada Extra di intentnya.
        Intent intentTitipan = this.getIntent();
        int kontakId = intentTitipan.getIntExtra("kontakId", -1);

        // Apabila ada maka ambil data kontak dari DB sesuai dengan Id yang ada di Extra-nya Intent.
        if(kontakId != -1)
        {
            // Ambil dari database..
            this.muatData(kontakId);
        }

        this.sesuaikanTampilan();
    }

    private void sesuaikanTampilan()
    {
        if(this.kontakTerpilih == null)
            this.btnHapus.setVisibility(View.GONE);
        else
            this.btnHapus.setVisibility(View.VISIBLE);
    }

    private void muatData(int kontakId)
    {
        KontakModel mKontak = new KontakModel(this);

        Kontak k = mKontak.cariBerdasarkanId(kontakId);

        this.edtNama.setText(k.getNama());
        this.edtNomorTelepon.setText(k.getNomorTelepon());

        this.kontakTerpilih = k;
    }

    public void btnSimpan_onClick(View view)
    {
        // Ambil data dari komponen
        String nama = this.edtNama.getText().toString();
        String nomorTelepon = this.edtNomorTelepon.getText().toString();

        // Panggil Modelnya
        KontakModel mKontak = new KontakModel(this);

        if(this.kontakTerpilih == null) // Jika tidak ada kontak terpilih berarti saat ini adalah window untuk menambah/mengurange data Pak..
        {
            // Masukkan ke Entity-nya
            Kontak k = new Kontak();
            k.setNama(nama);
            k.setNomorTelepon(nomorTelepon);

            mKontak.tambahKontak(k);
        }
        else
        {
            this.kontakTerpilih.setNama(nama);
            this.kontakTerpilih.setNomorTelepon(nomorTelepon);
            
            mKontak.perbaruiKontak(this.kontakTerpilih);
        }

        Toast.makeText(this, "Data telah disimpan..", Toast.LENGTH_SHORT).show();
    }

    public void btnKembali_onClick(View view)
    {
        this.finish();
    }

    public void btnHapus_onClick(View view)
    {
        KontakModel mKontak = new KontakModel(this);

        mKontak.hapusKontak(this.kontakTerpilih);

        this.edtNama.setText("");
        this.edtNomorTelepon.setText("");

        this.kontakTerpilih = null;

        this.sesuaikanTampilan();

        Toast.makeText(this, "Data telah dihapus..", Toast.LENGTH_SHORT).show();
    }
}
package com.yunhasnawa.kontaksederhana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yunhasnawa.kontaksederhana.entity.Kontak;
import com.yunhasnawa.kontaksederhana.model.KontakModel;

import java.util.ArrayList;

public class LihatKontakActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Jadikan properti kelas
    private ListView lstDaftarKontak;

    // Adapter untuk ListView
    private ArrayAdapter<String> adapterDaftarKontak;

    // Daftar nama dalam bentuk ArrayList<String>
    private ArrayList<String> daftarNama; // Contoh: ['Adi', 'Budi', 'Cici']

    // Daftar kontak dari database
    private ArrayList<Kontak> daftarKontak;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kontak);

        this.lstDaftarKontak = this.findViewById(R.id.lst_daftar_kontak);

        this.daftarNama = new ArrayList<>(); // [], Array yang belum punya isi, tetapi TIDAK null.

        this.isiDaftarNama();

        this.adapterDaftarKontak = new ArrayAdapter<>(
                this, // Konteks/Activity yang memanggil
                android.R.layout.simple_list_item_1, // Template bawaan Android
                this.daftarNama // Dari mana datanya diambil
        );

        // Pasangkan adapter ke ListView
        this.lstDaftarKontak.setAdapter(this.adapterDaftarKontak);

        // Ketika list item diklik muncul toast.
        // Event Listener
        // Tentang: objek yang "mendengarkan" apa yang dialami oleh objek yang didengarkan
        this.lstDaftarKontak.setOnItemClickListener(this);
    }

    private void isiDaftarNama()
    {
        /*
        this.daftarNama.add("Adi");
        this.daftarNama.add("Budi");
        this.daftarNama.add("Cici");
         */

        KontakModel mKontak = new KontakModel(this);

        ArrayList<Kontak> daftarKontak = mKontak.ambilSemuaKontak();

        for (Kontak k : daftarKontak)
        {
            this.daftarNama.add(k.getNama());
        }

        // Kita simpan daftarKontaknya ke property class
        this.daftarKontak = daftarKontak;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        // Lakukan apa saja yang Anda inginkan ketika itemnya ListView diklik.
        // Ambil kontak dari array sesuai dengan posisi klik oleh user
        Kontak kontakDiKlik = this.daftarKontak.get(position);

        int kontakId = kontakDiKlik.getId();

        Intent i = new Intent(LihatKontakActivity.this, TambahKontakActivity.class);
        i.putExtra("kontakId", kontakId);
        this.startActivity(i);
    }

    public void btnKembali_onClick(View view)
    {
        this.finish();
    }

    public void btnRefresh_onClick(View view)
    {
        this.daftarKontak.clear();

        this.daftarNama.clear();

        this.adapterDaftarKontak.clear();

        this.isiDaftarNama();

        this.adapterDaftarKontak.notifyDataSetChanged();

        Toast.makeText(this, "Data telah di-refresh..", Toast.LENGTH_SHORT).show();
    }
}
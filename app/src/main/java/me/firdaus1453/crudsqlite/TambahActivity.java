package me.firdaus1453.crudsqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {

    private EditText edtJudul, edtIsi;

    private DBCatatan dbCatatan;
    private String setJudul, setIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        Button btnSave = findViewById(R.id.btnSave);
        edtIsi = findViewById(R.id.edtIsi);
        edtJudul = findViewById(R.id.edtJudul);

        // Membuat object DBCatatan
        dbCatatan = new DBCatatan(getBaseContext());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(), "Tersimpan", Toast.LENGTH_SHORT).show();
                clearData();
                Intent intent = new Intent(TambahActivity.this, MainNavDrawerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearData() {
        edtJudul.setText("");
        edtIsi.setText("");
    }

    private void saveData() {
        // Mendapatkan table dengan mode menulis
        SQLiteDatabase create = dbCatatan.getWritableDatabase();

        // Membuat penampung data values
        ContentValues values = new ContentValues();
        values.put(DBCatatan.MyColumns.judul, setJudul);
        values.put(DBCatatan.MyColumns.isi, setIsi);

        // Menambahkan baris baru
        create.insert(DBCatatan.MyColumns.namaTabel, null, values);
    }

    private void setData() {
        setJudul = edtJudul.getText().toString();
        setIsi = edtIsi.getText().toString();
    }
}

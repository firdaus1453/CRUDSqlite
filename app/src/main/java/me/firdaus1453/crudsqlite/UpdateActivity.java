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

public class UpdateActivity extends AppCompatActivity {

    public static final String EXTRA_JUDUL = "judul";
    public static final String EXTRA_ISI = "isi";
    public static final String EXTRA_ID = "id";

    private DBCatatan dbCatatan;
    private String getJudul, getIsi, getId;
    private EditText edtJudul, edtIsi;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setTitle("Ubah Catatan");

        edtJudul = findViewById(R.id.edtJudulUpdate);
        edtIsi = findViewById(R.id.edtIsiUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);

        dbCatatan = new DBCatatan(getBaseContext());

        getId = getIntent().getStringExtra(EXTRA_ID);
        edtJudul.setText(getIntent().getStringExtra(EXTRA_JUDUL));
        edtIsi.setText(getIntent().getStringExtra(EXTRA_ISI));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpdateData();
                startActivity(new Intent(UpdateActivity.this, MainNavDrawerActivity.class));
                finish();
            }
        });
    }

    private void setUpdateData() {
        getJudul = edtJudul.getText().toString();
        getIsi = edtIsi.getText().toString();

        SQLiteDatabase database = dbCatatan.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBCatatan.MyColumns.judul, "");
        values.put(DBCatatan.MyColumns.judul, getJudul);
        values.put(DBCatatan.MyColumns.isi, getIsi);

        String selection = DBCatatan.MyColumns.id_judul + " LIKE?";
        String[] selectionArgs = {getId};
        database.update(DBCatatan.MyColumns.namaTabel, values, selection, selectionArgs);
        Toast.makeText(this, "Berhasil diubah", Toast.LENGTH_SHORT).show();
    }
}

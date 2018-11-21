package me.firdaus1453.crudsqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Intent intent = new Intent(UpdateActivity.this, MainNavDrawerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        MenuItem searchIem = menu.findItem(R.id.search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                DBCatatan getDbCatatan = new DBCatatan(getBaseContext());
                SQLiteDatabase DeleteData = getDbCatatan.getWritableDatabase();

                // Membuat query untuk mencari id_judul
                String selection = DBCatatan.MyColumns.id_judul + " LIKE ?";

                // mengambil data ID
                String[] selectionArgs = {getId};
                // Aksi Delete
                DeleteData.delete(DBCatatan.MyColumns.namaTabel, selection, selectionArgs);

                Toast.makeText(getBaseContext(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateActivity.this, MainNavDrawerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

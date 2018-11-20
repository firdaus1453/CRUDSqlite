package me.firdaus1453.crudsqlite;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecycleViewFragment extends Fragment {

    public static DBCatatan dbCatatan;
    public static ArrayList<DataFilter> dataList;
    public static ArrayList<DataFilter> mFilteredList;
    public static RecycleAdapter adapter;

    public RecycleViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);

        RecyclerView rvCatatan = view.findViewById(R.id.rvCatatan);

        dbCatatan = new DBCatatan(getContext());

        dataList = new ArrayList<>();
        getData();

        // Membuat adapter
        adapter = new RecycleAdapter(dataList);

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvCatatan.setLayoutManager(layoutManager);
        rvCatatan.setHasFixedSize(true);
        rvCatatan.setAdapter(adapter);
        return view;
    }

    public static void getData() {
        // Membuat object database
        SQLiteDatabase readData = dbCatatan.getReadableDatabase();
        Cursor cursor = readData.rawQuery("SELECT * FROM " + DBCatatan.MyColumns.namaTabel, null);

        cursor.moveToFirst();

        for (int count = 0; count < cursor.getCount(); count++) {
            cursor.moveToPosition(count);
            dataList.add(new DataFilter(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
    }

    public static void doFilter(String s) {

        dataList.clear();
        getData();

        mFilteredList = new ArrayList<>();
        s = s.toLowerCase();
        Log.i("judul sebelum", s);
        Log.i("Isi dataList sebelum", dataList.toString());
        for (DataFilter data : dataList) {
            String nama = data.getJudul().toLowerCase();
            if (nama.contains(s)) {
                Log.i("Isi data loop", data.getJudul());
                mFilteredList.add(data);
            }
        }
        Log.i("Isi mFiltered for", mFilteredList.toString());
        adapter.setFilter(mFilteredList);

    }
}

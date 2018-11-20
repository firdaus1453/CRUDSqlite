package me.firdaus1453.crudsqlite;

public class DataFilter {

    private Integer id;
    private String judul;
    private String isi;

    public DataFilter(Integer id,String judul, String isi) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
    }

    public Integer getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }
}

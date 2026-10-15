package com.example.myapplication;
import com.google.gson.annotations.SerializedName;
public class Notatki {
    @SerializedName("tytul")
    private String tytulNotatki;
    @SerializedName("tresc")
    private String trescNotatki;
    @SerializedName("data_utw")
    private String dataStworzeniaNotatki;

    public Notatki(String tytulNotatki, String trescNotatki, String dataStworzeniaNotatki) {
        this.tytulNotatki = tytulNotatki;
        this.trescNotatki = trescNotatki;
        this.dataStworzeniaNotatki = dataStworzeniaNotatki;
    }

    public String getTytulNotatki() {
        return tytulNotatki;
    }

    public void setTytulNotatki(String tytulNotatki) {
        this.tytulNotatki = tytulNotatki;
    }

    public String getTrescNotatki() {
        return trescNotatki;
    }

    public void setTrescNotatki(String trescNotatki) {
        this.trescNotatki = trescNotatki;
    }

    public String getDataStworzeniaNotatki() {
        return dataStworzeniaNotatki;
    }

    public void setDataStworzeniaNotatki(String dataStworzeniaNotatki) {
        this.dataStworzeniaNotatki = dataStworzeniaNotatki;
    }
}

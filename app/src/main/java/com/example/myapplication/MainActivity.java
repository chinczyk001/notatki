package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listaNotatek;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        listaNotatek = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaNotatek
        );

        listView.setAdapter(arrayAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/chinczyk001/bazaNotatekJson/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        Call<List<Notatki>> call = jsonPlaceHolder.getNotatki();

        call.enqueue(new Callback<List<Notatki>>() {
            @Override
            public void onResponse(Call<List<Notatki>> call, Response<List<Notatki>> response) {
                if (response.isSuccessful()) {
                    listaNotatek.clear();
                    for (Notatki notatka : response.body()) {
                        String tekst =
                                "Tytuł: " + notatka.getTytulNotatki()
                                        + "\nTreść: " + notatka.getTrescNotatki()
                                        + "\nData: " + notatka.getDataStworzeniaNotatki();
                        listaNotatek.add(tekst);
                    }

                    arrayAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Notatki>> call, Throwable t) {

            }
        });
    }
}
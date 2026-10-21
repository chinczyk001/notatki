package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Notatki> listaNotatki;
    ListView listView;
    ArrayAdapter<Notatki> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/chinczyk001/bazaNotatekJson/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<Notatki>> call = jsonPlaceHolder.getNotatki();
        call.enqueue(new Callback<List<Notatki>>() {
            @Override
            public void onResponse(Call<List<Notatki>> call, Response<List<Notatki>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,
                            response.code(),
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                listaNotatki = response.body();
            }

            @Override
            public void onFailure(Call<List<Notatki>> call, Throwable t) {

            }
        });
    }
}
package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.proyectoprueba.api.AdapterAgenda;
import com.example.proyectoprueba.api.RetrofitUtil;
import com.example.proyectoprueba.api.Turno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgendaActivity extends AppCompatActivity {
    private EditText fisioterapeuta, fecha;
    private RecyclerView rvAgenda;
    private FloatingActionButton fabAtras;
    private AdapterAgenda adapterAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        fisioterapeuta = findViewById(R.id.txtFisio);
        fecha = findViewById(R.id.txtfecha);
        rvAgenda = findViewById(R.id.rvListadoAgenda);
        fabAtras = findViewById(R.id.fabAtras);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvAgenda.setLayoutManager(layoutManager);
        cargarAgenda();
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(AgendaActivity.this, TurnoActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void cargarAgenda(){
        String id = "1";
        String hoy = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        Map<String, String> filtros = new HashMap<>();
        filtros.put("fecha",hoy);
        filtros.put("disponible","S");
        Call<Turno[]> callApi = RetrofitUtil.getPacienteService().getAgenda(id, filtros);
        callApi.enqueue(new Callback<Turno[]>() {
            @Override
            public void onResponse(Call<Turno[]> call, Response<Turno[]> response) {
                Turno[] arrayTurnos = response.body();
                adapterAgenda = new AdapterAgenda(arrayTurnos);
                rvAgenda.setAdapter(adapterAgenda);
            }
            @Override
            public void onFailure(Call<Turno[]> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verAgenda(View view){
        String id = fisioterapeuta.getText().toString();
        Map<String, String> filtros = new HashMap<>();
        filtros.put("fecha",fecha.getText().toString());
        filtros.put("disponible","S");
        Call<Turno[]> callApi = RetrofitUtil.getPacienteService().getAgenda(id, filtros);
        callApi.enqueue(new Callback<Turno[]>() {
            @Override
            public void onResponse(Call<Turno[]> call, Response<Turno[]> response) {
                Turno[] arrayTurnos = response.body();
                adapterAgenda = new AdapterAgenda(arrayTurnos);
                rvAgenda.setAdapter(adapterAgenda);
            }
            @Override
            public void onFailure(Call<Turno[]> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }
}
package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.proyectoprueba.api.AdapterFicha;
import com.example.proyectoprueba.api.AdapterPaciente;
import com.example.proyectoprueba.api.Datos;
import com.example.proyectoprueba.api.DatosFicha;
import com.example.proyectoprueba.api.Ficha;
import com.example.proyectoprueba.api.Paciente;
import com.example.proyectoprueba.api.RetrofitUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FichaActivity extends AppCompatActivity {
    private EditText emp, cli, desde, hasta;
    private RecyclerView rvFichas;
    private AdapterFicha adapterFicha;
    private FloatingActionButton fabNuevaFicha;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        desde = findViewById(R.id.txtfechaDesde);
        hasta = findViewById(R.id.txtfechaHasta);
        emp = findViewById(R.id.txtEm);
        cli = findViewById(R.id.txtCli);
        rvFichas = findViewById(R.id.rvListadoFichas);
        fabNuevaFicha = findViewById(R.id.fabNuevaFicha);
        fabAtras = findViewById(R.id.fabAtras);

        fabNuevaFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevaFichaIntent = new Intent(FichaActivity.this, NuevaFichaActivity.class);
                startActivity(nuevaFichaIntent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvFichas.setLayoutManager(layoutManager);
        cargarFichas();
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(FichaActivity.this, PrincipalActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void cargarFichas(){
        Call<DatosFicha> callApi = RetrofitUtil.getFichaService().getFichas();
        callApi.enqueue(new Callback<DatosFicha>() {
            @Override
            public void onResponse(Call<DatosFicha> call, Response<DatosFicha> response) {
                Ficha[] arrayFichas = response.body().getLista();
                adapterFicha = new AdapterFicha(arrayFichas);
                rvFichas.setAdapter(adapterFicha);
            }

            @Override
            public void onFailure(Call<DatosFicha> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verPorEmpleado(View view){
        String empleado = "{\"idEmpleado\":{\"idPersona\":"+emp.getText().toString()+"}}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("ejemplo",empleado);
        Call<DatosFicha> callApi = RetrofitUtil.getFichaService().getEmpleado(filtros);
        callApi.enqueue(new Callback<DatosFicha>() {
            @Override
            public void onResponse(Call<DatosFicha> call, Response<DatosFicha> response) {
                Ficha[] arrayFichas = response.body().getLista();
                adapterFicha = new AdapterFicha(arrayFichas);
                rvFichas.setAdapter(adapterFicha);
            }
            @Override
            public void onFailure(Call<DatosFicha> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verPorCliente(View view){
        String cliente = "{\"idCliente\":{\"idPersona\":"+cli.getText().toString()+"}}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("ejemplo",cliente);
        Call<DatosFicha> callApi = RetrofitUtil.getFichaService().getPaciente(filtros);
        callApi.enqueue(new Callback<DatosFicha>() {
            @Override
            public void onResponse(Call<DatosFicha> call, Response<DatosFicha> response) {
                Ficha[] arrayFichas = response.body().getLista();
                adapterFicha = new AdapterFicha(arrayFichas);
                rvFichas.setAdapter(adapterFicha);
            }
            @Override
            public void onFailure(Call<DatosFicha> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verPorPeriodo(View view){
        String periodo = "{\"fechaDesdeCadena\":\""+desde.getText().toString()+"\",\"fechaHastaCadena\":\""+hasta.getText().toString()+"\"}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("ejemplo",periodo);
        Call<DatosFicha> callApi = RetrofitUtil.getFichaService().getPeriodo(filtros);
        callApi.enqueue(new Callback<DatosFicha>() {
            @Override
            public void onResponse(Call<DatosFicha> call, Response<DatosFicha> response) {
                Ficha[] arrayFichas = response.body().getLista();
                adapterFicha = new AdapterFicha(arrayFichas);
                rvFichas.setAdapter(adapterFicha);
            }

            @Override
            public void onFailure(Call<DatosFicha> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }
}
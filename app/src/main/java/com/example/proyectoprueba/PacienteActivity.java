package com.example.proyectoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.api.AdapterPaciente;
import com.example.proyectoprueba.api.Datos;
import com.example.proyectoprueba.api.Paciente;
import com.example.proyectoprueba.api.RetrofitUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PacienteActivity extends AppCompatActivity {
    private EditText nom, ape;
    private RecyclerView rvPacientes;
    private AdapterPaciente adapterPaciente;
    private FloatingActionButton fabNuevoPaciente;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        nom = findViewById(R.id.txtNom);
        ape = findViewById(R.id.txtApe);
        rvPacientes = findViewById(R.id.rvListadoPacientes);
        fabNuevoPaciente = findViewById(R.id.fabNuevoPaciente);
        fabAtras = findViewById(R.id.fabAtras);

        fabNuevoPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoPacienteIntent = new Intent(PacienteActivity.this, NuevoPacienteActivity.class);
                startActivity(nuevoPacienteIntent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPacientes.setLayoutManager(layoutManager);
        cargarPacientes();
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(PacienteActivity.this, PrincipalActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void invocarApi(View v){
        Call<Datos> callApi = RetrofitUtil.getPacienteService().getPacientes();
        callApi.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                Paciente[] arrayPacientes = response.body().getLista();
                for (int i = 0; i < arrayPacientes.length; i++){
                    Log.e("s", "paciente "+i+": "+arrayPacientes[i]);
                }
            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void cargarPacientes(){
        Call<Datos> callApi = RetrofitUtil.getPacienteService().getPacientes();
        callApi.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                Paciente[] arrayPacientes = response.body().getLista();
                adapterPaciente = new AdapterPaciente(arrayPacientes);
                rvPacientes.setAdapter(adapterPaciente);
            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verFisioterapeutas(View view){
        Call<Datos> callApi = RetrofitUtil.getPacienteService().getUsuarioDelSistema();
        callApi.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                Paciente[] arrayPacientes = response.body().getLista();
                adapterPaciente = new AdapterPaciente(arrayPacientes);
                rvPacientes.setAdapter(adapterPaciente);
            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verPorNombre(View view){
        String nombre = "{\"nombre\":\""+nom.getText().toString()+"\"}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("like", "S");
        filtros.put("ejemplo",nombre);
        Call<Datos> callApi = RetrofitUtil.getPacienteService().getPacienteNombre(filtros);
        callApi.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                Paciente[] arrayPacientes = response.body().getLista();
                adapterPaciente = new AdapterPaciente(arrayPacientes);
                rvPacientes.setAdapter(adapterPaciente);
            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verPorApellido(View view){
        String apellido = "{\"apellido\":\""+ape.getText().toString()+"\"}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("like", "S");
        filtros.put("ejemplo",apellido);
        Call<Datos> callApi = RetrofitUtil.getPacienteService().getPacienteApellido(filtros);
        callApi.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                Paciente[] arrayPacientes = response.body().getLista();
                adapterPaciente = new AdapterPaciente(arrayPacientes);
                rvPacientes.setAdapter(adapterPaciente);
            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

}
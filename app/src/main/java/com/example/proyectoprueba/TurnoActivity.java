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
import com.example.proyectoprueba.api.AdapterTurno;
import com.example.proyectoprueba.api.DatosFicha;
import com.example.proyectoprueba.api.DatosTurno;
import com.example.proyectoprueba.api.Ficha;
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

public class TurnoActivity extends AppCompatActivity {
    private EditText emp, cli, desde, hasta;
    private RecyclerView rvTurnos;
    private AdapterTurno adapterTurno;
    private FloatingActionButton fabNuevoTurno;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno);
        desde = findViewById(R.id.txtReservaDesde);
        hasta = findViewById(R.id.txtReservaHasta);
        emp = findViewById(R.id.txtE);
        cli = findViewById(R.id.txtC);
        rvTurnos = findViewById(R.id.rvListadoTurnos);
        fabNuevoTurno = findViewById(R.id.fabNuevoTurno);
        fabAtras = findViewById(R.id.fabAtras);

        fabNuevoTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nuevoTurnoIntent = new Intent(TurnoActivity.this, AgendaActivity.class);
                startActivity(nuevoTurnoIntent);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTurnos.setLayoutManager(layoutManager);
        cargarTurnos();
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(TurnoActivity.this, PrincipalActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void cargarTurnos(){
        String hoy = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String periodo = "{\"fechaDesdeCadena\":\""+hoy+"\",\"fechaHastaCadena\":\""+hoy+"\"}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("ejemplo",periodo);
        Call<DatosTurno> callApi = RetrofitUtil.getTurnoService().getTurnos(filtros);
        callApi.enqueue(new Callback<DatosTurno>() {
            @Override
            public void onResponse(Call<DatosTurno> call, Response<DatosTurno> response) {
                Turno[] arrayTurnos = response.body().getLista();
                adapterTurno = new AdapterTurno(arrayTurnos);
                rvTurnos.setAdapter(adapterTurno);
            }
            @Override
            public void onFailure(Call<DatosTurno> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verReservaCliente(View view){
        String empleado = "{\"idCliente\":{\"idPersona\":"+cli.getText().toString()+"}}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("ejemplo",empleado);
        Call<DatosTurno> callApi = RetrofitUtil.getTurnoService().getReservaPaciente(filtros);
        callApi.enqueue(new Callback<DatosTurno>() {
            @Override
            public void onResponse(Call<DatosTurno> call, Response<DatosTurno> response) {
                Turno[] arrayTurnos = response.body().getLista();
                adapterTurno = new AdapterTurno(arrayTurnos);
                rvTurnos.setAdapter(adapterTurno);
            }
            @Override
            public void onFailure(Call<DatosTurno> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }

    public void verReservaFisioterapeuta(View view){
        String periodo = "{\"idEmpleado\":{\"idPersona\":"+emp.getText().toString()+"},\"fechaDesdeCadena\":\""+desde.getText().toString()+"\",\"fechaHastaCadena\":\""+hasta.getText().toString()+"\"}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("ejemplo",periodo);
        Call<DatosTurno> callApi = RetrofitUtil.getTurnoService().getReservaEmpleado(filtros);
        callApi.enqueue(new Callback<DatosTurno>() {
            @Override
            public void onResponse(Call<DatosTurno> call, Response<DatosTurno> response) {
                Turno[] arrayTurnos = response.body().getLista();
                adapterTurno = new AdapterTurno(arrayTurnos);
                rvTurnos.setAdapter(adapterTurno);
            }
            @Override
            public void onFailure(Call<DatosTurno> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }
}
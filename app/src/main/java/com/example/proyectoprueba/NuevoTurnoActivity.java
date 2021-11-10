package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.proyectoprueba.api.Ficha;
import com.example.proyectoprueba.api.Paciente;
import com.example.proyectoprueba.api.Turno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NuevoTurnoActivity extends AppCompatActivity {
    private EditText turnoFechaCadena;
    private EditText turnoHoraInicioCadena;
    private EditText turnoHoraFinCadena;
    private EditText turnoIdEmpleado;
    private EditText turnoIdCliente;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_turno);
        turnoFechaCadena = findViewById(R.id.txtTurnoFechaCadenaNuevo);
        turnoHoraInicioCadena = findViewById(R.id.txtTurnoHoraInicioCadenaNuevo);
        turnoHoraFinCadena = findViewById(R.id.txtTurnoHoraFinCadenaNuevo);
        turnoIdEmpleado = findViewById(R.id.txtTurnoIdEmpleadoNuevo);
        turnoIdCliente = findViewById(R.id.txtTurnoIdClienteNuevo);

        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            turnoHoraInicioCadena.setText((String) extras.get("iniM"));
            turnoHoraFinCadena.setText((String) extras.get("finM"));
            turnoIdEmpleado.setText(String.valueOf(extras.get("idEmM")));
            turnoFechaCadena.setText((String) extras.get("fechaM"));
        }

        fabAtras = findViewById(R.id.fabAtras);
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(NuevoTurnoActivity.this, TurnoActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void agregarNuevoTurno(View v) {
        Turno turno = new Turno();
        turno.setFechaCadena(turnoFechaCadena.getText().toString());
        turno.setHoraInicioCadena(turnoHoraInicioCadena.getText().toString());
        turno.setHoraFinCadena(turnoHoraFinCadena.getText().toString());
        Paciente idEmpleado = new Paciente();
        idEmpleado.setIdPersona(Integer.parseInt(turnoIdEmpleado.getText().toString()));
        Paciente idCliente = new Paciente();
        idCliente.setIdPersona(Integer.parseInt(turnoIdCliente.getText().toString()));
        turno.setIdEmpleado(idEmpleado);
        turno.setIdCliente(idCliente);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            public void run() {
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, new Gson().toJson(turno));
                Request request = new Request.Builder()
                        .url("https://equipoyosh.com/stock-nutrinatalia/reserva")
                        .post(body)
                        .addHeader("Authorization", "Basic d21472b7bdb23496d183a270e9a3516ce03fd7bf26f4e05e6efa94e105d1abc5")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("usuario", "usuario1")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String respuesta = response.body().string();
                    Log.e("s", respuesta);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
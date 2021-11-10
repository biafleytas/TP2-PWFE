package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectoprueba.api.Ficha;
import com.example.proyectoprueba.api.Paciente;
import com.example.proyectoprueba.api.TipoProducto;
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

public class ModificarTurnoActivity extends AppCompatActivity {
    private EditText observacion, asistio;
    private TextView tvObservacion, tvAsistio;
    private Integer idTurno;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_turno);
        tvObservacion = findViewById(R.id.txtObservacionTM);
        tvObservacion.setText("Observacion:");
        observacion = findViewById(R.id.txtObservacionTModificar);
        tvAsistio = findViewById(R.id.txtAsistioTM);
        tvAsistio.setText("Asistio:");
        asistio = findViewById(R.id.txtAsistioTModificar);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            idTurno = (Integer) extras.get("idM");
            observacion.setText((String) extras.get("obM"));
            asistio.setText((String) extras.get("aM"));
        }

        fabAtras = findViewById(R.id.fabAtras);
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(ModificarTurnoActivity.this, TurnoActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void putTurno(View v){
        Turno turno = new Turno();
        turno.setIdReserva(idTurno);
        turno.setObservacion(observacion.getText().toString());
        turno.setFlagAsistio(asistio.getText().toString());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            public void run(){
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, new Gson().toJson(turno));
                Request request = new Request.Builder()
                        .url("https://equipoyosh.com/stock-nutrinatalia/reserva")
                        .put(body)
                        .addHeader("Authorization", "Basic d21472b7bdb23496d183a270e9a3516ce03fd7bf26f4e05e6efa94e105d1abc5")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("usuario", "usuario1")
                        .build();
                Response response = null;
                try{
                    response = client.newCall(request).execute();
                    String respuesta = response.body().string();
                    Log.e("s", respuesta);
                    finish();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
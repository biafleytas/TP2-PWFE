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

public class ModificarFichaActivity extends AppCompatActivity {
    private EditText observacion;
    private TextView tvObservacion;
    private Integer idFicha, idEmpleado, idCliente, idTipoProducto;
    private String motivo, diagnostico;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_ficha);
        tvObservacion = findViewById(R.id.txtObservacion);
        tvObservacion.setText("Observacion:");
        observacion = findViewById(R.id.txtObservacionModificar);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            idFicha = (Integer) extras.get("idM");
            observacion.setText((String) extras.get("obM"));
            idEmpleado = (Integer) extras.get("idEM");
            idCliente = (Integer) extras.get("idCM");
            idTipoProducto = (Integer) extras.get("idTPM");
            motivo = (String) extras.get("mM");
            diagnostico = (String) extras.get("dM");
        }

        fabAtras = findViewById(R.id.fabAtras);
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(ModificarFichaActivity.this, FichaActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void putFicha(View v){
        Ficha ficha = new Ficha();
        ficha.setIdFichaClinica(idFicha);
        Paciente p1 = new Paciente();
        p1.setIdPersona(idEmpleado);
        ficha.setIdEmpleado(p1);
        Paciente p2 = new Paciente();
        p2.setIdPersona(idCliente);
        ficha.setIdCliente(p2);
        TipoProducto t = new TipoProducto();
        t.setIdTipoProducto(idTipoProducto);
        ficha.setIdTipoProducto(t);
        ficha.setObservacion(observacion.getText().toString());
        ficha.setMotivoConsulta(motivo);
        ficha.setDiagnostico(diagnostico);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            public void run(){
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, new Gson().toJson(ficha));
                Request request = new Request.Builder()
                        .url("https://equipoyosh.com/stock-nutrinatalia/fichaClinica")
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
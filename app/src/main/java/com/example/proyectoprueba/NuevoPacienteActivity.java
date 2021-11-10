package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoprueba.api.AdapterPaciente;
import com.example.proyectoprueba.api.Datos;
import com.example.proyectoprueba.api.Paciente;
import com.example.proyectoprueba.api.RetrofitUtil;
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

public class NuevoPacienteActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText telefono;
    private EditText email;
    private EditText ruc;
    private EditText ci;
    private EditText tipo;
    private EditText fechaNacimiento;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciente);
        nombre = findViewById(R.id.txtNombreNuevo);
        apellido = findViewById(R.id.txtApellidoNuevo);
        telefono = findViewById(R.id.txtTelefonoNuevo);
        email = findViewById(R.id.txtEmailNuevo);
        ruc = findViewById(R.id.txtRucNuevo);
        ci = findViewById(R.id.txtCiNuevo);
        tipo = findViewById(R.id.txtTipoNuevo);
        fechaNacimiento = findViewById(R.id.txtFechaNacimientoNuevo);

        fabAtras = findViewById(R.id.fabAtras);
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(NuevoPacienteActivity.this, PacienteActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void agregarNuevoPaciente(View v){
        Paciente paciente = new Paciente();
        paciente.setNombre(nombre.getText().toString());
        paciente.setApellido(apellido.getText().toString());
        paciente.setTelefono(telefono.getText().toString());
        paciente.setEmail(email.getText().toString());
        paciente.setRuc(ruc.getText().toString());
        paciente.setCedula(ci.getText().toString());
        paciente.setTipoPersona(tipo.getText().toString());
        paciente.setFechaNacimiento(fechaNacimiento.getText().toString()+" 00:00:00");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            public void run(){
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, new Gson().toJson(paciente));
                Request request = new Request.Builder()
                        .url("https://equipoyosh.com/stock-nutrinatalia/persona")
                        .post(body)
                        .addHeader("Authorization", "Basic d21472b7bdb23496d183a270e9a3516ce03fd7bf26f4e05e6efa94e105d1abc5")
                        .addHeader("Content-Type", "application/json")
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
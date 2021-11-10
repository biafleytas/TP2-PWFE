package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyectoprueba.api.Paciente;
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

public class ModificarPacienteActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText telefono;
    private EditText email;
    private EditText ruc;
    private EditText ci;
    private EditText tipo;
    private EditText fechaNacimiento;
    private Integer idPaciente;
    private TextView tvNombre, tvApellido, tvCi, tvRuc, tvTel, tvEmail, tvTipo, tvFecha;
    private FloatingActionButton fabAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_paciente);
        tvNombre = findViewById(R.id.txtNombreM);
        tvNombre.setText("Nombre:");
        tvApellido = findViewById(R.id.txtApellidoM);
        tvApellido.setText("Apellido:");
        tvCi = findViewById(R.id.txtCiM);
        tvCi.setText("CI:");
        tvRuc = findViewById(R.id.txtRucM);
        tvRuc.setText("RUC:");
        tvTel = findViewById(R.id.txtTelM);
        tvTel.setText("Telefono:");
        tvEmail = findViewById(R.id.txtEmailM);
        tvEmail.setText("Email:");
        tvTipo = findViewById(R.id.txtTipoM);
        tvTipo.setText("Tipo:");
        tvFecha = findViewById(R.id.txtfechaNacimientoM);
        tvFecha.setText("Fecha de Nacimiento:");
        nombre = findViewById(R.id.txtNombreModificar);
        apellido = findViewById(R.id.txtApellidoModificar);
        telefono = findViewById(R.id.txtTelefonoModificar);
        email = findViewById(R.id.txtEmailModificar);
        ruc = findViewById(R.id.txtRucModificar);
        ci = findViewById(R.id.txtCiModificar);
        tipo = findViewById(R.id.txtTipoModificar);
        fechaNacimiento = findViewById(R.id.txtFechaNacimientoModificar);
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            idPaciente = (Integer) extras.get("idM");
            nombre.setText((String) extras.get("nomM"));
            apellido.setText((String) extras.get("apM"));
            telefono.setText((String) extras.get("telM"));
            email.setText((String) extras.get("emM"));
            ruc.setText((String) extras.get("rucM"));
            ci.setText((String) extras.get("ciM"));
            tipo.setText((String) extras.get("tpM"));
            fechaNacimiento.setText((String) extras.get("fnM"));
        }

        fabAtras = findViewById(R.id.fabAtras);
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(ModificarPacienteActivity.this, PacienteActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void putPaciente(View v){
        Paciente paciente = new Paciente();
        paciente.setIdPersona(idPaciente);
        paciente.setNombre(nombre.getText().toString());
        paciente.setApellido(apellido.getText().toString());
        paciente.setTelefono(telefono.getText().toString());
        paciente.setEmail(email.getText().toString());
        paciente.setRuc(ruc.getText().toString());
        paciente.setCedula(ci.getText().toString());
        paciente.setTipoPersona(tipo.getText().toString());
        paciente.setFechaNacimiento(fechaNacimiento.getText().toString()+ " 00:00:00");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            public void run(){
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, new Gson().toJson(paciente));
                Request request = new Request.Builder()
                        .url("https://equipoyosh.com/stock-nutrinatalia/persona")
                        .put(body)
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
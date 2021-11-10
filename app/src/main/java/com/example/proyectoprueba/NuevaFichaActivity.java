package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.proyectoprueba.api.DatosTurno;
import com.example.proyectoprueba.api.Ficha;
import com.example.proyectoprueba.api.Paciente;
import com.example.proyectoprueba.api.RetrofitUtil;
import com.example.proyectoprueba.api.TipoProducto;
import com.example.proyectoprueba.api.Turno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class NuevaFichaActivity extends AppCompatActivity {
    private EditText observacion;
    private EditText motivoConsulta;
    private EditText diagnostico;
    private EditText idEmpleado;
    private EditText idTipoProducto;
    private FloatingActionButton fabAtras;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_ficha);
        observacion = findViewById(R.id.txtObservacionNuevo);
        motivoConsulta = findViewById(R.id.txtMotivoConsultaNuevo);
        sp = findViewById(R.id.listaC);
        verPorReserva();
        diagnostico = findViewById(R.id.txtDiagnosticoNuevo);
        idEmpleado = findViewById(R.id.txtIdEmpleadoNuevo);
        idTipoProducto = findViewById(R.id.txtIdTipoProductoNuevo);
        fabAtras = findViewById(R.id.fabAtras);
        fabAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atrasIntent = new Intent(NuevaFichaActivity.this, FichaActivity.class);
                startActivity(atrasIntent);
            }
        });
    }

    public void agregarNuevaFicha(View v) {
        Ficha ficha = new Ficha();
        ficha.setObservacion(observacion.getText().toString());
        ficha.setMotivoConsulta(motivoConsulta.getText().toString());
        ficha.setDiagnostico(diagnostico.getText().toString());
        Paciente empleado = new Paciente();
        empleado.setIdPersona(Integer.parseInt(idEmpleado.getText().toString()));
        ficha.setIdEmpleado(empleado);
        Paciente cliente = new Paciente();
        cliente.setIdPersona(Integer.parseInt(sp.getSelectedItem().toString()));
        ficha.setIdCliente(cliente);
        TipoProducto producto = new TipoProducto();
        producto.setIdTipoProducto(Integer.parseInt(idTipoProducto.getText().toString()));
        ficha.setIdTipoProducto(producto);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            public void run() {
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(JSON, new Gson().toJson(ficha));
                Request request = new Request.Builder()
                        .url("https://equipoyosh.com/stock-nutrinatalia/fichaClinica")
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

    public void verPorReserva(){
        String hoy = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String fecha = "{\"fechaDesdeCadena\":\""+hoy+"\",\"fechaHastaCadena\":\""+hoy+"\"}";
        Map<String, String> filtros = new HashMap<>();
        filtros.put("ejemplo",fecha);
        Call<DatosTurno> callApi = RetrofitUtil.getTurnoService().getReservaDia(filtros);
        callApi.enqueue(new Callback<DatosTurno>() {
            @Override
            public void onResponse(Call<DatosTurno> call, retrofit2.Response<DatosTurno> response) {
                Turno[] arrayTurnos = response.body().getLista();
                String [] idP = new String[arrayTurnos.length];
                for(int i=0; i<arrayTurnos.length; i++){
                    idP[i] = String.valueOf(arrayTurnos[i].getIdCliente().getIdPersona());
                }
                ArrayAdapter<String> adp = new ArrayAdapter<>(NuevaFichaActivity.this, android.R.layout.simple_spinner_item, idP);
                sp.setAdapter(adp);
            }
            @Override
            public void onFailure(Call<DatosTurno> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });
    }
}
package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoprueba.api.Datos;
import com.example.proyectoprueba.api.Paciente;
import com.example.proyectoprueba.api.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText password;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.textNombreUsuario);
        password = findViewById(R.id.textPassword);
    }
    
    public void btnIngresar(View v){
        Call<Datos> callApi = RetrofitUtil.getPacienteService().getUsuarioDelSistema();
        callApi.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                Paciente[] arrayUsuarios = response.body().getLista();
                for (int i = 0; i < arrayUsuarios.length; i++){
                    if (usuario.getText().toString().equalsIgnoreCase(arrayUsuarios[i].getUsuarioLogin())){
                        Intent principalIntent = new Intent(MainActivity.this, PrincipalActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("usuario", usuario.getText().toString());
                        principalIntent.putExtras(bundle);
                        startActivity(principalIntent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Log.e("s", t.toString());
            }
        });

    }
}
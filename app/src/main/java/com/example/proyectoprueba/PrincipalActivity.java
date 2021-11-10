package com.example.proyectoprueba;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void btnPaciente(View v){
        Intent principalIntent = new Intent(this, PacienteActivity.class);
        startActivity(principalIntent);
    }

    public void btnTurno(View v){
        Intent principalIntent = new Intent(this, TurnoActivity.class);
        startActivity(principalIntent);
    }

    public void btnFicha(View v){
        Intent principalIntent = new Intent(this, FichaActivity.class);
        startActivity(principalIntent);
    }
}
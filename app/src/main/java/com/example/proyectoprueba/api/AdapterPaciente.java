package com.example.proyectoprueba.api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectoprueba.ModificarPacienteActivity;
import com.example.proyectoprueba.NuevoPacienteActivity;
import com.example.proyectoprueba.PacienteActivity;
import com.example.proyectoprueba.PrincipalActivity;
import com.example.proyectoprueba.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Path;


public class AdapterPaciente extends RecyclerView.Adapter<AdapterPaciente.ViewHolder> {
    private Paciente [] dsPaciente;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_paciente, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPaciente.ViewHolder viewHolder, int i) {
        viewHolder.tvPaciente.setText("Nombre: "+ dsPaciente[i].getNombre()+" "+dsPaciente[i].getApellido());
        viewHolder.tvCedula.setText("CI: "+dsPaciente[i].getCedula());
        viewHolder.tvEmail.setText("Email: "+dsPaciente[i].getEmail());
        viewHolder.tvTelefono.setText("Telefono: "+dsPaciente[i].getTelefono());
        viewHolder.tvTipo.setText("Tipo: "+dsPaciente[i].getTipoPersona());
        Integer id = dsPaciente[i].getIdPersona();
        String url = "https://equipoyosh.com/stock-nutrinatalia/persona/"+id;
        viewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable(){
                    public void run(){
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                            .url(url)
                            .delete()
                            .addHeader("Authorization", "Basic d21472b7bdb23496d183a270e9a3516ce03fd7bf26f4e05e6efa94e105d1abc5")
                            .addHeader("Content-Type", "application/json")
                            .build();
                        okhttp3.Response response = null;
                        try{
                            response = client.newCall(request).execute();
                            String respuesta = response.body().string();
                            Log.e("s", respuesta);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        viewHolder.modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent modificarIntent = new Intent(view.getContext(), ModificarPacienteActivity.class);
                modificarIntent.putExtra("idM",dsPaciente[i].getIdPersona());
                modificarIntent.putExtra("nomM",dsPaciente[i].getNombre());
                modificarIntent.putExtra("apM",dsPaciente[i].getApellido());
                modificarIntent.putExtra("ciM",dsPaciente[i].getCedula());
                modificarIntent.putExtra("rucM",dsPaciente[i].getRuc());
                modificarIntent.putExtra("emM",dsPaciente[i].getEmail());
                modificarIntent.putExtra("telM",dsPaciente[i].getTelefono());
                modificarIntent.putExtra("tpM",dsPaciente[i].getTipoPersona());
                modificarIntent.putExtra("fnM",dsPaciente[i].getFechaNacimiento());
                view.getContext().startActivity(modificarIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dsPaciente.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvPaciente;
        public TextView tvCedula;
        public TextView tvEmail;
        public TextView tvTelefono;
        public TextView tvTipo;
        public FloatingActionButton eliminar;
        public FloatingActionButton modificar;
        public ViewHolder(View v){
            super(v);
            tvPaciente = v.findViewById(R.id.txtPaciente);
            tvCedula = v.findViewById(R.id.txtPacienteCedula);
            tvEmail = v.findViewById(R.id.txtPacienteEmail);
            tvTelefono= v.findViewById(R.id.txtPacienteTelefono);
            tvTipo= v.findViewById(R.id.txtPacienteTipo);
            eliminar = v.findViewById(R.id.fabEliminar);
            modificar = v.findViewById(R.id.fabEditar);
        }
    }

    public AdapterPaciente(Paciente[] listaDePacientes){
        this.dsPaciente = listaDePacientes;
    }
}

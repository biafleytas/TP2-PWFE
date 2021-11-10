package com.example.proyectoprueba.api;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.ModificarPacienteActivity;
import com.example.proyectoprueba.ModificarTurnoActivity;
import com.example.proyectoprueba.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AdapterTurno extends RecyclerView.Adapter<AdapterTurno.ViewHolder>{
    private Turno [] dsTurno;

    @NonNull
    @Override
    public AdapterTurno.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_turno, viewGroup, false);
        AdapterTurno.ViewHolder pvh = new AdapterTurno.ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTurno.ViewHolder viewHolder, int i) {
        viewHolder.tvTurnoFechaCadena.setText("Fecha: "+ dsTurno[i].getFechaCadena());
        viewHolder.tvTurnoHoraInicioCadena.setText("Hora inicio: "+dsTurno[i].getHoraInicioCadena());
        viewHolder.tvTurnoHoraFinCadena.setText("Hora Fin: "+dsTurno[i].getHoraFinCadena());
        viewHolder.tvTurnoIdEmpleado.setText("Empleado: "+dsTurno[i].getIdEmpleado().getNombre()+" "+dsTurno[i].getIdEmpleado().getApellido());
        viewHolder.tvTurnoIdCliente.setText("Paciente: "+dsTurno[i].getIdCliente().getNombre()+" "+dsTurno[i].getIdCliente().getApellido());
        Integer id = dsTurno[i].getIdReserva();
        String url = "https://equipoyosh.com/stock-nutrinatalia/reserva/"+id;
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
                Intent modificarIntent = new Intent(view.getContext(), ModificarTurnoActivity.class);
                modificarIntent.putExtra("idM",dsTurno[i].getIdReserva());
                modificarIntent.putExtra("obM",dsTurno[i].getObservacion());
                modificarIntent.putExtra("idEM",dsTurno[i].getIdEmpleado().getIdPersona());
                modificarIntent.putExtra("idCM",dsTurno[i].getIdCliente().getIdPersona());
                modificarIntent.putExtra("aM",dsTurno[i].getFlagAsistio());
                modificarIntent.putExtra("fcM",dsTurno[i].getFechaCadena());
                modificarIntent.putExtra("hiM",dsTurno[i].getHoraInicioCadena());
                modificarIntent.putExtra("hfM",dsTurno[i].getHoraFinCadena());
                view.getContext().startActivity(modificarIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsTurno.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTurnoFechaCadena;
        public TextView tvTurnoHoraInicioCadena;
        public TextView tvTurnoHoraFinCadena;
        public TextView tvTurnoIdEmpleado;
        public TextView tvTurnoIdCliente;
        public FloatingActionButton eliminar;
        public FloatingActionButton modificar;
        public ViewHolder(View v){
            super(v);
            tvTurnoFechaCadena = v.findViewById(R.id.txtTurnoFechaCadena);
            tvTurnoHoraInicioCadena = v.findViewById(R.id.txtTurnoHoraInicioCadena);
            tvTurnoHoraFinCadena = v.findViewById(R.id.txtTurnoHoraFinCadena);
            tvTurnoIdEmpleado= v.findViewById(R.id.txtTurnoIdEmpleado);
            tvTurnoIdCliente= v.findViewById(R.id.txtTurnoIdCliente);
            eliminar = v.findViewById(R.id.fabEliminar);
            modificar = v.findViewById(R.id.fabEditar);
        }
    }

    public AdapterTurno(Turno[] listaDeTurnos){
        this.dsTurno = listaDeTurnos;
    }
}

package com.example.proyectoprueba.api;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.ModificarFichaActivity;
import com.example.proyectoprueba.ModificarPacienteActivity;
import com.example.proyectoprueba.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AdapterFicha extends RecyclerView.Adapter<AdapterFicha.ViewHolder>{
    private Ficha [] dsFicha;

    @NonNull
    @Override
    public AdapterFicha.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ficha, viewGroup, false);
        AdapterFicha.ViewHolder pvh = new AdapterFicha.ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFicha.ViewHolder viewHolder, int i) {
        viewHolder.tvObservacion.setText("Observacion: "+ dsFicha[i].getObservacion());
        viewHolder.tvMotivoConsulta.setText("Motivo: "+dsFicha[i].getMotivoConsulta());
        viewHolder.tvDiagnostico.setText("Diagnostico: "+dsFicha[i].getDiagnostico());
        viewHolder.tvIdEmpleado.setText("Empleado: "+dsFicha[i].getIdEmpleado().getNombre()+" "+dsFicha[i].getIdEmpleado().getApellido());
        viewHolder.tvIdCliente.setText("Paciente: "+dsFicha[i].getIdCliente().getNombre()+" "+dsFicha[i].getIdCliente().getApellido());
        viewHolder.tvIdTipoProducto.setText("Producto: "+dsFicha[i].getIdTipoProducto().getDescripcion());
        Integer id = dsFicha[i].getIdFichaClinica();
        String url = "https://equipoyosh.com/stock-nutrinatalia/fichaClinica/"+id;
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
                Intent modificarIntent = new Intent(view.getContext(), ModificarFichaActivity.class);
                modificarIntent.putExtra("idM",dsFicha[i].getIdFichaClinica());
                modificarIntent.putExtra("obM",dsFicha[i].getObservacion());
                modificarIntent.putExtra("idEM",dsFicha[i].getIdEmpleado().getIdPersona());
                modificarIntent.putExtra("idCM",dsFicha[i].getIdCliente().getIdPersona());
                modificarIntent.putExtra("idTPM",dsFicha[i].getIdTipoProducto().getIdTipoProducto());
                modificarIntent.putExtra("mM",dsFicha[i].getMotivoConsulta());
                modificarIntent.putExtra("dM",dsFicha[i].getDiagnostico());
                view.getContext().startActivity(modificarIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsFicha.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvObservacion;
        public TextView tvMotivoConsulta;
        public TextView tvDiagnostico;
        public TextView tvIdEmpleado;
        public TextView tvIdCliente;
        public TextView tvIdTipoProducto;
        public FloatingActionButton eliminar;
        public FloatingActionButton modificar;
        public ViewHolder(View v){
            super(v);
            tvObservacion = v.findViewById(R.id.txtObservacion);
            tvMotivoConsulta = v.findViewById(R.id.txtMotivoConsulta);
            tvDiagnostico = v.findViewById(R.id.txtDiagnostico);
            tvIdEmpleado= v.findViewById(R.id.txtIdEmpleado);
            tvIdCliente= v.findViewById(R.id.txtIdCliente);
            tvIdTipoProducto= v.findViewById(R.id.txtIdTipoProducto);
            eliminar = v.findViewById(R.id.fabEliminar);
            modificar = v.findViewById(R.id.fabEditar);
        }
    }

    public AdapterFicha(Ficha[] listaDeFichas){
        this.dsFicha = listaDeFichas;
    }
}

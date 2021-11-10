package com.example.proyectoprueba.api;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.NuevoTurnoActivity;
import com.example.proyectoprueba.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder>{
    private Turno [] dsAgenda;

    @NonNull
    @Override
    public AdapterAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_agenda, viewGroup, false);
        AdapterAgenda.ViewHolder pvh = new AdapterAgenda.ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAgenda.ViewHolder viewHolder, int i) {
        viewHolder.tvInicio.setText("Hora inicio: "+ dsAgenda[i].getHoraInicioCadena());
        viewHolder.tvFin.setText("Hora Fin: "+dsAgenda[i].getHoraFinCadena());

        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(view.getContext(), NuevoTurnoActivity.class);
                addIntent.putExtra("iniM",dsAgenda[i].getHoraInicioCadena());
                addIntent.putExtra("finM",dsAgenda[i].getHoraFinCadena());
                addIntent.putExtra("fechaM",dsAgenda[i].getFechaCadena());
                addIntent.putExtra("idEmM",dsAgenda[i].getIdEmpleado().getIdPersona());
                view.getContext().startActivity(addIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsAgenda.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvInicio, tvFin;
        public FloatingActionButton add;
        public ViewHolder(View v){
            super(v);
            tvInicio = v.findViewById(R.id.txtInicio);
            tvFin = v.findViewById(R.id.txtFin);
            add = v.findViewById(R.id.fabAdd);
        }
    }

    public AdapterAgenda(Turno[] listaDeAgendas){
        this.dsAgenda = listaDeAgendas;
    }
}

package com.sg.proyectofinal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sg.proyectofinal.R;
import com.sg.proyectofinal.models.Servicio;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServicioAdapter extends RecyclerView.Adapter<ServicioAdapter.ServicioViewHolder> {

    private List<Servicio> servicios;
    private Context context;

    public ServicioAdapter(List<Servicio> servicios, Context context) {
        this.servicios = servicios;
        this.context = context;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_servicio, parent, false);
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        Servicio servicio = servicios.get(position);
        holder.nombreServicio.setText(servicio.getNombre());
        holder.descripcionServicio.setText(servicio.getDescripcion());

        // Cargar icono local
        holder.iconoServicio.setImageResource(servicio.getIcono());
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }

    public static class ServicioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreServicio, descripcionServicio;
        ImageView iconoServicio;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreServicio = itemView.findViewById(R.id.nombreServicio);
            descripcionServicio = itemView.findViewById(R.id.descripcionServicio);
            iconoServicio = itemView.findViewById(R.id.iconoServicio);
        }
    }
}
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
import com.sg.proyectofinal.models.MiembroEquipo;

import java.util.List;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.MiembroViewHolder> {

    public interface OnMiembroClickListener {
        void onMiembroClick(MiembroEquipo miembro);
    }

    private final List<MiembroEquipo> miembros;
    private final OnMiembroClickListener listener;
    private final Context context;

    public EquipoAdapter(List<MiembroEquipo> miembros, OnMiembroClickListener listener, Context context) {
        this.miembros = miembros;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public MiembroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_miembro_equipo, parent, false);
        return new MiembroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiembroViewHolder holder, int position) {
        MiembroEquipo miembro = miembros.get(position);

        holder.nombre.setText(miembro.getNombre());
        holder.cargo.setText(miembro.getCargo());
        holder.departamento.setText(miembro.getDepartamento());
        holder.imagen.setImageResource(miembro.getImagenPerfil());

        holder.itemView.setOnClickListener(v -> listener.onMiembroClick(miembro));
    }

    @Override
    public int getItemCount() {
        return miembros.size();
    }

    static class MiembroViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, cargo, departamento;
        ImageView imagen;

        public MiembroViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
            cargo = itemView.findViewById(R.id.tvCargo);
            departamento = itemView.findViewById(R.id.tvDepartamento);
            imagen = itemView.findViewById(R.id.ivFoto);
        }
    }
}
package com.sg.proyectofinal.adapters; // Asegúrate que el paquete sea el correcto

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sg.proyectofinal.R;
import com.sg.proyectofinal.models.AreaProtegidaList;

import java.util.List;

public class AreasProtegidasAdapter extends RecyclerView.Adapter<AreasProtegidasAdapter.AreaViewHolder> {

    private List<AreaProtegidaList> areasList;

    public AreasProtegidasAdapter(List<AreaProtegidaList> areasList) {
        this.areasList = areasList;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_area_protegida, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        AreaProtegidaList area = areasList.get(position);
        holder.tvNombre.setText(area.getNombre());
        holder.tvDescripcion.setText(area.getDescripcion());
        holder.tvUbicacion.setText(area.getUbicacion());
    }

    @Override
    public int getItemCount() {
        return areasList.size();
    }

    static class AreaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion, tvUbicacion;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tv_nombre_area);
            tvDescripcion = itemView.findViewById(R.id.tv_descripcion_area);
            tvUbicacion = itemView.findViewById(R.id.tv_ubicacion_area);
        }
    }
}
package com.sg.proyectofinal.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sg.proyectofinal.R;
import com.sg.proyectofinal.models.AreaProtegida;

import java.util.List;

public class AreaProtegidaAdapter extends RecyclerView.Adapter<AreaProtegidaAdapter.AreaViewHolder> {
    private List<AreaProtegida> areasList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(AreaProtegida area);
    }

    public AreaProtegidaAdapter(List<AreaProtegida> areasList, OnItemClickListener listener) {
        this.areasList = areasList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_area_protegida, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        AreaProtegida area = areasList.get(position);
        holder.bind(area, listener);
    }

    @Override
    public int getItemCount() {
        return areasList.size();
    }

    static class AreaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagenArea;
        private TextView nombreArea;
        private TextView visitasArea;
        private TextView ubicacionArea;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenArea = itemView.findViewById(R.id.imagen_area);
            nombreArea = itemView.findViewById(R.id.nombre_area);
            visitasArea = itemView.findViewById(R.id.visitas_area);
            ubicacionArea = itemView.findViewById(R.id.ubicacion_area);
        }

        public void bind(final AreaProtegida area, final OnItemClickListener listener) {
            imagenArea.setImageResource(area.getImagen());
            nombreArea.setText(area.getNombre());
            visitasArea.setText(String.format("Visitas: %,d", area.getVisitas()));
            ubicacionArea.setText(area.getUbicacion());

            itemView.setOnClickListener(v -> listener.onItemClick(area));
        }
    }
}
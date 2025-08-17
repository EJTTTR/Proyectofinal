package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sg.proyectofinal.R;

public class FragmentDetalleArea extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_area, container, false);

        // Obtener argumentos
        Bundle args = getArguments();
        if (args != null) {
            ImageView imagen = view.findViewById(R.id.imagen_detalle);
            TextView nombre = view.findViewById(R.id.nombre_detalle);
            TextView visitas = view.findViewById(R.id.visitas_detalle);
            TextView descripcion = view.findViewById(R.id.descripcion_detalle);
            TextView ubicacion = view.findViewById(R.id.ubicacion_detalle);
            TextView extension = view.findViewById(R.id.extension_detalle);
            TextView clima = view.findViewById(R.id.clima_detalle);
            TextView floraFauna = view.findViewById(R.id.flora_fauna_detalle);
            TextView actividades = view.findViewById(R.id.actividades_detalle);

            // Configurar datos
            imagen.setImageResource(args.getInt("imagen"));
            nombre.setText(args.getString("nombre"));
            visitas.setText(String.format("Visitas anuales: %,d", args.getInt("visitas")));
            descripcion.setText(args.getString("descripcion"));

            // Detalles adicionales
            ubicacion.setText(String.format("Ubicación: %s", args.getString("ubicacion")));
            extension.setText(String.format("Extensión: %s", args.getString("extension")));
            clima.setText(String.format("Clima: %s", args.getString("clima")));
            floraFauna.setText(String.format("Flora y Fauna: %s", args.getString("floraFauna")));
            actividades.setText(String.format("Actividades: %s", args.getString("actividades")));
        }

        return view;
    }
}
package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sg.proyectofinal.R;
import com.sg.proyectofinal.models.MiembroEquipo;

public class DetalleMiembroFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detalle_miembro, container, false);

        if (getArguments() != null) {
            MiembroEquipo miembro = (MiembroEquipo) getArguments().getSerializable("miembro");

            ImageView imagen = root.findViewById(R.id.ivFotoCompleta);
            TextView nombre = root.findViewById(R.id.tvNombreCompleto);
            TextView cargo = root.findViewById(R.id.tvCargoCompleto);
            TextView descripcion = root.findViewById(R.id.tvDescripcionCompleta);

            if (miembro != null) {
                imagen.setImageResource(miembro.getImagenCompleta());
                nombre.setText(miembro.getNombre());
                cargo.setText(miembro.getCargo());
                descripcion.setText(miembro.getDescripcionCompleta());
            }
        }

        return root;
    }
}
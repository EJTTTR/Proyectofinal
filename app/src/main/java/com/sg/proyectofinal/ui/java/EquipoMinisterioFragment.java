package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sg.proyectofinal.R;
import com.sg.proyectofinal.adapters.EquipoAdapter;
import com.sg.proyectofinal.models.MiembroEquipo;

import java.util.ArrayList;
import java.util.List;

public class EquipoMinisterioFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_equipo_ministerio, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.rvEquipo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<MiembroEquipo> equipo = obtenerEquipoMinisterio();
        EquipoAdapter adapter = new EquipoAdapter(equipo, miembro -> {
            Bundle args = new Bundle();
            args.putSerializable("miembro", miembro);
            Navigation.findNavController(root).navigate(R.id.action_equipoMinisterioFragment_to_detalleMiembroFragment, args);
        }, requireContext());

        recyclerView.setAdapter(adapter);
        return root;
    }

    private List<MiembroEquipo> obtenerEquipoMinisterio() {
        List<MiembroEquipo> equipo = new ArrayList<>();

        equipo.add(new MiembroEquipo(
                "Ana Emilia Pimentel Rodríguez",
                "Viceministro",
                "Cambio Climático y Sostenibilidad",
                "Especialista en planificación y desarrollo organizacional",
                getString(R.string.ana_pimentel_descripcion),
                R.drawable.ana_pimentel_recorte,
                R.drawable.ana_pimentel_completa
        ));

        equipo.add(new MiembroEquipo(
                "Carlos Augusto Batista",
                "Viceministro",
                "Áreas Protegidas",
                "Ingeniero industrial y ambientalista",
                getString(R.string.carlos_batista_descripcion),
                R.drawable.carlos_batista_recorte,
                R.drawable.carlos_batista_completa
        ));

        equipo.add(new MiembroEquipo(
                "Lenin Ramón Bueno Rodríguez",
                "Viceministro",
                "Gestión Ambiental",
                "Ingeniero civil con especialización en sostenibilidad",
                getString(R.string.lenin_bueno_descripcion),
                R.drawable.lenin_bueno_recorte,
                R.drawable.lenin_bueno_completa
        ));

        equipo.add(new MiembroEquipo(
                "José Ramón Reyes López",
                "Viceministro",
                "Recursos Costeros y Marinos",
                "Conocido como el «padre de los manatíes»",
                getString(R.string.jose_reyes_descripcion),
                R.drawable.jose_reyes_recorte,
                R.drawable.jose_reyes_completa
        ));

        equipo.add(new MiembroEquipo(
                "José Elías González",
                "Viceministro",
                "Recursos Forestales",
                "Profesional forestal con amplia experiencia",
                getString(R.string.jose_gonzalez_descripcion),
                R.drawable.jose_gonzalez_recorte,
                R.drawable.jose_gonzalez_completa
        ));

        // Agregar más miembros según sea necesario

        return equipo;
    }
}
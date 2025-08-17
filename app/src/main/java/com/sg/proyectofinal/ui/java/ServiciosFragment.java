package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sg.proyectofinal.R;
import com.sg.proyectofinal.adapters.ServicioAdapter;
import com.sg.proyectofinal.models.Servicio;

import java.util.ArrayList;
import java.util.List;

public class ServiciosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ServicioAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_servicios, container, false);

        recyclerView = root.findViewById(R.id.recyclerServicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Datos estáticos de los servicios
        List<Servicio> servicios = obtenerServiciosEstaticos();

        adapter = new ServicioAdapter(servicios, getContext());
        recyclerView.setAdapter(adapter);

        return root;
    }

    private List<Servicio> obtenerServiciosEstaticos() {
        List<Servicio> servicios = new ArrayList<>();

        servicios.add(new Servicio(
                "Autorizaciones Ambientales",
                "Evaluación y aprobación de proyectos que requieren licencia ambiental",
                R.drawable.ic_autorizacion
        ));

        servicios.add(new Servicio(
                "Certificación Ambiental",
                "Certificación de cumplimiento de normas ambientales para empresas",
                R.drawable.ic_certificado
        ));

        servicios.add(new Servicio(
                "Educación Ambiental",
                "Programas de concienciación y capacitación sobre protección ambiental",
                R.drawable.ic_educacion
        ));

        servicios.add(new Servicio(
                "Control de Calidad del Aire",
                "Monitoreo y control de emisiones atmosféricas",
                R.drawable.ic_aire
        ));

        servicios.add(new Servicio(
                "Manejo de Residuos",
                "Regulación y supervisión de manejo de desechos sólidos y peligrosos",
                R.drawable.ic_residuos
        ));

        servicios.add(new Servicio(
                "Protección de Biodiversidad",
                "Conservación de especies y ecosistemas protegidos",
                R.drawable.ic_biodiversidad
        ));

        return servicios;
    }
}
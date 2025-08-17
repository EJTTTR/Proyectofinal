package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sg.proyectofinal.R;
import com.sg.proyectofinal.adapters.AreaProtegidaAdapter;
import com.sg.proyectofinal.models.AreaProtegida;

import java.util.ArrayList;
import java.util.List;

public class FragmentMapaAreas extends Fragment {
    private RecyclerView recyclerView;
    private AreaProtegidaAdapter adapter;
    private List<AreaProtegida> areasList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mapa_areas, container, false);

        // Configurar el mapa
        ImageView mapa = view.findViewById(R.id.mapa_areas);
        mapa.setImageResource(R.drawable.areas_protegidas_mas_visitadas_en_rd);

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recycler_areas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista de áreas
        inicializarAreas();

        // Configurar adaptador
        adapter = new AreaProtegidaAdapter(areasList, new AreaProtegidaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AreaProtegida area) {
                abrirDetallesArea(area);
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void inicializarAreas() {
        areasList = new ArrayList<>();

        // Agregar todas las áreas protegidas con sus datos completos
        areasList.add(new AreaProtegida(
                "Salto de la Damajagua",
                66316,
                "Conocido como los 27 Charcos, es un conjunto de cascadas y pozas naturales en Puerto Plata. Ideal para excursiones de aventura.",
                "Provincia Puerto Plata",
                "Aprox. 5 km de recorrido",
                "Tropical húmedo",
                "Bosque subtropical con especies como caoba, ceiba y diversas aves.",
                "Caminatas, saltos en cascadas, baños en pozas naturales",
                R.drawable.salto_damajagua // Asegúrate de tener esta imagen en drawable
        ));

        areasList.add(new AreaProtegida(
                "Monumento Natural Laguna Gri Gri",
                112941,
                "Laguna de agua dulce rodeada de manglares en Río San Juan, con un canal natural que conduce al mar.",
                "Río San Juan, provincia María Trinidad Sánchez",
                "Aprox. 1.5 km²",
                "Tropical costero",
                "Manglares rojos y blancos, aves migratorias, peces de agua dulce.",
                "Paseos en bote, observación de aves, fotografía",
                R.drawable.laguna_grigri
        ));

        areasList.add(new AreaProtegida(
                "Bancos de la Plata y de la Navidad",
                100306,
                "Primer santuario marino del Atlántico, importante zona de reproducción de ballenas jorobadas.",
                "Costa norte de RD, frente a la Bahía de Samaná",
                "Aprox. 19,438 km² marinos",
                "Marino tropical",
                "Ballenas jorobadas, delfines, corales, peces tropicales.",
                "Avistamiento de ballenas (enero-marzo), snorkeling",
                R.drawable.bancos_plata
        ));

        areasList.add(new AreaProtegida(
                "Los Haitises",
                127704,
                "Parque nacional con mogotes, manglares y cuevas con pictografías taínas. Ecosistema único en el país.",
                "Provincias Samaná, Monte Plata y Hato Mayor",
                "1,600 km²",
                "Tropical húmedo",
                "Manglares, bosque húmedo, pelícanos, cotorras, manatíes.",
                "Paseos en bote, visita a cuevas, observación de aves",
                R.drawable.haitises
        ));

        areasList.add(new AreaProtegida(
                "Salto del Limón",
                25768,
                "Impresionante cascada de 40 metros de altura en medio de la selva tropical de Samaná.",
                "Samaná",
                "Área de 5 km alrededor de la cascada",
                "Tropical húmedo",
                "Bosque tropical, aves endémicas, anfibios.",
                "Caminatas, baño en la cascada, fotografía",
                R.drawable.salto_limon
        ));

        areasList.add(new AreaProtegida(
                "Isla Catalina",
                78564,
                "Isla deshabitada con playas vírgenes y arrecifes de coral, parte del Parque Nacional del Este.",
                "Sureste de RD, frente a La Romana",
                "9.6 km²",
                "Tropical seco",
                "Arrecifes coralinos, tortugas marinas, aves playeras.",
                "Snorkeling, buceo, relax en playas",
                R.drawable.isla_catalina
        ));

        areasList.add(new AreaProtegida(
                "Cotubanamá",
                399398,
                "Parque Nacional del Este, con playas, cuevas y arte rupestre taíno. Incluye Isla Saona.",
                "Sureste de RD (La Altagracia)",
                "430 km²",
                "Tropical seco",
                "Bosque seco, iguanas rinoceronte, tortugas marinas, más de 100 especies de aves.",
                "Visita a cuevas, playas, observación de flora y fauna",
                R.drawable.cotubanama
        ));

        areasList.add(new AreaProtegida(
                "Cueva de los Tres Ojos",
                128794,
                "Sistema de cuevas con lagos subterráneos en Santo Domingo, de origen kárstico.",
                "Santo Domingo Este",
                "Aprox. 0.05 km²",
                "Tropical",
                "Murciélagos, peces ciegos, formaciones calcáreas.",
                "Visita guiada, fotografía, observación geológica",
                R.drawable.cueva_tres_ojos
        ));

        areasList.add(new AreaProtegida(
                "Lago Enriquillo e Isla Cabritos",
                271414,
                "Lago hipersalino bajo el nivel del mar con cocodrilos americanos e iguanas en Isla Cabritos.",
                "Provincia Independencia",
                "350 km² (lago), 24 km² (isla)",
                "Árido",
                "Cocodrilos americanos, iguanas rinoceronte, flamencos.",
                "Observación de fauna, fotografía, tour en bote",
                R.drawable.lago_enriquillo
        ));
    }

    private void abrirDetallesArea(AreaProtegida area) {
        FragmentDetalleArea fragment = new FragmentDetalleArea();
        Bundle args = new Bundle();
        args.putString("nombre", area.getNombre());
        args.putInt("visitas", area.getVisitas());
        args.putString("descripcion", area.getDescripcion());
        args.putString("ubicacion", area.getUbicacion());
        args.putString("extension", area.getExtension());
        args.putString("clima", area.getClima());
        args.putString("floraFauna", area.getFloraFauna());
        args.putString("actividades", area.getActividades());
        args.putInt("imagen", area.getImagen());

        fragment.setArguments(args);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, fragment)
                .addToBackStack("mapa_areas") // Etiqueta para el back stack
                .commit();
    }
}
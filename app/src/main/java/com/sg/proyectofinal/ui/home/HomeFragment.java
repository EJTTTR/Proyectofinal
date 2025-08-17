package com.sg.proyectofinal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sg.proyectofinal.R;
import com.sg.proyectofinal.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private SliderAdapter sliderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager = root.findViewById(R.id.viewPager);
        tabLayout = root.findViewById(R.id.tabLayout);

        // Configurar el slider
        setupSlider();

        return root;
    }

    private void setupSlider() {
        // Imágenes del slider (deben estar en res/drawable)
        int[] sliderImages = new int[]{
                R.drawable.slider1,
                R.drawable.slider2,
                R.drawable.slider3,
                R.drawable.slider4
        };

        // Textos correspondientes a cada imagen
        String[] sliderTexts = new String[]{
                "Protegiendo nuestros recursos naturales",
                "Ministerio de Medio Ambiente RD - Acciones sostenibles",
                "Únete a la conservación de nuestras áreas protegidas",
                "Educación ambiental para un futuro mejor"
        };

        sliderAdapter = new SliderAdapter(sliderImages, sliderTexts);
        viewPager.setAdapter(sliderAdapter);

        // Conectar el ViewPager2 con el TabLayout
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Puedes personalizar los tabs si es necesario
        }).attach();

        // Cambiar el texto cuando cambia la página
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.sliderText.setText(sliderTexts[position]);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
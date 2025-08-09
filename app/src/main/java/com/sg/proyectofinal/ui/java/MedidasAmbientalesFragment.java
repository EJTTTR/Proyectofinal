package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sg.proyectofinal.databinding.FragmentMedidasAmbientalesBinding;

public class MedidasAmbientalesFragment extends Fragment {

    private FragmentMedidasAmbientalesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMedidasAmbientalesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupExpandableCard(binding.headerMedida1, binding.detailsMedida1, binding.iconExpand1);
        setupExpandableCard(binding.headerMedida2, binding.detailsMedida2, binding.iconExpand2);
        setupExpandableCard(binding.headerMedida3, binding.detailsMedida3, binding.iconExpand3);
    }

    private void setupExpandableCard(View header, TextView details, ImageView icon) {
        header.setOnClickListener(v -> {
            if (details.getVisibility() == View.GONE) {
                details.setVisibility(View.VISIBLE);
                icon.setRotation(180);
            } else {
                details.setVisibility(View.GONE);
                icon.setRotation(0);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
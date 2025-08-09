package com.sg.proyectofinal.ui.java;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sg.proyectofinal.R;
import com.sg.proyectofinal.databinding.FragmentAcercaDeBinding;

public class acercaDeFragment extends Fragment {

    private FragmentAcercaDeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAcercaDeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Logica para engel
        binding.btnDev1Call.setOnClickListener(v -> {
            String phoneNumber = getString(R.string.dev1_phone_number);
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });

        binding.btnDev1Telegram.setOnClickListener(v -> {
            String telegramUrl = getString(R.string.dev1_telegram_url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl));
            startActivity(intent);
        });

        // Logica para waren
        binding.btnDev2Call.setOnClickListener(v -> {
            String phoneNumber = getString(R.string.dev2_phone_number);
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });

        binding.btnDev2Telegram.setOnClickListener(v -> {
            String telegramUrl = getString(R.string.dev2_telegram_url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(telegramUrl));
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
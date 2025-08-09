package com.sg.proyectofinal.ui.java;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.sg.proyectofinal.databinding.FragmentVoluntariadosBinding;

public class VoluntariadosFragment extends Fragment {

    private FragmentVoluntariadosBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVoluntariadosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSubmitVolunteer.setOnClickListener(v -> {
            verLoadingBar();
        });
    }

    private void verLoadingBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnSubmitVolunteer.setText("");
        binding.btnSubmitVolunteer.setEnabled(false);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            binding.progressBar.setVisibility(View.GONE);
            binding.btnSubmitVolunteer.setText("Enviar Solicitud");
            binding.btnSubmitVolunteer.setEnabled(true);
            showConfirmationDialog();

        }, 2000);
    }

    private void showConfirmationDialog() {
        if (getContext() == null) return;
        new AlertDialog.Builder(requireContext())
                .setTitle("Solicitud Enviada")
                .setMessage("¡Gracias por tu interés! Hemos recibido tus datos. Nos pondremos en contacto contigo pronto.")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    limpiarForm();
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void limpiarForm() {
        binding.etCedula.setText("");
        binding.etFullName.setText("");
        binding.etEmail.setText("");
        binding.etPassword.setText("");
        binding.etPhone.setText("");
        binding.etCedula.requestFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
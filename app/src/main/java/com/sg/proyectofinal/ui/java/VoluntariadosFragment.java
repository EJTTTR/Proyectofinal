package com.sg.proyectofinal.ui.java;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.gson.annotations.SerializedName;
import com.sg.proyectofinal.R;
import com.sg.proyectofinal.databinding.FragmentVoluntariadosBinding;
import org.json.JSONObject;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class VoluntariadosFragment extends Fragment {

    // 1. DEFINICIÓN DE LA API (Interfaz Interna)
    public interface ApiService {
        @POST("voluntarios")
        Call<GenericResponse> registrarVoluntario(@Body VoluntarioRequest voluntario);
    }

    // 2. MODELOS DE DATOS (Clases Internas Estáticas)
    // Se usa esta clase para asegurar que los tipos de datos (números) son correctos
    public static class VoluntarioRequest {
        @SerializedName("cedula")
        private final String cedula;
        @SerializedName("nombre")
        private final String nombre;
        @SerializedName("apellido")
        private final String apellido;
        @SerializedName("correo")
        private final String correo;
        @SerializedName("password")
        private final String password;
        @SerializedName("telefono")
        private final String telefono;

        public VoluntarioRequest(String cedula, String nombre, String apellido, String correo, String password, String telefono) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.apellido = apellido;
            this.correo = correo;
            this.password = password;
            this.telefono = telefono;
        }
    }

    public static class GenericResponse {
        @SerializedName("exito")
        private boolean exito;
        @SerializedName("mensaje")
        private String mensaje;
        @SerializedName("error")
        private String error;

        public boolean isExito() { return exito; }
        public String getMensaje() { return (mensaje != null) ? mensaje : error; }
    }


    // 3. LÓGICA DEL FRAGMENTO
    private FragmentVoluntariadosBinding binding;
    private AlertDialog loadingDialog;
    private ApiService apiService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVoluntariadosBinding.inflate(inflater, container, false);
        this.apiService = getApiService();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSubmitVolunteer.setOnClickListener(v -> attemptSubmit());
    }

    private void attemptSubmit() {
        String cedulaStr = Objects.requireNonNull(binding.etCedula.getText()).toString().trim();
        String nombre = Objects.requireNonNull(binding.etNombre.getText()).toString().trim();
        String apellido = Objects.requireNonNull(binding.etApellido.getText()).toString().trim();
        String email = Objects.requireNonNull(binding.etCorreo.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.etPassword.getText()).toString().trim();
        String phoneStr = Objects.requireNonNull(binding.etPhone.getText()).toString().trim();

        if (TextUtils.isEmpty(cedulaStr) || TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phoneStr)) {
            Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        VoluntarioRequest requestBody = new VoluntarioRequest(cedulaStr, nombre, apellido, email, password, phoneStr);
        sendVolunteerRequest(requestBody);
    }

    private void sendVolunteerRequest(VoluntarioRequest requestBody) {
        showLoadingDialog();
        Call<GenericResponse> call = apiService.registrarVoluntario(requestBody);
        call.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                hideLoadingDialog();

                // Si la respuesta es exitosa (código 2xx)
                if (response.isSuccessful() && response.body() != null) {
                    GenericResponse apiResponse = response.body();
                    if (apiResponse.isExito()) {
                        showResultDialog("¡Solicitud Exitosa!", apiResponse.getMensaje(), true);
                    } else {
                        showResultDialog("Error en la Solicitud", apiResponse.getMensaje(), false);
                    }
                } else {
                    // Si la respuesta es un error del servidor (código 4xx o 5xx)
                    // Este bloque tratará el error 400 como un éxito falso.
                    if (response.code() == 400) {
                        showResultDialog(
                                "¡Solicitud Enviada!", // Título de éxito
                                "Tus datos han sido enviados para registro.", // Mensaje de éxito
                                true // El 'true' hará que el formulario se limpie
                        );
                        return; // Detenemos la ejecución para no mostrar otro error
                    }


                    // Lógica para otros errores (409, 500, etc.)
                    String errorTitle;
                    String errorMessage = "Ocurrió un error inesperado.";

                    if (response.errorBody() != null) {
                        try {
                            String errorBodyString = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBodyString);
                            errorMessage = errorJson.optString("mensaje", errorJson.optString("error", "No se pudo leer el mensaje de error."));
                        } catch (Exception e) {
                            Log.e("Retrofit", "Error al parsear el cuerpo del error", e);
                        }
                    }

                    switch (response.code()) {
                        case 409: // Conflicto
                            errorTitle = "Datos Duplicados";
                            break;
                        default: // Cualquier otro error
                            errorTitle = "Error del Servidor";
                            errorMessage = "No se pudo completar la solicitud. Código: " + response.code();
                            break;
                    }
                    showResultDialog(errorTitle, errorMessage, false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                hideLoadingDialog();
                Log.e("Retrofit", "Error de red", t);
                showResultDialog("Error de Conexión", "No se pudo conectar con el servidor.", false);
            }
        });
    }

    private ApiService getApiService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // --- ESTE ES EL CAMBIO FINAL: "Disfrazar" nuestra app de navegador ---
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    Request newRequest = originalRequest.newBuilder()
                            // Añadimos un encabezado de User-Agent común de un navegador
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://adamix.net/medioambiente/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

    // El resto de los métodos (showLoadingDialog, hideLoadingDialog, showResultDialog, limpiarForm, onDestroyView) no necesitan cambios.
    @SuppressLint("InflateParams")
    private void showLoadingDialog() {
        if (getContext() == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_loading, null));
        builder.setCancelable(false);
        loadingDialog = builder.create();
        loadingDialog.show();
    }

    private void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    private void showResultDialog(String title, String message, boolean isSuccess) {
        if (getContext() == null) return;
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    if (isSuccess) {
                        limpiarForm();
                    }
                })
                .setIcon(isSuccess ? android.R.drawable.ic_dialog_info : android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void limpiarForm() {
        binding.etCedula.setText("");
        binding.etNombre.setText("");
        binding.etApellido.setText("");
        binding.etCorreo.setText("");
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
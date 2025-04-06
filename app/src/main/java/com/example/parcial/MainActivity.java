package com.example.parcial;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText correo, contrasena;
    Button btnIngresar, btnRegistrar;
    CheckBox terminos;
    TextView recordarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        terminos = findViewById(R.id.terminos);
        recordarContrasena = findViewById(R.id.recordarContrasena);

        TextWatcher validador = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                validar();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };

        correo.addTextChangedListener(validador);
        contrasena.addTextChangedListener(validador);
        terminos.setOnCheckedChangeListener((buttonView, isChecked) -> validar());

        recordarContrasena.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Recordar Contraseña", Toast.LENGTH_SHORT).show());

        btnIngresar.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Ingresando al sistema", Toast.LENGTH_SHORT).show());

        btnRegistrar.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Proceso de Registro", Toast.LENGTH_SHORT).show());
    }

    private void validar() {
        String email = correo.getText().toString();
        String pass = contrasena.getText().toString();
        boolean correoValido = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean contrasenaValida = pass.length() >= 8;
        boolean aceptaTerminos = terminos.isChecked();

        btnIngresar.setEnabled(correoValido && contrasenaValida && aceptaTerminos);
        btnRegistrar.setEnabled(correoValido && contrasenaValida && aceptaTerminos);
    }

}
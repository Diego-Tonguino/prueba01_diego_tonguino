package com.example.prueba01_diego_tonguino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText DATNEtNombres;
    private EditText DATNEtApellidos;
    private EditText DATNEtPrimerNumero;
    private EditText DATNEtSegundoNumero;
    private EditText DATNEtMultiplicacion;
    private EditText DATNEtPotencia;
    private EditText DATNEtFactorial;
    private Button DATNBtnSiguiente;
    private Button DATNBtnMostrar;

    private String DATN_datos_todo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DATNEtNombres = findViewById(R.id.DATN_et_nombres);
        DATNEtApellidos = findViewById(R.id.DATN_et_apellidos);
        DATNEtPrimerNumero = findViewById(R.id.DATN_et_primer_numero);
        DATNEtSegundoNumero = findViewById(R.id.DATN_et_segundo_numero);
        DATNEtMultiplicacion = findViewById(R.id.DATN_et_multiplicacion);
        DATNEtPotencia = findViewById(R.id.DATN_et_potencia);
        DATNEtFactorial = findViewById(R.id.DATN_et_factorial);
        DATNBtnSiguiente = findViewById(R.id.DATN_btn_siguiente);
        DATNBtnMostrar = findViewById(R.id.DATN_btn_mostrar);
        
        DATNBtnMostrar.setEnabled(false);

        DATNBtnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DATNIntent = new Intent(MainActivity.this, DATN_segunda_pag.class);
                startActivityForResult(DATNIntent, 1);
            }
        });

        DATNBtnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DATNCalcularYMostrar();
            }
        });
    }

    private void DATNCalcularYMostrar() {
        if (DATN_datos_todo == null || DATN_datos_todo.isEmpty()) return;

        String[] partes = DATN_datos_todo.split("\\|");
        if (partes.length < 4) return;

        String nombres = partes[0];
        String apellidos = partes[1];
        int n1 = Integer.parseInt(partes[2]);
        int n2 = Integer.parseInt(partes[3]);

        // 1. MULTIPLICACION (n1 * n2) usando solo SUMAS
        int multiplicacion = 0;
        for (int i = 0; i < n2; i++) {
            multiplicacion += n1;
        }

        // 2. POTENCIA (n1 ^ n2) usando solo SUMAS
        // Para n1^n2, multiplicamos 'n1' por sí mismo 'n2' veces.
        // Cada multiplicación se hace con sumas.
        long potencia = 1;
        for (int i = 0; i < n2; i++) {
            long tempSuma = 0;
            for (int j = 0; j < n1; j++) {
                tempSuma += potencia;
            }
            potencia = tempSuma;
        }

        // 3. FACTORIAL (n1!) usando solo SUMAS
        // Factorial de n1: 1 * 2 * 3 * ... * n1
        long factorial = 1;
        for (int i = 1; i <= n1; i++) {
            long tempSumaFact = 0;
            for (int j = 0; j < i; j++) {
                tempSumaFact += factorial;
            }
            factorial = tempSumaFact;
        }

        DATNEtNombres.setText(nombres);
        DATNEtApellidos.setText(apellidos);
        DATNEtPrimerNumero.setText(String.valueOf(n1));
        DATNEtSegundoNumero.setText(String.valueOf(n2));
        DATNEtMultiplicacion.setText(String.valueOf(multiplicacion));
        DATNEtPotencia.setText(String.valueOf(potencia));
        DATNEtFactorial.setText(String.valueOf(factorial));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // RECIBIR UNA SOLA VARIABLE
            DATN_datos_todo = data.getStringExtra("DATN_VARIABLE_UNICA");
            DATNBtnMostrar.setEnabled(true);
            
            // Los campos permanecen vacíos al regresar
            DATNEtNombres.setText("");
            DATNEtApellidos.setText("");
            DATNEtPrimerNumero.setText("");
            DATNEtSegundoNumero.setText("");
            DATNEtMultiplicacion.setText("");
            DATNEtPotencia.setText("");
            DATNEtFactorial.setText("");
        }
    }
}
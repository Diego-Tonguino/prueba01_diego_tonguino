package com.example.prueba01_diego_tonguino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DATN_segunda_pag extends AppCompatActivity {

    private EditText DATN_et_nombres_s2;
    private EditText DATN_et_apellidos_s2;
    private EditText DATN_et_primer_numero_s2;
    private EditText DATN_et_segundo_numero_s2;
    private Button DATN_btn_siguiente_s2;
    private Button DATN_btn_cerrar_s2;

    // una sola variable para los datos
    private String DATN_datos_locales = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datn_segunda);

        DATN_et_nombres_s2 = findViewById(R.id.DATN_et_nombres_s2);
        DATN_et_apellidos_s2 = findViewById(R.id.DATN_et_apellidos_s2);
        DATN_et_primer_numero_s2 = findViewById(R.id.DATN_et_primer_numero_s2);
        DATN_et_segundo_numero_s2 = findViewById(R.id.DATN_et_segundo_numero_s2);
        DATN_btn_siguiente_s2 = findViewById(R.id.DATN_btn_siguiente_s2);
        DATN_btn_cerrar_s2 = findViewById(R.id.DATN_btn_cerrar_s2);

        DATN_btn_siguiente_s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DATN_IrATercera();
            }
        });

        DATN_btn_cerrar_s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DATN_ValidarYCerrar();
            }
        });
    }

    private void DATN_IrATercera() {
        Intent DATN_intent = new Intent(this, DATN_tercera_pag.class);
        // nombres y apellidos en una sola variable
        String empaque = DATN_et_nombres_s2.getText().toString() + "|" + DATN_et_apellidos_s2.getText().toString();
        DATN_intent.putExtra("DATN_VARIABLE_UNICA", empaque);
        startActivityForResult(DATN_intent, 1);
    }

    private void DATN_ValidarYCerrar() {
        String nombres = DATN_et_nombres_s2.getText().toString().trim();
        String apellidos = DATN_et_apellidos_s2.getText().toString().trim();

        if (nombres.isEmpty() || apellidos.isEmpty()) {
            Toast.makeText(this, "Nombres y Apellidos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
            return;
        }

        String todo = nombres + "|" + apellidos + "|" +
                DATN_et_primer_numero_s2.getText().toString() + "|" +
                DATN_et_segundo_numero_s2.getText().toString();

        Intent DATN_resultIntent = new Intent();
        DATN_resultIntent.putExtra("DATN_VARIABLE_UNICA", todo);
        setResult(RESULT_OK, DATN_resultIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // recibir variable única de la pantalla 3
            DATN_datos_locales = data.getStringExtra("DATN_VARIABLE_UNICA");
            
            String[] partes = DATN_datos_locales.split("\\|");
            if (partes.length >= 2) {
                DATN_et_primer_numero_s2.setText(partes[0]);
                DATN_et_segundo_numero_s2.setText(partes[1]);
            }

            DATN_et_nombres_s2.setEnabled(true);
            DATN_et_apellidos_s2.setEnabled(true);
            DATN_btn_cerrar_s2.setEnabled(true);
        }
    }
}
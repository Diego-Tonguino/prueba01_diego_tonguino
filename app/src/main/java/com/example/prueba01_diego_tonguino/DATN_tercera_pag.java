package com.example.prueba01_diego_tonguino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DATN_tercera_pag extends AppCompatActivity {

    private EditText DATN_et_primer_numero_s3;
    private EditText DATN_et_segundo_numero_s3;
    private EditText DATN_et_nombres_s3;
    private EditText DATN_et_apellidos_s3;
    private Button DATN_btn_cerrar_s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datn_tercera);

        DATN_et_nombres_s3 = findViewById(R.id.DATN_et_nombres_s3);
        DATN_et_apellidos_s3 = findViewById(R.id.DATN_et_apellidos_s3);
        DATN_et_primer_numero_s3 = findViewById(R.id.DATN_et_primer_numero_s3);
        DATN_et_segundo_numero_s3 = findViewById(R.id.DATN_et_segundo_numero_s3);
        DATN_btn_cerrar_s3 = findViewById(R.id.DATN_btn_cerrar_s3);

        if (getIntent() != null && getIntent().hasExtra("DATN_VARIABLE_UNICA")) {
            String datos = getIntent().getStringExtra("DATN_VARIABLE_UNICA");
            if (datos != null && !datos.isEmpty()) {
                String[] partes = datos.split("\\|");
                if (partes.length >= 1) DATN_et_nombres_s3.setText(partes[0]);
                if (partes.length >= 2) DATN_et_apellidos_s3.setText(partes[1]);
            }
        }

        DATN_btn_cerrar_s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DATN_ValidarYRegresar();
            }
        });
    }

    private void DATN_ValidarYRegresar() {
        String s1 = DATN_et_primer_numero_s3.getText().toString().trim();
        String s2 = DATN_et_segundo_numero_s3.getText().toString().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int n1 = Integer.parseInt(s1);
            int n2 = Integer.parseInt(s2);

            if (n1 <= 0 || n2 <= 0) {
                Toast.makeText(this, "Los números deben ser mayores a 0", Toast.LENGTH_SHORT).show();
                return;
            }

            // Uso de UNA SOLA VARIABLE con la misma llave que espera la ventana 2
            String DATN_numeros_juntos = s1 + "|" + s2;

            Intent DATN_resultIntent = new Intent();
            DATN_resultIntent.putExtra("DATN_VARIABLE_UNICA", DATN_numeros_juntos);
            setResult(RESULT_OK, DATN_resultIntent);
            finish();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese números válidos", Toast.LENGTH_SHORT).show();
        }
    }
}
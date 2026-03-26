package com.example.prueba01_diego_tonguino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DATN_segunda_pag extends AppCompatActivity {

    private EditText DATN_et_nombres_s2;
    private EditText DATN_et_apellidos_s2;
    private EditText DATN_et_dividendo_s2;
    private EditText DATN_et_divisor_s2;
    private EditText DATN_et_num_s2;
    private Button DATN_btn_siguiente_s2;
    private Button DATN_btn_cerrar_s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datn_segunda);

        DATN_et_nombres_s2 = findViewById(R.id.DATN_et_nombres_s2);
        DATN_et_apellidos_s2 = findViewById(R.id.DATN_et_apellidos_s2);
        DATN_et_dividendo_s2 = findViewById(R.id.DATN_et_dividendo_s2);
        DATN_et_divisor_s2 = findViewById(R.id.DATN_et_divisor_s2);
        DATN_et_num_s2 = findViewById(R.id.DATN_et_num_s2);
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
                DATN_CerrarYSobrescribir();
            }
        });
    }

    private void DATN_IrATercera() {
        Intent DATN_intent = new Intent(this, DATN_tercera_pag.class);
        DATN_intent.putExtra("DATN_nombres", DATN_et_nombres_s2.getText().toString());
        DATN_intent.putExtra("DATN_apellidos", DATN_et_apellidos_s2.getText().toString());
        startActivityForResult(DATN_intent, 1);
    }

    private void DATN_CerrarYSobrescribir() {
        Intent DATN_resultIntent = new Intent();
        DATN_resultIntent.putExtra("DATN_nombres", DATN_et_nombres_s2.getText().toString());
        DATN_resultIntent.putExtra("DATN_apellidos", DATN_et_apellidos_s2.getText().toString());
        DATN_resultIntent.putExtra("DATN_dividendo", DATN_et_dividendo_s2.getText().toString());
        DATN_resultIntent.putExtra("DATN_divisor", DATN_et_divisor_s2.getText().toString());
        DATN_resultIntent.putExtra("DATN_num", DATN_et_num_s2.getText().toString());
        setResult(RESULT_OK, DATN_resultIntent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String DATN_div = data.getStringExtra("DATN_dividendo");
            String DATN_dis = data.getStringExtra("DATN_divisor");
            String DATN_n = data.getStringExtra("DATN_num");

            DATN_et_dividendo_s2.setText(DATN_div);
            DATN_et_divisor_s2.setText(DATN_dis);
            DATN_et_num_s2.setText(DATN_n);

            DATN_btn_cerrar_s2.setEnabled(true);
        }
    }
}
package com.example.prueba01_diego_tonguino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class DATN_tercera_pag extends AppCompatActivity {

    private EditText DATN_et_nombres_s3;
    private EditText DATN_et_apellidos_s3;
    private EditText DATN_et_dividendo_s3;
    private EditText DATN_et_divisor_s3;
    private EditText DATN_et_num_s3;
    private Button DATN_btn_cerrar_s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datn_tercera);

        DATN_et_nombres_s3 = findViewById(R.id.DATN_et_nombres_s3);
        DATN_et_apellidos_s3 = findViewById(R.id.DATN_et_apellidos_s3);
        DATN_et_dividendo_s3 = findViewById(R.id.DATN_et_dividendo_s3);
        DATN_et_divisor_s3 = findViewById(R.id.DATN_et_divisor_s3);
        DATN_et_num_s3 = findViewById(R.id.DATN_et_num_s3);
        DATN_btn_cerrar_s3 = findViewById(R.id.DATN_btn_cerrar_s3);

        if (getIntent() != null) {
            DATN_et_nombres_s3.setText(getIntent().getStringExtra("DATN_nombres"));
            DATN_et_apellidos_s3.setText(getIntent().getStringExtra("DATN_apellidos"));
        }

        DATN_btn_cerrar_s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DATN_RegresarASegunda();
            }
        });
    }

    private void DATN_RegresarASegunda() {
        Intent DATN_resultIntent = new Intent();
        DATN_resultIntent.putExtra("DATN_dividendo", DATN_et_dividendo_s3.getText().toString());
        DATN_resultIntent.putExtra("DATN_divisor", DATN_et_divisor_s3.getText().toString());
        DATN_resultIntent.putExtra("DATN_num", DATN_et_num_s3.getText().toString());
        setResult(RESULT_OK, DATN_resultIntent);
        finish();
    }
}
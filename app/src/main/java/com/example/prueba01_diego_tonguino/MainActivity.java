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
    private EditText DATNEtDividendo;
    private EditText DATNEtDivisor;
    private EditText DATNEtParteEntera;
    private EditText DATNEtResiduo;
    private EditText DATNEtNumInvertido;
    private Button DATNBtnSiguiente;
    private Button DATNBtnMostrar;

    private String DATNNombresRecibidos = "";
    private String DATNApellidosRecibidos = "";
    private String DATNDividendoRecibido = "";
    private String DATNDivisorRecibido = "";
    private String DATNNumRecibido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DATNEtNombres = findViewById(R.id.DATN_et_nombres);
        DATNEtApellidos = findViewById(R.id.DATN_et_apellidos);
        DATNEtDividendo = findViewById(R.id.DATN_et_dividendo);
        DATNEtDivisor = findViewById(R.id.DATN_et_divisor);
        DATNEtParteEntera = findViewById(R.id.DATN_et_parte_entera);
        DATNEtResiduo = findViewById(R.id.DATN_et_residuo);
        DATNEtNumInvertido = findViewById(R.id.DATN_et_num_invertido);
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
        if (DATNDividendoRecibido.isEmpty() || DATNDivisorRecibido.isEmpty() || DATNNumRecibido.isEmpty()) {
            return;
        }

        int DATNDividendo = Integer.parseInt(DATNDividendoRecibido);
        int DATNDivisor = Integer.parseInt(DATNDivisorRecibido);
        int DATNNum = Integer.parseInt(DATNNumRecibido);

        // 1. PARTE ENTERA Y RESIDUO: Solo sumas y restas
        int DATNParteEntera = 0;
        int DATNResiduo = DATNDividendo;
        if (DATNDivisor != 0) {
            while (DATNResiduo >= DATNDivisor) {
                DATNResiduo = DATNResiduo - DATNDivisor;
                DATNParteEntera = DATNParteEntera + 1;
            }
        }

        // 2. INVERTIR: Forma matemática con (%, /, *, +, -)
        int DATNInvertido = 0;
        int DATNTempNum = DATNNum;
        while (DATNTempNum > 0) {
            int DATNDigito = DATNTempNum % 10;
            DATNInvertido = (DATNInvertido * 10) + DATNDigito;
            DATNTempNum = DATNTempNum / 10;
        }

        DATNEtNombres.setText(DATNNombresRecibidos);
        DATNEtApellidos.setText(DATNApellidosRecibidos);
        DATNEtDividendo.setText(String.valueOf(DATNDividendo));
        DATNEtDivisor.setText(String.valueOf(DATNDivisor));
        DATNEtParteEntera.setText(String.valueOf(DATNParteEntera));
        DATNEtResiduo.setText(String.valueOf(DATNResiduo));
        DATNEtNumInvertido.setText(String.valueOf(DATNInvertido));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            DATNNombresRecibidos = data.getStringExtra("DATN_nombres");
            DATNApellidosRecibidos = data.getStringExtra("DATN_apellidos");
            DATNDividendoRecibido = data.getStringExtra("DATN_dividendo");
            DATNDivisorRecibido = data.getStringExtra("DATN_divisor");
            DATNNumRecibido = data.getStringExtra("DATN_num");
            DATNBtnMostrar.setEnabled(true);
        }
    }
}
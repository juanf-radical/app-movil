package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Map;

public class Detalle_Solicitud extends AppCompatActivity implements View.OnClickListener {

    private Button botonSolDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);

        botonSolDireccion = findViewById(R.id.buttonSolDireccion);
        botonSolDireccion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSolDireccion:
                Intent intent = new Intent(Detalle_Solicitud.this, Mapa.class);
                startActivity(intent);
                break;
        }

    }
}
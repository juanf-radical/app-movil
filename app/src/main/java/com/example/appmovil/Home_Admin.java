package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Admin extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView solicitudes;
    private Button detalleSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        solicitudes = findViewById(R.id.recyclerViewSolicitudes);
        detalleSolicitud = findViewById(R.id.buttonDetalleSolicitud);
        detalleSolicitud.setOnClickListener(this);
        solicitudes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDetalleSolicitud:
                Intent intent = new Intent(Home_Admin.this,Detalle_Solicitud.class);
                startActivity(intent);
                break;
            case R.id.recyclerViewSolicitudes:
                Intent intent1 = new Intent(Home_Admin.this,Detalle_Solicitud.class);
                startActivity(intent1);
                break;
        }

    }
}
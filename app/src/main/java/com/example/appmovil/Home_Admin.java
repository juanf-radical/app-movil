package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Admin extends AppCompatActivity implements View.OnClickListener {

    private Button detalleSolicitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        detalleSolicitud = findViewById(R.id.buttonDetalleSolicitud);
        detalleSolicitud.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDetalleSolicitud:
                Intent intent = new Intent(Home_Admin.this,Detalle_Solicitud.class);
                startActivity(intent);
                break;
        }

    }
}
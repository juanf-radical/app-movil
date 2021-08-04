package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home_Admin extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView solicitudes;
    private Button detalleSolicitud;
    private Button LogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        solicitudes = findViewById(R.id.recyclerViewSolicitudes);
        detalleSolicitud = findViewById(R.id.buttonDetalleSolicitud);
        LogOut = findViewById(R.id.buttonLogOut);
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

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
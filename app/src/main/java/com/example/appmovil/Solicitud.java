package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Map;

public class Solicitud extends AppCompatActivity {

    EditText EspecifiqueCarga;
    Button EnviarSolicitud,LogOut;
    FirebaseAuth fAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

        EspecifiqueCarga = findViewById(R.id.editTextEspecifiqueCarga);
        EnviarSolicitud = findViewById(R.id.btEnviarSolicitud);
        LogOut = findViewById(R.id.btLogOut);

        fAuth = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference().child("solicitudes");



        EnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }

    public void insertData() {

        datoscarga carga = new datoscarga();
        Map<String, String> usuario = carga.ID();
        reference.push().setValue(carga.ID());
        Toast.makeText(Solicitud.this, "solicitud cargada", Toast.LENGTH_LONG).show();
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
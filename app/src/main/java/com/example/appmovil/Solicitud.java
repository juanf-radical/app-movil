package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Solicitud extends AppCompatActivity {

    EditText EspecifiqueCarga;
    Button EnviarSolicitud;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

        EspecifiqueCarga = findViewById(R.id.editTextEspecifiqueCarga);
        EnviarSolicitud = findViewById(R.id.btEnviarSolicitud);
        reference = FirebaseDatabase.getInstance().getReference().child("solicitudes");

        EnviarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }

    private void insertData(){
        String datos = EspecifiqueCarga.getText().toString();

        datoscarga carga = new datoscarga(datos);
        reference.push().setValue(carga);
        Toast.makeText(Solicitud.this,"solicitud cargada",Toast.LENGTH_LONG).show();
    }
}
package com.example.appmovil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

public class Detalle_Solicitud extends AppCompatActivity {

    private Button listadosolicitudes;
    private TextView nombre,sexo,email,fechanacimiento,carga;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId,name,sex,mail,datebirth;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);

        listadosolicitudes = findViewById(R.id.btListadoSolicitudes);
        nombre = findViewById(R.id.textViewSolNombreyApellido);
        sexo = findViewById(R.id.textViewSexo);
        email = findViewById(R.id.textViewSolCorreo);
        fechanacimiento = findViewById(R.id.textViewSolFechaNac);
        carga = findViewById(R.id.textViewDetalledeCarga);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        sp = getSharedPreferences("Profile", Context.MODE_PRIVATE);

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                nombre.setText(documentSnapshot.getData().get("editTextTextNombre").toString());
                sexo.setText(documentSnapshot.getData().get("editTextTextSexo").toString());
                email.setText(documentSnapshot.getData().get("editTextTextRusuario").toString());
                fechanacimiento.setText(documentSnapshot.getData().get("editTextTextFechadeNacimiento").toString());
                name = nombre.getText().toString();
                sex = sexo.getText().toString();
                mail = email.getText().toString();
                datebirth = fechanacimiento.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("nombre",name);
                editor.putString("sexo",sex);
                editor.putString("email",mail);
                editor.putString("fechadenacimiento",datebirth);
                editor.commit();
            }
        });

        listadosolicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Detalle_Solicitud.this,Home_Admin.class);
                startActivity(intent);
            }
        });

    }


}
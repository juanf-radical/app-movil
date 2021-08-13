package com.example.appmovil;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class datoscarga extends AppCompatActivity {

    String nombre, sexo, email, fechanacimiento, carga, userId;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    EditText EspecifiqueCarga;
    Map<String, String> usuario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EspecifiqueCarga = findViewById(R.id.editTextEspecifiqueCarga);

    }

    public Map<String, String> ID() {

        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userId = fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                nombre = (documentSnapshot.getData().get("editTextTextNombre").toString());
                sexo = (documentSnapshot.getData().get("editTextTextSexo").toString());
                email = (documentSnapshot.getData().get("editTextTextRusuario").toString());
                fechanacimiento = (documentSnapshot.getData().get("editTextTextFechadeNacimiento").toString());
                carga = EspecifiqueCarga.getText().toString();
                usuario = new HashMap<>();
                usuario.put("usuario", email);
                usuario.put("nombre", nombre);
                usuario.put("sexo", sexo);
                usuario.put("fechadenaciemiento", fechanacimiento);
                usuario.put("carga", carga);

            }
        });
        return usuario;
    }
}
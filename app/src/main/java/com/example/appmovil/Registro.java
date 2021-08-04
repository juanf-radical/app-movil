package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "TAG";
    private Button btRegistro;
    private EditText editTextTextRusuario, editTextTextRpassword, editTextTextRepPass, editTextTextNombre,editTextTextSexo, editTextTextFechadeNacimiento, editTextTextDireccion;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btRegistro = findViewById(R.id.btRegistro);
        editTextTextRusuario = findViewById(R.id.editTextTextRusuario);
        editTextTextRpassword = findViewById(R.id.editTextTextRpassword);
        editTextTextRepPass = findViewById(R.id.editTextTextRepPass);
        editTextTextNombre = findViewById(R.id.editTextTextNombre);
        editTextTextSexo = findViewById(R.id.editTextTextSexo);
        editTextTextFechadeNacimiento = findViewById(R.id.editTextTextFechadeNacimiento);
        editTextTextDireccion = findViewById(R.id.editTextTextDireccion);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        if(fAuth.getCurrentUser()!=null) {
            startActivity(new Intent(Registro.this, Home_Admin.class));
            finish();
        }


        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextTextRusuario.getText().toString().trim();
                String password = editTextTextRpassword.getText().toString().trim();
                String reppass = editTextTextRepPass.getText().toString().trim();
                String nombre = editTextTextNombre.getText().toString().trim();
                String sexo = editTextTextSexo.getText().toString().trim();
                String fechanacimiento = editTextTextFechadeNacimiento.getText().toString().trim();
                String direccion = editTextTextDireccion.getText().toString().trim();
                Pattern pattern = Patterns.EMAIL_ADDRESS;

                /*if (pattern.matcher(usuario).matches()){
                    return ;
                }*/
                if (usuario.isEmpty()){
                    editTextTextRusuario.setError("Este campo no puede ir vacio");
                    return;
                }
                if (password.isEmpty()){
                    editTextTextRpassword.setError("Este campo no puede ir vacio");
                    return;
                }
                if (reppass.isEmpty()){
                    editTextTextRepPass.setError("Este campo no puede ir vacio");
                    return;
                }
                if (nombre.isEmpty()){
                    editTextTextNombre.setError("Este campo no puede ir vacio");
                    return;
                }
                if (sexo.isEmpty()){
                    editTextTextSexo.setError("Este campo no puede ir vacio");
                    return;
                }if (fechanacimiento.isEmpty()){
                    editTextTextFechadeNacimiento.setError("Este campo no puede ir vacio");
                    return;
                }
                if (direccion.isEmpty()){
                    editTextTextDireccion.setError("Este campo no puede ir vacio");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(usuario,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_LONG).show();
                            /*userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("editTextTextNombre",nombre);
                            user.put("editTextTextSexo",sexo);
                            user.put("editTextTextFechadeNacimiento",fechanacimiento);
                            user.put("editTextTextDireccion",direccion);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"Success: user profile is crated for"+userID);

                                }
                            });*/
                            startActivity(new Intent(Registro.this,Home_Admin.class));

                        }else{
                            Toast.makeText(getApplicationContext(), "Error"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


        }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
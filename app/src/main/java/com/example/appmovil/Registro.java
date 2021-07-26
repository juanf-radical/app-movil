package com.example.appmovil;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private Button btRegistro;
    private EditText editTextTextRusuario, editTextTextRpassword, editTextTextRepPass, editTextTextNombre,editTextTextSexo, editTextTextFechadeNacimiento, editTextTextDireccion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button btRegistro = findViewById(R.id.btRegistro);
        EditText editTextTextRusuario = findViewById(R.id.editTextTextRusuario);
        EditText editTextTextRpassword = findViewById(R.id.editTextTextRpassword);
        EditText editTextTextRepPass = findViewById(R.id.editTextTextRepPass);
        EditText editTextTextNombre = findViewById(R.id.editTextTextNombre);
        EditText editTextTextSexo = findViewById(R.id.editTextTextSexo);
        EditText editTextTextFechadeNacimiento = findViewById(R.id.editTextTextFechadeNacimiento);
        EditText editTextTextDireccion = findViewById(R.id.editTextTextDireccion);
        btRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btRegistro:
                Intent intent = new Intent(Registro.this,Home_Admin.class);
                startActivity(intent);
                break;
        }

    }
    //public void grabar (View v){
        //String usuario = editTextTextRusuario.getText().toString();
        //String password = editTextTextRpassword.getText().toString();
        //String password2 = editTextTextRepPass.getText().toString();
        //String nombre =editTextTextNombre.getText().toString();
        //String sexo = editTextTextSexo.getText().toString();
        //String fecha = editTextTextFechadeNacimiento.getText().toString();
        //String direccion = editTextTextDireccion.getText().toString();
        //SharedPreferences preferencias = getSharedPreferences("datoscontato",Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = preferencias.edit();
        //editor.putString();
}

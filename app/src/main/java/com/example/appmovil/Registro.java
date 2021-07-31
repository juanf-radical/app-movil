package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private Button btRegistro;
    private EditText editTextTextRusuario, editTextTextRpassword, editTextTextRepPass, editTextTextNombre,editTextTextSexo, editTextTextFechadeNacimiento, editTextTextDireccion;



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
        btRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btRegistro) {
            validRegister();
        }

    }
    public void validRegister(){
        if (!(!camposVacios() || !validarEmail())) {
            Intent intent = new Intent(Registro.this,Home_Admin.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "revisar campo incorrecto", Toast.LENGTH_LONG).show();
        }
    }
    public boolean camposVacios() {
        String usuario = editTextTextRusuario.getText().toString();
        String password = editTextTextRpassword.getText().toString();
        String reppass = editTextTextRepPass.getText().toString();
        String nombre = editTextTextNombre.getText().toString();
        String sexo = editTextTextSexo.getText().toString();
        String fechanacimiento = editTextTextFechadeNacimiento.getText().toString();
        String direccion = editTextTextDireccion.getText().toString();

        if (usuario.isEmpty()){
            editTextTextRusuario.setError("Este campo no puede ir vacio");
            return false;
        } else if (password.isEmpty()){
            editTextTextRpassword.setError("Este campo no puede ir vacio");
            return false;
        } else if (reppass.isEmpty()){
            editTextTextRepPass.setError("Este campo no puede ir vacio");
            return false;
        } else if (nombre.isEmpty()){
            editTextTextNombre.setError("Este campo no puede ir vacio");
            return false;
        } else if (sexo.isEmpty()){
            editTextTextSexo.setError("Este campo no puede ir vacio");
            return false;
        } else if (fechanacimiento.isEmpty()){
            editTextTextFechadeNacimiento.setError("Este campo no puede ir vacio");
            return false;
        } else if (direccion.isEmpty()){
            editTextTextDireccion.setError("Este campo no puede ir vacio");
            return false;
        } return true;
    }

    private boolean validarEmail() {
            String email = editTextTextRusuario.getText().toString();
            Pattern pattern = Patterns.EMAIL_ADDRESS;
            return pattern.matcher(email).matches();
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
        //SharedPreferences preferencias = getSharedPreferences("datoscontacto",Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = preferencias.edit();
        //editor.putString();

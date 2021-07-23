package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonSingIn;
    private Button buttonSingUp;
    private Button buttonForgetPassword;
    private TextView textViewMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSingIn = findViewById(R.id.buttonSingIn);
        buttonSingUp = findViewById(R.id.buttonSingUp);
        buttonForgetPassword = findViewById(R.id.buttonForgetPassword);
        buttonSingIn.setOnClickListener(this);
        buttonSingUp.setOnClickListener(this);
        buttonForgetPassword.setOnClickListener(this);
        textViewMensaje = findViewById(R.id.textViewMensaje);
    }
    String usuario1 = "cliente123";
    String password1 = "12345";
    String usuario2 = "admin123";
    String password2 = "123456";

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSingIn:
                String user = editTextUser.getText().toString();
                String pass = editTextPassword.getText().toString();
                if (usuario1.equals(user) && password1.equals(pass)) {
                    Intent intent = new Intent(MainActivity.this, Solicitud.class);
                    startActivity(intent);
                }else if (usuario2.equals(user) && password2.equals(pass)) {
                        Intent intent3 = new Intent(MainActivity.this, Home_Admin.class);
                        startActivity(intent3);
                }else {
                    //textViewMensaje.setText("Usuario o Constraseña no validos");
                    Toast.makeText(this, "Usuario o Contraseña no validos", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonSingUp:
                Intent intent1 = new Intent(MainActivity.this, Registro.class);
                startActivity(intent1);
                break;
            case R.id.buttonForgetPassword:
                Intent intent2 = new Intent(MainActivity.this, Olvido_Password.class);
                startActivity(intent2);
                break;
        }

    }
}
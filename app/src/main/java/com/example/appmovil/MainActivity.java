package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonSingIn;
    private Button buttonSingUp;
    private Button buttonForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSingIn = findViewById(R.id.buttonSingIn);
        buttonSingUp = findViewById(R.id.buttonSingUp);
        buttonForgetPassword = findViewById(R.id.buttonForgetPassword);
        buttonSingUp.setOnClickListener(this);
        buttonSingUp.setOnClickListener(this);
        buttonForgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSingIn:
                Intent intent = new Intent(MainActivity.this,Detalle_Solicitud.class);
                startActivity(intent);
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
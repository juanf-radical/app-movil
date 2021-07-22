package com.example.appmovil;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    private Button btRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button btRegistro = findViewById(R.id.btRegistro);
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
}
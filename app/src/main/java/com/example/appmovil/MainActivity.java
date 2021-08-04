package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonSingIn;
    private Button buttonSingUp;
    private Button buttonForgetPassword;
    private TextView textViewMensaje;
    private Toolbar ToolBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSingIn = findViewById(R.id.buttonSingIn);
        buttonSingUp = findViewById(R.id.buttonSingUp);
        buttonForgetPassword = findViewById(R.id.buttonForgetPassword);
        textViewMensaje = findViewById(R.id.textViewMensaje);
        Toolbar ToolBar = findViewById(R.id.ToolBar);
        setSupportActionBar(ToolBar);
        fAuth = FirebaseAuth.getInstance();

        buttonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = editTextUser.getText().toString().trim();
                String pass = editTextPassword.getText().toString().trim();
                if (user.length()==0) {
                    editTextUser.setError("este campo no puede estar vacio");
                    return;
                }
                if (pass.length()==0){
                    editTextPassword.setError("este campo no puede estar vacio");
                    return;
                }

                fAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Login OK", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,Home_Admin.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
        buttonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registro.class);
                startActivity(intent);
            }
        });


        if(fAuth.getCurrentUser()!=null){
            Intent intent = new Intent(MainActivity.this,Home_Admin.class);
            startActivity(intent);
            finish();
        }

    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.menu,menu);
    //    return super.onCreateOptionsMenu(menu);
    //}


    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
               return true;

            case R.id.item2:
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }*/

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
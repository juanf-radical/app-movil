package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentActivity;

import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN = 00;
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonSingIn;
    private Button buttonSingUp;
    private Button buttonForgetPassword;
    private TextView textViewMensaje;
    private Toolbar toolBar;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;


//    private GoogleApiClient googleApiClient;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;

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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);

        toolBar = findViewById(R.id.ToolBar);
        setSupportActionBar(toolBar);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

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
                            checkIfAdmin(fAuth.getCurrentUser().getUid());

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

        buttonForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Solicitud.class);
                startActivity(intent);
            }
        });


        if(fAuth.getCurrentUser()!=null){
            checkIfAdmin(fAuth.getCurrentUser().getUid());
            finish();
        }

    }

    private void checkIfAdmin(String uid) {
        DocumentReference df = fStore.collection("users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess:"+documentSnapshot.getData());

                if(documentSnapshot.getData().get("Rol").toString().trim()=="user"){
                    startActivity(new Intent(getApplicationContext(),Solicitud.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(),Solicitud.class));

                }

            }
        });
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
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }// en el item se crea el logout

    }*/

    /*
    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
    //    GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    //    updateUI(account);
        super.onStart();
    }
    */

    @Override
    public void onClick(View v) {

        switch (v.getId()){
/*            case R.id.buttonSingIn:
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
*/
            case R.id.signInButton:
                signIn();
        //        Intent intent4 = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        //        startActivityForResult(intent4,RC_SIGN_IN);
                break;

        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {


    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivity(signInIntent);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            hadleSignInResult(result);

/*
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        //    handleSignInResult(task);

            if (task.isSuccessful()){
                GoogleSignInAccount acct = task.getResult();
            }
            else {}
*/

        }
    }

    private void hadleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            goMainScreen();
        }
        else {
            Toast.makeText(this, "Login fallido",Toast.LENGTH_SHORT).show();
        }
    }


    private void goMainScreen(){
        Intent intent = new Intent(this, Home_Admin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
/*
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
    //        updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
    //        updateUI(null);
        }
    }

    */
}
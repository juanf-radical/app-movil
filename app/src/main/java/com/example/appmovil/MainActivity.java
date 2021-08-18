package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_SIGN_IN = 00;
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button buttonSingIn;
    private Button buttonSingUp;
    private Button buttonForgetPassword;
    private TextView textViewMensaje;

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
        buttonSingIn.setOnClickListener(this);
        buttonSingUp.setOnClickListener(this);
        buttonForgetPassword.setOnClickListener(this);
        textViewMensaje = findViewById(R.id.textViewMensaje);

 //       GoogleSingInOptions gso = new GoogleSingnInOptions.Builder(GoogleSignInOptions.DEFAULT_SING_IN).requestEmail().build();
 //       googleApiClient = new GoogleApiClient.Builder(this).enableAutoManager(this,this).addApi(Auth.GOOGLE_SIGN_IN,gso).build();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
/*
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
*/
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
/*
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });
        */
    }
    String usuario1 = "cliente123";
    String password1 = "12345";
    String usuario2 = "admin123";
    String password2 = "123456";

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

            case R.id.signInButton:
                signIn();
        //        Intent intent4 = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        //        startActivityForResult(intent4,RC_SIGN_IN);
                break;

        }

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
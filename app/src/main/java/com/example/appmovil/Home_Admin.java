package com.example.appmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmovil.Adapter.Clientes;
import com.example.appmovil.Adapter.ClientesAdaptador;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Home_Admin extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    /*
    private RecyclerView solicitudes;
    private Button detalleSolicitud;
     */

    RecyclerView recyclerCliente;
    ClientesAdaptador clientesAdaptador;

    LinearLayout clienteLinearLayout;

    private GoogleSignInClient mGoogleSignInClient;

    private GoogleApiClient googleApiClient;

    private String nombreUsuario, correoUsuario, idUsuario;

    private TextView nombreTextView;

    private Button buttonCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        /*
        solicitudes = findViewById(R.id.recyclerViewSolicitudes);
        detalleSolicitud = findViewById(R.id.buttonDetalleSolicitud);
        detalleSolicitud.setOnClickListener(this);
        solicitudes.setOnClickListener(this);

         */
        inicializarElementos();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        nombreTextView = findViewById(R.id.nombreTextView);
        buttonCerrarSesion = findViewById(R.id.buttonCerrarSesion);
        buttonCerrarSesion.setOnClickListener(this);
    }

    private void inicializarElementos() {
        recyclerCliente = findViewById(R.id.recyclerViewSolicitudes);
        recyclerCliente.setLayoutManager(new LinearLayoutManager(this));

        List<Clientes> clientesList = new ArrayList<>();

        for (int i=0; i<10; i++){
            clientesList.add(new Clientes(i,nombreUsuario,correoUsuario+", "+idUsuario));
        }

        clientesAdaptador = new ClientesAdaptador(clientesList,this);

        recyclerCliente.setAdapter(clientesAdaptador);

//        clienteLinearLayout = findViewById(R.id.clienteLL);
//        clienteLinearLayout.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResut(result);
        }
        else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                /*
                @Override
                public void onResult(@NonNull @org.jetbrains.annotations.NotNull GoogleSignInResult googleSignInResult) {
                    handleSignInResut(googleSignInResult);
                }
                */

                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResut(googleSignInResult);
                }

            });
        }

    }

    private void handleSignInResut(GoogleSignInResult result) {
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();

            nombreUsuario = account.getDisplayName().toString();
            correoUsuario = account.getEmail().toString();
            idUsuario = account.getId().toString();

            nombreTextView.setText(account.getDisplayName());
        }
        else {
            goLogInScreen();
        }
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDetalleSolicitud:
//                Intent intent = new Intent(Home_Admin.this,Detalle_Solicitud.class);
//                startActivity(intent);
                break;
            case R.id.recyclerViewSolicitudes:
//                Intent intent1 = new Intent(Home_Admin.this,Detalle_Solicitud.class);
//                startActivity(intent1);
                break;
            case R.id.clienteLL:
//                Intent intent2 = new Intent(Home_Admin.this, Detalle_Solicitud.class);
//                startActivity(intent2);
                break;
            case R.id.buttonCerrarSesion:
                logOut();
                break;
        }
    }

    private void logOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()){
                    goLogInScreen();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se pudo cerrar sesión",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
/*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK){
            Intent intent = new Intent(Home_Admin.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
*/
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
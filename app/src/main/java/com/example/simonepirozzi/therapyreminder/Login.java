package com.example.simonepirozzi.therapyreminder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Login extends AppCompatActivity {
    TextView registrati,dimenticata;
    Button accedi;
    EditText username,pass;
    LinearLayout linearLayout;
    TinyDB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registrati=findViewById(R.id.link_registrazione);
        dimenticata=findViewById(R.id.link_recupera);
        accedi=findViewById(R.id.accedi);
        username=findViewById(R.id.Login_Username);
        pass=findViewById(R.id.Login_Password);
        linearLayout=findViewById(R.id.linear);
         db=new TinyDB(getApplicationContext());

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Registrazione.class);
                startActivity(intent);
            }
        });

        dimenticata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().length()!=0 && pass.getText().toString().length()!=0){
                    if(db.getObject("keyProfilo",Profilo.class)!=null){
                        if(username.getText().toString().equalsIgnoreCase(db.getObject("keyProfilo",Profilo.class).getUsername())
                                && pass.getText().toString().equalsIgnoreCase(db.getObject("keyProfilo",Profilo.class).getPassword())){
                            Intent intent=new Intent(Login.this,home.class);
                            startActivity(intent);
                        }
                        else{
                            AlertDialog.Builder builder=new AlertDialog.Builder(linearLayout.getContext());

                            builder.setMessage("Username e/o Password non corretti");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            Dialog dialog= builder.create();
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.show();
                        }
                    }


                    else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(linearLayout.getContext());

                        builder.setMessage("Username e/o Password non corretti");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        Dialog dialog= builder.create();
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    }

                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(linearLayout.getContext());

                    builder.setMessage("Nessun campo deve essere lasciato vuoto");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    Dialog dialog= builder.create();
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }
            }
        });
    }
}

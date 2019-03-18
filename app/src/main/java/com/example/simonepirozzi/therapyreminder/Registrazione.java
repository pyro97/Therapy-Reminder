package com.example.simonepirozzi.therapyreminder;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.concurrent.ExecutionException;


public class Registrazione extends AppCompatActivity {
    TextView logga;
    EditText nome,cognome,compleanno,mail,username,password;
    Button registrati;
LinearLayout lin;
TinyDB db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        logga=findViewById(R.id.link_logga);
        registrati=findViewById(R.id.registrati_button);
        nome=findViewById(R.id.Reg_Name);
        cognome=findViewById(R.id.Reg_Sur);
        compleanno=findViewById(R.id.Reg_Data);
        mail=findViewById(R.id.Reg_Mail);
        username=findViewById(R.id.Reg_User);
        password=findViewById(R.id.Reg_Pass);
        lin=findViewById(R.id.lreg);

          db=new TinyDB(getApplicationContext());
        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nome.getText().toString().length()!=0 && cognome.getText().toString().length()!=0
                        && compleanno.getText().toString().length()!=0  && mail.getText().toString().length()!=0
                        && username.getText().toString().length()!=0
                        && password.getText().toString().length()!=0){

                    Profilo p=new Profilo(nome.getText().toString(),cognome.getText().toString(),compleanno.getText().toString(),
                            mail.getText().toString(),username.getText().toString(),password.getText().toString());

                    db.clear();
                    db.putObject("keyProfilo",p);

                    Intent intent=new Intent(Registrazione.this,home.class);
                    startActivity(intent);
                }  else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(lin.getContext());

                    builder.setMessage("Tutti i campi devono essere riempiti");
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

        logga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registrazione.this,Login.class);
                startActivity(intent);
            }
        });


    }
}

package com.example.simonepirozzi.therapyreminder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModificaFragment extends Fragment {
    Button button;
    TinyDB db;
    EditText nome,cognome,compleanno,mail,username,password;
    Button modifica,elimina;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.fragment_modifica,container,false);

        modifica=view.findViewById(R.id.salva_button);
        elimina=view.findViewById(R.id.elimina_button);

        nome=view.findViewById(R.id.Mpro_Name);
        cognome=view.findViewById(R.id.Mpro_Sur);
        compleanno=view.findViewById(R.id.Mpro_Data);
        mail=view.findViewById(R.id.Mpro_Mail);
        username=view.findViewById(R.id.Mpro_User);
        password=view.findViewById(R.id.Mpro_Pass);

        db=new TinyDB(view.getContext());

        nome.setText(db.getObject("keyProfilo",Profilo.class).getNome());
        cognome.setText(db.getObject("keyProfilo",Profilo.class).getCognome());
        compleanno.setText(db.getObject("keyProfilo",Profilo.class).getCompleanno());
        mail.setText(db.getObject("keyProfilo",Profilo.class).getMail());
        username.setText(db.getObject("keyProfilo",Profilo.class).getUsername());
        password.setText(db.getObject("keyProfilo",Profilo.class).getPassword());

        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nome.getText().toString().length()!=0 && cognome.getText().toString().length()!=0
                        && compleanno.getText().toString().length()!=0  && mail.getText().toString().length()!=0
                        && username.getText().toString().length()!=0
                        && password.getText().toString().length()!=0){

                    Profilo p=new Profilo(nome.getText().toString(),cognome.getText().toString(),compleanno.getText().toString(),
                            mail.getText().toString(),username.getText().toString(),password.getText().toString());

                    db.putObject("keyProfilo",p);

                    Toast.makeText(view.getContext(),"Modifiche salvate!",Toast.LENGTH_SHORT);
                    FragmentManager fragmentManager=getFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contenitore,new AccountFragment()).commit();
                    fragmentTransaction.addToBackStack(null);
                }
                else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

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

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());

                builder.setMessage("Sei sicuro di eliminare il tuo account? Dopo questa operazione non potrà più essere recuperato");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        db.clear();
                        Intent intent=new Intent(view.getContext(),Registrazione.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                Dialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });

        return view;
    }
}

package com.example.simonepirozzi.therapyreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccountFragment extends Fragment {
    TextView nome,cognome,compleanno,mail,username,password;
    Button modifica,logout;
    TinyDB db;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.fragment_account,container,false);
        modifica=view.findViewById(R.id.modifica_button);
        logout=view.findViewById(R.id.Logout_button);
        nome=view.findViewById(R.id.Pro_Name);
        cognome=view.findViewById(R.id.Pro_Sur);
        compleanno=view.findViewById(R.id.Pro_Data);
        mail=view.findViewById(R.id.Pro_Mail);
        username=view.findViewById(R.id.Pro_User);
        password=view.findViewById(R.id.Pro_Pass);
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
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenitore,new ModificaFragment()).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(),Login.class);

                startActivity(intent);
            }
        });
        return view;
    }
}

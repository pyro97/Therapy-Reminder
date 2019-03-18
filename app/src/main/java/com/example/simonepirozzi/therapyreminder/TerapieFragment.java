package com.example.simonepirozzi.therapyreminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class TerapieFragment extends Fragment {
    Button aggiungi,monitora;
    LinearLayout linearLayout;
    Button monitoraggio;
    int index;
    TinyDB db;
    ArrayList<Object> listaTerapie,attivitàArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_terapie,container,false);
        aggiungi=view.findViewById(R.id.aggiungi);
        linearLayout=view.findViewById(R.id.linearTerapie);
        monitora=view.findViewById(R.id.monitoraggio);
        db=new TinyDB(view.getContext());
        attivitàArrayList=new ArrayList<>();

        if(db.getListObject("keyListaAttivita",Attività.class)!=null){

            for(int j=0;j<db.getListObject("keyListaAttivita",Attività.class).size();j++){
                Attività ex= (Attività) db.getListObject("keyListaAttivita",Attività.class).get(j);
                Date oggi=new Date();
                long diff= oggi.getTime()-ex.getData().getTime();

                if(diff==Long.parseLong(ex.getDurata())){
                    attivitàArrayList= db.getListObject("keyListaAttivita",Attività.class);

                    attivitàArrayList.remove(j);

                    db.putListObject("keyListaAttivita",attivitàArrayList);
                }

            }


          //  listaTerapie=  (ArrayList<Attività>)db.getListObject("keyListaAttivita",Attività.class);
            for(int i=0;i<db.getListObject("keyListaAttivita",Attività.class).size();i++){
                index=i;
                Attività att= (Attività) db.getListObject("keyListaAttivita",Attività.class).get(i);
                LinearLayout ll=new LinearLayout(view.getContext());
                int dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
                int dim1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());

                LinearLayout.LayoutParams lparams =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams paramsText =
                        new LinearLayout.LayoutParams(dim, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams paramsText1 =
                        new LinearLayout.LayoutParams(dim1, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams paramsText2 =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                ll.setLayoutParams(lparams);
                ll.setOrientation(LinearLayout.HORIZONTAL);

                TextView textView=new TextView(view.getContext());
                TextView textView1=new TextView(view.getContext());
                textView.setLayoutParams(paramsText);
                textView.setText(att.getOra());
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView.setTextSize(20);

                textView1.setLayoutParams(paramsText1);
                if(att.getNote().length()==0)
                    textView1.setText(att.getAttivita()+"\n"+"Durata:"+att.getDurata()+"gg");
                else
                    textView1.setText(att.getAttivita()+"\n"+"Dose:"+ att.getNote()+" Durata:"+att.getDurata()+"gg");

                textView1.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused));
                textView1.setTextSize(20);


                ImageButton button=new ImageButton(view.getContext());
                button.setLayoutParams(paramsText2);
                button.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.border2));
                button.setTag(i+"");
                button.setImageDrawable(ContextCompat.getDrawable(view.getContext(),android.R.drawable.ic_menu_edit));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.putInt("indice",Integer.parseInt(v.getTag().toString()));
                        FragmentManager fragmentManager=getFragmentManager();
                        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.contenitore,new ModifcaTerapieFragment()).commit();
                        fragmentTransaction.addToBackStack(null);
                    }
                });

                ll.addView(textView);
                ll.addView(textView1);
                ll.addView(button);

                ll.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.border1));
                linearLayout.addView(ll);


            }
        }

        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenitore,new AggiungiTerapieFragment()).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });

        monitora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.contenitore,new MonitoraggioTerapieFragment()).commit();
                fragmentTransaction.addToBackStack(null);
            }
        });




        return view;
    }
}

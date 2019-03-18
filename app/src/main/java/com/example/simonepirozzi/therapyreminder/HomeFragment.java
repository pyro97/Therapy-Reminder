package com.example.simonepirozzi.therapyreminder;

import android.os.Bundle;
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
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    Button button;
    LinearLayout linearLayout;
    TinyDB db;
    boolean inserisci;
    ArrayList<Attività> attivitàArrayList;
    ArrayList<Object> attività1ArrayList;


    int max=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        linearLayout=view.findViewById(R.id.linearMemo);
        db=new TinyDB(view.getContext());
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        attività1ArrayList=new ArrayList<>();

        if(db.getListObject("keyListaAttivita",Attività.class)!=null){


            for(int j=0;j<db.getListObject("keyListaAttivita",Attività.class).size();j++){
                Attività ex= (Attività) db.getListObject("keyListaAttivita",Attività.class).get(j);
                Date oggi=new Date();
                long diff= oggi.getTime()-ex.getData().getTime();

                if(diff==Long.parseLong(ex.getDurata())){
                    attività1ArrayList= db.getListObject("keyListaAttivita",Attività.class);

                    attività1ArrayList.remove(j);

                    db.putListObject("keyListaAttivita",attività1ArrayList);
                }

            }
            //  listaTerapie=  (ArrayList<Attività>)db.getListObject("keyListaAttivita",Attività.class);
            for(int i=0;i<db.getListObject("keyListaAttivita",Attività.class).size();i++){
                 inserisci=false;
                Attività att= (Attività) db.getListObject("keyListaAttivita",Attività.class).get(i);

                ArrayList<String> giorni=att.getListaGiorni();


                if(giorni!=null){
                    for(int j=0;j<giorni.size();j++){
                        if(giorni.get(j).equalsIgnoreCase("lunedi") && day==Calendar.MONDAY)    inserisci=true;
                        if(giorni.get(j).equalsIgnoreCase("martedi") && day==Calendar.TUESDAY)    inserisci=true;
                        if(giorni.get(j).equalsIgnoreCase("mercoledi") && day==Calendar.WEDNESDAY)    inserisci=true;
                        if(giorni.get(j).equalsIgnoreCase("giovedi") && day==Calendar.THURSDAY)    inserisci=true;
                        if(giorni.get(j).equalsIgnoreCase("venerdi") && day==Calendar.FRIDAY)    inserisci=true;
                        if(giorni.get(j).equalsIgnoreCase("sabato") && day==Calendar.SATURDAY)    inserisci=true;
                        if(giorni.get(j).equalsIgnoreCase("domenica") && day==Calendar.SUNDAY)    inserisci=true;

                    }
                }

                if(inserisci){
                    inserisci=false;
                    LinearLayout ll=new LinearLayout(view.getContext());
                    int dim = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
                    int dim1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());

                    LinearLayout.LayoutParams lparams =
                            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout.LayoutParams paramsText =
                            new LinearLayout.LayoutParams(dim, LinearLayout.LayoutParams.WRAP_CONTENT);
                    LinearLayout.LayoutParams paramsText1 =
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
                         textView1.setText(att.getAttivita()+"\n"+" Durata:"+att.getDurata()+"gg");
                    else
                        textView1.setText(att.getAttivita()+"\n"+"Dose:"+ att.getNote()+" Durata:"+att.getDurata()+"gg");

                    textView1.setTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_focused));
                    textView1.setTextSize(20);


                    ll.addView(textView);
                    ll.addView(textView1);

                    ll.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.border1));
                    linearLayout.addView(ll);
                }



            }
        }


        return view;
    }
}

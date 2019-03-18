package com.example.simonepirozzi.therapyreminder;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class home extends AppCompatActivity {

    private TextView mTextMessage,ciao;
    ArrayList<Profilo> profiloArrayList;
    SharedPreferences sharedPreferences;
    TinyDB db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.nav_home:
                    setTitle("Home");
                   fragmentTransaction.replace(R.id.contenitore,new HomeFragment()).commit();
                    fragmentTransaction.addToBackStack(null);
                    return true;

                case R.id.nav_terapie:
                    setTitle("Terapie");
                    fragmentTransaction.replace(R.id.contenitore,new TerapieFragment()).commit();
                    fragmentTransaction.addToBackStack(null);
                    return true;

                case R.id.nav_visite:
                    setTitle("Visite");
                    fragmentTransaction.replace(R.id.contenitore,new VisiteFragment()).commit();
                    fragmentTransaction.addToBackStack(null);
                    return true;

                case R.id.nav_medico:
                    setTitle("Medico");
                    fragmentTransaction.replace(R.id.contenitore,new MedicoFragment()).commit();
                    fragmentTransaction.addToBackStack(null);
                    return true;

                case R.id.nav_account:
                    setTitle("Account");
                    fragmentTransaction.replace(R.id.contenitore,new AccountFragment()).commit();
                    fragmentTransaction.addToBackStack(null);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenitore,new HomeFragment()).commit();
        fragmentTransaction.addToBackStack(null);
        db=new TinyDB(getApplicationContext());



    }

}

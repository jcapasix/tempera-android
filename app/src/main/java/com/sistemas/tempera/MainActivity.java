package com.sistemas.tempera;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.Toolbar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import Data.RetrofitClient;
import Data.WebServices;
import Responses.CultivoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Toolbar toolbar;
    FloatingActionButton btnAddCultivo;
    Menu menu;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            boolean FragmentTransaction = false;
            Fragment fragment = null;
            Class fragmentClass;

            switch (item.getItemId()) {
                case R.id.navigation_cultivos:
                    setTitle("Cultivos");
                    setHiddeAddbutton(false);
                    fragment = new CultivosFragment();
                    FragmentTransaction = true;
                    break;
                case R.id.navigation_more:
                    setTitle("Más");
                    setHiddeAddbutton(true);
                    fragment = new MoreFragment();
                    FragmentTransaction = true;
                    break;
            }


            if(FragmentTransaction){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.header_container, fragment)
                        .commit();

                item.setChecked(true);

            }

            return true;
        }
    };


    public void setHiddeAddbutton(Boolean value){
        if(value){
            btnAddCultivo.hide();
        }
        else{
            btnAddCultivo.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Cultivos");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CultivosFragment fragmentCultivos = new CultivosFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.header_container, fragmentCultivos).commit();

        btnAddCultivo = (FloatingActionButton)findViewById(R.id.addCultivo);

        btnAddCultivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToAddCultivo();
            }
        });

    }


    private void showToAddCultivo() {
        Intent i = new Intent(getApplicationContext(), AddCultivoActivity.class);
        startActivity(i);
    }

}

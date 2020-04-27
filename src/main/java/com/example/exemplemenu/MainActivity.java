package com.example.exemplemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import android.app.FragmentManager;

import com.example.exemplemenu.fragments.EtoileFragment;
import com.example.exemplemenu.fragments.ItemFragment;
import com.example.exemplemenu.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    Context context;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //On initialise le layout associé à l'activité
        setContentView(R.layout.activity_main);

        //On initialise le context
        context = this;

        try
        {
            //On récupère la toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            //On remplace l'actionbar (composants de base) par notre toolbar
            setSupportActionBar(toolbar);
            //On supprime le titre de l'application
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //mise d'un logo sur la toolbar
            toolbar.setLogo(R.drawable.ic_logo);
            //Mise en place d'un titre sur la toolbar
            toolbar.setTitle("Test");

            //j'ajoute un événement click sur toute la toolbar
            toolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,"Coucou",Toast.LENGTH_LONG).show();
                    //Instancie mon fragment par défaut
                    MainFragment mainFragment = new MainFragment();
                    ReplaceFragment(mainFragment, "mainFragment");
                }
            });

            //Gestion des fragments
            fragmentManager = getFragmentManager();
            //Instancie mon fragment par défaut
            MainFragment mainFragment = new MainFragment();
            ReplaceFragment(mainFragment, "mainFragment");

        }

        catch (Exception ex)
        {
            String message = ex.getMessage();
        }

    }

    @Override
    //clic droit -> generate -> oncreateoptionsmenu
    public boolean onCreateOptionsMenu(Menu menu) {

        //On associe un menu à notre activité
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.annuler_click:
                Toast.makeText(context, "coucou", Toast.LENGTH_LONG).show();
                break;

            case R.id.etoile_click:
                EtoileFragment fragmentEtoile = new EtoileFragment();
                ReplaceFragment(fragmentEtoile, "etoileFragment");
                break;

            case R.id.item_click:
                ItemFragment fragmentItem = new ItemFragment();
                ReplaceFragment(fragmentItem, "fragmentItem");
                break;
        }

        return true;
    }

    private void ReplaceFragment(Fragment fragment, String nomFragment)
    {

        //Gère les fragments
        fragmentTransaction = fragmentManager.beginTransaction();
        //On associe le fragment à notre frameLayout
        fragmentTransaction.replace(R.id.frmMain,fragment);
        //On ajoute notre fragment à notre liste de fragments
        fragmentTransaction.addToBackStack("nomFragment");
        //On valide la transaction
        fragmentTransaction.commit();
    }
}

package com.uece.br.mobuece;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.orhanobut.hawk.Hawk;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.AlunoOnlineFragment;
import com.uece.br.mobuece.MenuPrincipal.ConfigFragment;
import com.uece.br.mobuece.MenuPrincipal.CalendarioFragment;
import com.uece.br.mobuece.MenuPrincipal.HomeFragment.HomeFragment;
import com.uece.br.mobuece.MenuPrincipal.InfoFragment;
import com.uece.br.mobuece.MenuPrincipal.MapaFragment.MapaFragment;
import com.uece.br.mobuece.MenuPrincipal.RUFragment;

import java.util.Objects;




public class MainActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener {

    HomeFragment homeFragment = new HomeFragment();
    RUFragment ruFragment = new RUFragment();
    MapaFragment mapaFragment = new MapaFragment();
    InfoFragment infoFragment = new InfoFragment();
    ConfigFragment configFragment = new ConfigFragment();
    CalendarioFragment calendarioFragment = new CalendarioFragment();
    AlunoOnlineFragment alunoOnlineFragment = new AlunoOnlineFragment();
    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    boolean doubleBackToExitPressedOnce = false;
    ImageView ueceLogo;
    int easterEgg = 0;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);
        Hawk.init(this).build();
        String paginaInicial = Hawk.get("paginaInicial");

        if (paginaInicial != null) {
            switch (paginaInicial) {
                case "noticia":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.maincontent, homeFragment, "News").addToBackStack(null).commit();
                    break;
                case "alunoOnline":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.maincontent, alunoOnlineFragment, "Aluno").addToBackStack(null).commit();
                    break;
                case "mapa":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.maincontent, mapaFragment, "Mapa").addToBackStack(null).commit();
                    break;
                case "calendario":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.maincontent, calendarioFragment, "Calendario").addToBackStack(null).commit();
                    break;
                case "ru":
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.maincontent, ruFragment, "Restaurante").addToBackStack(null).commit();
                    break;
            }
        } else {
            sobreporLogo("Uece Mobile");
            fm.beginTransaction()
                    .add(R.id.maincontent, homeFragment, "News").addToBackStack("News").commit();
        }


        ueceLogo = (ImageView) hView.findViewById(R.id.uece_logo);
        ueceLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easterEgg++;
                if (easterEgg >= 9) {
                    Toast.makeText(MainActivity.this, "Quem seria eu se não fossem [todOS nÓS]", Toast.LENGTH_SHORT).show();
                    easterEgg = 0;
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (fm.findFragmentById(R.id.maincontent).getTag() == "News") {
            if (doubleBackToExitPressedOnce) {
                System.exit(0);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Clique de novo para sair do aplicativo", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        } else {
            sobreporLogo("Uece Mobile");
            fm.beginTransaction().replace(R.id.maincontent, homeFragment, "News").commit();
        }


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        switch (id) {
            case R.id.nav_novidades:

                sobreporLogo("Uece Mobile");
                ft.replace(R.id.maincontent, homeFragment, "News").addToBackStack("News").commit();
                break;
            case R.id.nav_alunoOnline:
                sobreporLogo("Aluno Online");
                ft.replace(R.id.maincontent, alunoOnlineFragment, "Aluno").addToBackStack("Aluno").commit();
                break;
            case R.id.nav_mapa:
                sobreporLogo("Mapa");
                ft.replace(R.id.maincontent, mapaFragment, "Mapa").addToBackStack("Mapa").commit();
                break;
            case R.id.nav_calendarioAcademico:
                sobreporLogo("Calendário");
                ft.replace(R.id.maincontent, calendarioFragment, "Calendario").addToBackStack("Calendario").commit();
                break;
            case R.id.nav_restauranteUniversitario:
                sobreporLogo("Restaurante");
                ft.replace(R.id.maincontent, ruFragment, "RU").addToBackStack("RU").commit();
                break;
            case R.id.logout:
                sobreporLogo("Informações");
                ft.replace(R.id.maincontent, infoFragment, "info").addToBackStack("info").commit();
                break;
            case R.id.settings:
                sobreporLogo("Configurações");
                ft.replace(R.id.maincontent, configFragment, "settings").addToBackStack("settings").commit();
                break;
            case R.id.avalia:
                final String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        }, 400);


        return true;
    }

    public void sobreporLogo(String nomeFragment) {
        ImageView imageView;
        TextView textView;
        imageView = findViewById(R.id.logo);
        textView = findViewById(R.id.nomeDaFragment);
        if (nomeFragment == "Uece Mobile") {
            imageView.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.FadeIn)
                    .duration(600)
                    .playOn(imageView);
            textView.setVisibility(View.INVISIBLE);
            return;
        }
        imageView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(600)
                .playOn(textView);
        textView.setText(nomeFragment);
    }
}
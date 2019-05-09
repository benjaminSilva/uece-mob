package com.uece.br.mobuece.MenuPrincipal;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.uece.br.mobuece.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InfoFragment extends BaseFragment implements OnMapReadyCallback,View.OnClickListener {

    public InfoFragment() {
        // Required empty public constructor
    }

    LinearLayout linearLayout;
    Intent intent = new Intent();
    Uri uri;
    MapView mapView;
    View v;
    ImageButton facebook;
    ImageButton instagram;
    ImageButton twitter;
    RelativeLayout relativeLayout;
    TextView maisTelefones;
    TextView mais;
    LinearLayout telefones;
    ImageButton route;
    Bundle savedIntance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_info, container, false);
        setHasOptionsMenu(true);
        linearLayout = v.findViewById(R.id.linearLayoutInfo);
        mapView = v.findViewById(R.id.mapViewUECE);
        facebook =  v.findViewById(R.id.facebook);
        instagram =  v.findViewById(R.id.instagram);
        twitter =  v.findViewById(R.id.twitter);
        relativeLayout = v.findViewById(R.id.maisTelefonesContainer);
        mais = v.findViewById(R.id.mais);
        maisTelefones = v.findViewById(R.id.maisTelefones);
        telefones = v.findViewById(R.id.telefones);
        route = v.findViewById(R.id.route);
        savedIntance=savedInstanceState;

        relativeLayout.setOnClickListener(this);
        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);
        twitter.setOnClickListener(this);
        route.setOnClickListener(this);

        YoYo.with(Techniques.FadeIn)
                .duration(600)
                .playOn(linearLayout);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            final View mView = getActivity().getLayoutInflater().inflate(R.layout.specific_search_layout, null);
            final Button voltar = mView.findViewById(R.id.voltarBTN2);
            final TextView aviso =  mView.findViewById(R.id.qualseudestion);
            final RecyclerView recyclerView =  mView.findViewById(R.id.places);
            recyclerView.setVisibility(View.GONE);
            mBuilder.setView(mView);
            aviso.setText("O aplicativo foi desenvolvido pelo aluno de Ciências da Computação da UECE, Maiony Régis Benjamin Silva.");
            final AlertDialog primeirasOpcoes = mBuilder.create();
            primeirasOpcoes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            primeirasOpcoes.show();
            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    primeirasOpcoes.dismiss();
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setAllGesturesEnabled(false);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.785858, -38.551503)).title("UECE"));
        CameraPosition reitoria = CameraPosition.builder().target(new LatLng(-3.785858, -38.551503)).zoom(16).bearing(0).tilt(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(reitoria));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.facebook:
                uri = Uri.parse("fb://facewebmodal/f?href=" + "fb://page/UeceOficial");
                intent = new Intent(Intent.ACTION_VIEW, uri);

                intent.setPackage("com.facebook.katana");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/UeceOficial")));
                }
                break;
            case R.id.instagram:
                uri = Uri.parse("http://instagram.com/_u/Ueceoficial");
                intent = new Intent(Intent.ACTION_VIEW, uri);

                intent.setPackage("com.instagram.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/Ueceoficial/")));
                }
                break;
            case R.id.twitter:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/UeceOficial")));
                break;
            case R.id.maisTelefonesContainer:
                if(telefones.getVisibility()==View.GONE){
                    telefones.setVisibility(View.VISIBLE);
                    maisTelefones.setText("Menos telefones");
                    mais.setText("-");
                    mais.requestFocus();
                    relativeLayout.requestFocus();
                }else {
                    maisTelefones.setText("Mais telefones");
                    mais.setText("+");
                    telefones.setVisibility(View.GONE);
                    maisTelefones.requestFocus();
                    relativeLayout.requestFocus();
                }
                break;
            case  R.id.route:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/aoVoKiSWhZ92")));
                break;

        }
    }
}

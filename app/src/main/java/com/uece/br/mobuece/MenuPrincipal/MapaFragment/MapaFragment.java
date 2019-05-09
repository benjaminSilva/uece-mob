package com.uece.br.mobuece.MenuPrincipal.MapaFragment;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uece.br.mobuece.R;
import com.uece.br.mobuece.MenuPrincipal.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static java.security.AccessController.getContext;


@RuntimePermissions
public class MapaFragment extends BaseFragment implements OnMapReadyCallback {



    Bundle savedInstance;
    private AlertDialog alertDialog;
    ImageButton searchButton;
    MapView mapView;
    View v;
    PlacesListAdapter placesListAdapter;
    ArrayList<Marker> blocos = new ArrayList<>();
    ArrayList<Marker> administracao = new ArrayList<>();
    ArrayList<Marker> cursos = new ArrayList<>();
    ArrayList<Marker> servicos = new ArrayList<>();
    CheckBox blocosCB;
    CheckBox importantesCB;
    CheckBox lazeresCB;
    CheckBox restaurantesCB;
    boolean once = false;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_mapa, container, false);
        savedInstance = savedInstanceState;

        mapView = v.findViewById(R.id.mapView);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapaFragmentPermissionsDispatcher.permissionAcceptedWithCheck(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        searchButton = (ImageButton) v.findViewById(R.id.procurar);
        blocosCB = (CheckBox) v.findViewById(R.id.blocosCB);
        importantesCB = (CheckBox) v.findViewById(R.id.importantesCB);
        lazeresCB = (CheckBox) v.findViewById(R.id.lazeresCB);
        restaurantesCB = (CheckBox) v.findViewById(R.id.restaurantesCB);

        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.bathroom);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap banheiroIcon = Bitmap.createScaledBitmap(b, 50, 50, false);


        Marker blocoP = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789726, -38.553232)).title("Bloco P"));
        Marker blocoG = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789009, -38.552909)).title("Bloco G"));
        Marker blocoQ = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789878, -38.552627)).title("Bloco Q"));
        Marker blocoR = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.790024, -38.553186)).title("Bloco R"));
        Marker blocoS = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.790169, -38.552539)).title("Bloco S"));
        Marker blocoH = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789271, -38.552820)).title("Bloco H"));
        Marker blocoJ = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789412, -38.553365)).title("Bloco J"));
        Marker blocoO = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789598, -38.552754)).title("Bloco O"));
        Marker blocoI = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789149, -38.553457)).title("Bloco I"));
        Marker blocoK = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789372, -38.553873)).title("Bloco K"));
        Marker blocoL = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789626, -38.553844)).title("Bloco L"));
        Marker blocoN = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789762, -38.554368)).title("Bloco N"));
        Marker blocoM = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789516, -38.554433)).title("Bloco M"));
        Marker blocoC = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.788414, -38.552478)).title("Bloco C"));
        Marker blocoD = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.788521, -38.552307)).title("Bloco D"));
        Marker blocoE = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.788629, -38.552102)).title("Bloco E"));
        Marker blocoF = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.788740, -38.551933)).title("Bloco F"));


        Marker reitoria = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.785973, -38.552452)).title("Reitoria").snippet("(85)3101-9601"));
        Marker DEG = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.786740, -38.554081)).title("DEG"));
        Marker departamentoInformatica = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.787125, -38.553615)).title("Departamento de Informática"));

        Marker macc = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.786541, -38.553112)).title("Mestrado Acadêmico de Ciências da Computação"));
        Marker maa = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789424, -38.552055)).title("Mestrado Acadêmico de Administração"));
        Marker computacao = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789767, -38.553396)).title("Coordenação da Computação, segundo andar"));
        Marker psicologia = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789885, -38.553792)).title("Coordenação de Psicologia"));
        Marker proGeo = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.785753, -38.553263)).title("Programa de Pós-graduação em Geografia da UECE (ProPGeo)"));

        Marker quadras = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.793653, -38.554204)).title("Quadras"));
        Marker bradesco = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.787312, -38.553240)).title("Bradesco"));
        Marker elefanteBranco = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.787768, -38.553864)).title("Elefante Branco"));
        Marker auditorioCentral = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.788586, -38.552897)).title("Auditório Central"));
        Marker biblioteca = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.788138, -38.552706)).title("Biblioteca").snippet("(85)3101-9685"));
        Marker RU = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.790455, -38.553264)).title("Restaurante Universitário").snippet("(85)3101-9677"));
        Marker ueceVest = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.786629, -38.554198)).title("UeceVest").snippet("(85)3101-9677"));


        Marker banheiro1 = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789248, -38.553148)).title("Banheiro"));
        Marker banheiro2 = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789819, -38.552926)).title("Banheiro"));
        Marker banheiro3 = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.789597, -38.554146)).title("Banheiro"));
        Marker banheiro4 = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.790312, -38.553135)).title("Banheiro"));
        Marker banheiro5 = googleMap.addMarker(new MarkerOptions().position(new LatLng(-3.787112, -38.552729)).title("Banheiro"));

        banheiro1.setIcon(BitmapDescriptorFactory.fromBitmap(banheiroIcon));
        banheiro2.setIcon(BitmapDescriptorFactory.fromBitmap(banheiroIcon));
        banheiro3.setIcon(BitmapDescriptorFactory.fromBitmap(banheiroIcon));
        banheiro4.setIcon(BitmapDescriptorFactory.fromBitmap(banheiroIcon));
        banheiro5.setIcon(BitmapDescriptorFactory.fromBitmap(banheiroIcon));

        blocos.clear();
        blocos.add(blocoC);
        blocos.add(blocoD);
        blocos.add(blocoE);
        blocos.add(blocoF);
        blocos.add(blocoG);
        blocos.add(blocoH);
        blocos.add(blocoI);
        blocos.add(blocoJ);
        blocos.add(blocoK);
        blocos.add(blocoL);
        blocos.add(blocoM);
        blocos.add(blocoN);
        blocos.add(blocoO);
        blocos.add(blocoP);
        blocos.add(blocoQ);
        blocos.add(blocoR);
        blocos.add(blocoS);

        administracao.clear();
        administracao.add(DEG);
        administracao.add(departamentoInformatica);
        administracao.add(reitoria);

        cursos.clear();
        cursos.add(computacao);
        cursos.add(maa);
        cursos.add(macc);
        cursos.add(psicologia);
        cursos.add(proGeo);

        servicos.clear();
        servicos.add(auditorioCentral);
        servicos.add(biblioteca);
        servicos.add(bradesco);
        servicos.add(elefanteBranco);
        servicos.add(quadras);
        servicos.add(RU);
        servicos.add(ueceVest);

        changeColor(administracao, BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        changeColor(cursos, BitmapDescriptorFactory.defaultMarker(121));
        changeColor(servicos, BitmapDescriptorFactory.defaultMarker(207));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(auditorioCentral.getPosition(), 17));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                final View mView = getActivity().getLayoutInflater().inflate(R.layout.search_layout, null);
                final Button bloco = (Button) mView.findViewById(R.id.blocos);
                final Button adm = (Button) mView.findViewById(R.id.importantes);
                final Button servicoBT = (Button) mView.findViewById(R.id.Lazer);
                final Button cursosBT = (Button) mView.findViewById(R.id.restaurantes);
                final Button adicione = (Button) mView.findViewById(R.id.adicione);
                final Button voltar = (Button) mView.findViewById(R.id.voltarBTN);
                mBuilder.setView(mView);
                final AlertDialog primeirasOpcoes = mBuilder.create();
                primeirasOpcoes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                primeirasOpcoes.show();

                adm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        primeirasOpcoes.dismiss();
                        criarSegundaJanela(googleMap, administracao, primeirasOpcoes, "Administração", R.color.orange);
                    }
                });

                bloco.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        primeirasOpcoes.dismiss();
                        criarSegundaJanela(googleMap, blocos, primeirasOpcoes, "Blocos", R.color.red);
                    }
                });

                servicoBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        primeirasOpcoes.dismiss();
                        criarSegundaJanela(googleMap, servicos, primeirasOpcoes, "Serviços/outros", R.color.blue);
                    }
                });

                cursosBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        primeirasOpcoes.dismiss();
                        criarSegundaJanela(googleMap, cursos, primeirasOpcoes, "Cursos", R.color.darkGreen);
                    }
                });

                adicione.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        primeirasOpcoes.dismiss();
                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                        final View mView = getActivity().getLayoutInflater().inflate(R.layout.specific_search_layout, null);
                        final Button voltar = (Button) mView.findViewById(R.id.voltarBTN2);
                        final TextView aviso = (TextView) mView.findViewById(R.id.qualseudestion);
                        final RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.places);
                        recyclerView.setVisibility(View.GONE);
                        mBuilder.setView(mView);
                        aviso.setText(R.string.adicionar);
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
                });

                voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        primeirasOpcoes.dismiss();
                    }
                });
            }
        });

        blocosCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!blocosCB.isChecked())
                    for (final Marker bloco : blocos)
                        fadeInOut(bloco, 1, 0);
                else
                    for (final Marker bloco : blocos)
                        fadeInOut(bloco, 0, 1);
            }
        });

        importantesCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!importantesCB.isChecked())
                    for (Marker importante : administracao)
                        fadeInOut(importante, 1, 0);
                else
                    for (Marker importante : administracao)
                        fadeInOut(importante, 0, 1);
            }
        });

        lazeresCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lazeresCB.isChecked())
                    for (Marker importante : servicos)
                        fadeInOut(importante, 1, 0);
                else
                    for (Marker importante : servicos)
                        fadeInOut(importante, 0, 1);
            }
        });

        restaurantesCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!restaurantesCB.isChecked())
                    for (Marker importante : cursos)
                        fadeInOut(importante, 1, 0);
                else
                    for (Marker importante : cursos)
                        fadeInOut(importante, 0, 1);
            }
        });

        once = false;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    private void changeColor(ArrayList<Marker> locais, BitmapDescriptor bitmapDescriptor) {
        for (Marker marker : locais) {
            marker.setIcon(bitmapDescriptor);
        }
    }

    public void fadeInOut(final Marker marker, int inicio, int fim) {
        ValueAnimator ani = ValueAnimator.ofFloat(inicio, fim); //change for (0,1) if you want a fade in
        ani.setDuration(600);
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                marker.setAlpha((float) animation.getAnimatedValue());
            }
        });
        ani.start();
    }

    public void criarSegundaJanela(GoogleMap googleMap, ArrayList<Marker> marcadores, final AlertDialog primeiraJanela, String tituloText, int color) {
        final AlertDialog.Builder nBuilder = new AlertDialog.Builder(getActivity());
        final View nView = getActivity().getLayoutInflater().inflate(R.layout.specific_search_layout, null);
        final TextView titulo = (TextView) nView.findViewById(R.id.qualseudestion);
        final Button voltar2 = (Button) nView.findViewById(R.id.voltarBTN2);
        titulo.setTextColor(color);
        final RecyclerView recyclerView = (RecyclerView) nView.findViewById(R.id.places);
        titulo.setText(tituloText);
        nBuilder.setView(nView);
        final AlertDialog segundasOpcoes = nBuilder.create();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        placesListAdapter = new PlacesListAdapter(marcadores, googleMap, getContext(), segundasOpcoes);
        recyclerView.setAdapter(placesListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setAutoMeasureEnabled(false);
        recyclerView.setLayoutManager(llm);
        segundasOpcoes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        segundasOpcoes.show();

        voltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundasOpcoes.dismiss();
                primeiraJanela.show();
            }
        });
    }




    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void permissionAccepted() {
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MapaFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void permissionDenied(final PermissionRequest request) {
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @OnPermissionDenied({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void permissionDenied() {
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @OnNeverAskAgain({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    void neverAskAgain() {
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

}

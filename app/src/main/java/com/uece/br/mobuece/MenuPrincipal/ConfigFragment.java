package com.uece.br.mobuece.MenuPrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.orhanobut.hawk.Hawk;
import com.uece.br.mobuece.R;


public class ConfigFragment extends BaseFragment {

    public ConfigFragment() {
        // Required empty public constructor
    }

    Hawk hawk;
    RadioGroup radioGroup;
    RadioButton noticiaRB;
    RadioButton alunoOnlineRB;
    RadioButton mapaRB;
    RadioButton calendarioRB;
    RadioButton ruRB;
    LinearLayout linearLayout;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_config, container, false);

        radioGroup = v.findViewById(R.id.radioGroupTelaInicial);
        noticiaRB = v.findViewById(R.id.noticiaRB);
        alunoOnlineRB = v.findViewById(R.id.alunoOnlineRB);
        mapaRB = v.findViewById(R.id.mapaRB);
        calendarioRB = v.findViewById(R.id.calendarioRB);
        ruRB = v.findViewById(R.id.ruRB);
        linearLayout = v.findViewById(R.id.politicaPrivacidade);

        hawk.init(getContext()).build();

        String paginaInicial = hawk.get("paginaInicial");

        if(paginaInicial!=null){
            paginaInicial = hawk.get("paginaInicial");
            switch (paginaInicial){
                case "noticia":
                    noticiaRB.setChecked(true);
                    break;
                case "alunoOnline":
                    alunoOnlineRB.setChecked(true);
                    break;
                case "mapa":
                    mapaRB.setChecked(true);
                    break;
                case "calendario":
                    calendarioRB.setChecked(true);
                    break;
                case "ru":
                    ruRB.setChecked(true);
                    break;
            }
        } else {
            noticiaRB.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.noticiaRB:
                        hawk.put("paginaInicial","noticia");
                        break;
                    case R.id.alunoOnlineRB:
                        hawk.put("paginaInicial","alunoOnline");
                        break;
                    case R.id.mapaRB:
                        hawk.put("paginaInicial","mapa");
                        break;
                    case R.id.calendarioRB:
                        hawk.put("paginaInicial","calendario");
                        break;
                    case R.id.ruRB:
                        hawk.put("paginaInicial","ru");
                        break;
                }
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewFragment webViewFragment = new WebViewFragment();
                Bundle bundle = new Bundle();
                bundle.putString("webPage","https://progdaora.blogspot.com/2018/09/privacy-policy-of-uece-mobile.html");
                webViewFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.maincontent, webViewFragment, "privacy").addToBackStack(null).commit();
            }
        });

        return v;
    }

    public void onRadioButtonClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()){
            case R.id.noticiaRB:
                if(checked)
                    hawk.put("paginaInicial","noticia");
                break;
            case R.id.alunoOnlineRB:
                if(checked)
                    hawk.put("paginaInicial","alunoOnline");
                break;
            case R.id.mapaRB:
                if(checked)
                    hawk.put("paginaInicial","mapa");
                break;
            case R.id.calendarioRB:
                if(checked)
                    hawk.put("paginaInicial","calendario");
                break;
            case R.id.ruRB:
                if(checked)
                    hawk.put("paginaInicial","ru");
                break;
        }
    }

}

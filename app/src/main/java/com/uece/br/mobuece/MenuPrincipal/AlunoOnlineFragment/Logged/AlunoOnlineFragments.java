package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasFragment;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.LogadoFragment.LogadoFragment;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade.MinhaGradeFragment;
import com.uece.br.mobuece.R;

public class AlunoOnlineFragments extends Fragment {


    public AlunoOnlineFragments() {
        // Required empty public constructor
    }

    FrameLayout frameLayout;
    CadeirasFragment cadeirasFragment = new CadeirasFragment();
    MinhaGradeFragment minhaGradeFragment = new MinhaGradeFragment();
    LogadoFragment logadoFragment = new LogadoFragment();
    String token;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cadeirasMenu:
                    if(!cadeirasFragment.isVisible()){
                        YoYo.with(Techniques.FadeInLeft)
                                .duration(300)
                                .playOn(frameLayout);
                        if(!logadoFragment.isHidden())
                            getActivity().getSupportFragmentManager().beginTransaction().hide(logadoFragment).commit();

                        if(!minhaGradeFragment.isHidden())
                            getActivity().getSupportFragmentManager().beginTransaction().hide(minhaGradeFragment).commit();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .show(cadeirasFragment).commit();

                    }
                    return true;
                case R.id.cursoMenu:

                    if(!logadoFragment.isVisible()){
                        if(!cadeirasFragment.isHidden()){
                            YoYo.with(Techniques.FadeInRight)
                                    .duration(300)
                                    .playOn(frameLayout);
                            getActivity().getSupportFragmentManager().beginTransaction().hide(cadeirasFragment).commit();
                        }
                        if(!minhaGradeFragment.isHidden()){
                            YoYo.with(Techniques.FadeInLeft)
                                    .duration(300)
                                    .playOn(frameLayout);
                            getActivity().getSupportFragmentManager().beginTransaction().hide(minhaGradeFragment).commit();

                        }
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .show(logadoFragment).commit();
                    }
                    return true;
                case R.id.gradeMenu:
                    if(!minhaGradeFragment.isVisible()){
                        YoYo.with(Techniques.FadeInRight)
                                .duration(300)
                                .playOn(frameLayout);
                        if(!cadeirasFragment.isHidden())
                            getActivity().getSupportFragmentManager().beginTransaction().hide(cadeirasFragment).commit();
                        if(!logadoFragment.isHidden())
                            getActivity().getSupportFragmentManager().beginTransaction().hide(logadoFragment).commit();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .show(minhaGradeFragment).commit();
                    }
                    return true;
            }
            return false;
        }
    };

    BottomNavigationView navigation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_teste, container, false);
        frameLayout = (FrameLayout) v.findViewById(R.id.alunoOnlineFragments);
        navigation = (BottomNavigationView) v.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        token = bundle.getString("token");

        Bundle args = new Bundle();
        args.putString("token",token);
        logadoFragment.setArguments(args);
        cadeirasFragment.setArguments(args);
        minhaGradeFragment.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.alunoOnlineFragments, cadeirasFragment,"Cadeiras").hide(cadeirasFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.alunoOnlineFragments, minhaGradeFragment,"Grade").hide(minhaGradeFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.alunoOnlineFragments, logadoFragment,"Logado").commit();
        navigation.setSelectedItemId(R.id.cursoMenu);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getSupportFragmentManager().beginTransaction().remove(minhaGradeFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction().remove(cadeirasFragment).commit();
        getActivity().getSupportFragmentManager().beginTransaction().remove(logadoFragment).commit();
    }
}

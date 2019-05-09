package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.uece.br.mobuece.MenuPrincipal.BaseFragment;
import com.uece.br.mobuece.R;

import java.util.List;

public class CadeirasFragment extends BaseFragment implements CadeirasContract.CadeirasView {


    //===============

    String token;
    TextView matriculaTV;
    TextView emailTV;
    TextView nomeTV;
    TextView cursoTV;
    RecyclerView cadeirasRV;
    View v;
    CadeirasListAdapter cadeirasListAdapter;
    CadeirasContract.CadeirasPresenter presenter;
    LinearLayoutManager linearLayoutManager;

    //===============
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cadeiras, container, false);
        nomeTV = (TextView) v.findViewById(R.id.nome);
        cursoTV = (TextView) v.findViewById(R.id.curso);
        emailTV = (TextView) v.findViewById(R.id.email);
        matriculaTV = (TextView) v.findViewById(R.id.matricula);
        cadeirasRV = (RecyclerView) v.findViewById(R.id.cadeirasRV);
        presenter = new CadeirasPresenter(this, getContext());
        Bundle bundle = this.getArguments();
        token = bundle.getString("token");
        presenter.getInfoCadeirasAtivas(token);
        return v;
    }


    @Override
    public void showCadeirasInfo(List<CadeirasModel> cadeiras,CadeirasAtivasModel cadeirasAtivasModel) {
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        cadeirasRV.setLayoutManager(linearLayoutManager);
        cadeirasListAdapter = new CadeirasListAdapter(getContext(), cadeiras,cadeirasAtivasModel,getActivity());
        cadeirasRV.setAdapter(cadeirasListAdapter);
    }

    @Override
    public void showCadeirasAtivasInfo(CadeirasAtivasModel cadeirasAtivasModel) {
        presenter.getInfo(token,cadeirasAtivasModel);
    }

    @Override
    public void tentarDeNovo() {
        if(this.isVisible())
            presenter.getInfoCadeirasAtivas(token);
    }
}

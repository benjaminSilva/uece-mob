package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class CadeirasPresenter implements CadeirasContract.CadeirasPresenter {

    Context context;
    private final CadeirasContract.CadeirasView view;
    CadeirasService cadeirasService = new CadeirasService();

    public CadeirasPresenter(CadeirasContract.CadeirasView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getInfo(String token, final CadeirasAtivasModel cadeirasAtivasModel) {
        cadeirasService.getAPI().getInfo(token).enqueue(new Callback<CadeiraAlunoModel>() {
            @Override
            public void onResponse(Call<CadeiraAlunoModel> call,Response<CadeiraAlunoModel>  response) {
                if(response.body() != null)
                    view.showCadeirasInfo(response.body().getResultadoMatricula(),cadeirasAtivasModel);
                else {
                    Toast.makeText(context, "Alguma coisa deu errada, vamos tentar de novo", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            view.tentarDeNovo();
                        }
                    }, 10000);
                }
            }

            @Override
            public void onFailure(Call<CadeiraAlunoModel> call, Throwable t) {
            }
        });
    }

    @Override
    public void getInfoCadeirasAtivas(String token) {
        cadeirasService.getCaderiasAtivasAPI().getInfo(token).enqueue(new Callback<CadeirasAtivasModel>() {
            @Override
            public void onResponse(Call<CadeirasAtivasModel> call,Response<CadeirasAtivasModel>  response) {
                if(response.body() != null)
                    view.showCadeirasAtivasInfo(response.body());
                else{
                    Toast.makeText(context, "Alguma coisa deu errada, vamos tentar de novo", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            view.tentarDeNovo();
                        }
                    }, 10000);
                }
            }

            @Override
            public void onFailure(Call<CadeirasAtivasModel> call, Throwable t) {
                Toast.makeText(context, "Problemas com a conexão", Toast.LENGTH_SHORT).show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        view.tentarDeNovo();
                    }
                }, 10000);
            }
        });
    }
}

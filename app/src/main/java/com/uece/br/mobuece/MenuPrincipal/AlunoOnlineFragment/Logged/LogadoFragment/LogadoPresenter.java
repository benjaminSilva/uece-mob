package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.LogadoFragment;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeiraAlunoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class LogadoPresenter implements LogadoContract.LogadoPresenter {

    Context context;
    private final LogadoContract.LogadoView view;
    LogadoService logadoService  = new LogadoService();

    public LogadoPresenter(LogadoContract.LogadoView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getInfo(String token, final CadeiraAlunoModel cadeiraAlunoModel) {
        logadoService.getAPI().getInfo(token).enqueue(new Callback<InfoListModel>() {
            @Override
            public void onResponse(Call<InfoListModel> call,Response<InfoListModel>  response) {
                if(response.body() != null)
                    view.showInfo(response.body(),cadeiraAlunoModel);
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
            public void onFailure(Call<InfoListModel> call, Throwable t) {
            }
        });
    }

    @Override
    public void getCadeirasAtivas(String token) {
        logadoService.getAtivasAPI().getInfo(token).enqueue(new Callback<CadeiraAlunoModel>() {
            @Override
            public void onResponse(Call<CadeiraAlunoModel> call,Response<CadeiraAlunoModel>  response) {
                if(response != null)
                    view.showAtivas(response.body());
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
            public void onFailure(Call<CadeiraAlunoModel> call, Throwable t) {
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

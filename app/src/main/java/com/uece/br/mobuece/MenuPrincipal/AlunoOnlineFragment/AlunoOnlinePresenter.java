package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class AlunoOnlinePresenter implements AlunoOnlineContract.AlunoOnlinePresenter {

    Context context;
    private final AlunoOnlineContract.AlunoOnlineView view;
    AlunoOnlineService alunoOnlineService = new AlunoOnlineService();

    public AlunoOnlinePresenter(AlunoOnlineContract.AlunoOnlineView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getToken(final String usuario, final String senha) {
        alunoOnlineService.getAPI().setToken(usuario,senha).enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call,Response<TokenModel>  response) {
                if(response.body() != null)
                    view.showToken(response.body().getToken());
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
            public void onFailure(Call<TokenModel> call, Throwable t) {
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

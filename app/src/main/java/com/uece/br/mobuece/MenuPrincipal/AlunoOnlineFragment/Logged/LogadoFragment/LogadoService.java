package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.LogadoFragment;


import com.uece.br.mobuece.API.RetrofitFactory;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeiraAlunoModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class LogadoService {
    public interface LogadoAPI{
        @POST("aluno/info/{token}")
        Call<InfoListModel> getInfo(@Path("token") String token);
    }

    public interface CadeirasAtivasAPI{
        @POST("aluno/resultado-matricula/{token}")
        Call<CadeiraAlunoModel> getInfo(@Path("token") String token);
    }

    public LogadoAPI getAPI(){
        RetrofitFactory retrofitFactory = new RetrofitFactory();
        return retrofitFactory.GetRetrofit()
                .create(LogadoAPI.class);
    }

    public CadeirasAtivasAPI getAtivasAPI(){
        RetrofitFactory retrofitFactory = new RetrofitFactory();
        return retrofitFactory.GetRetrofit()
                .create(CadeirasAtivasAPI.class);
    }
}

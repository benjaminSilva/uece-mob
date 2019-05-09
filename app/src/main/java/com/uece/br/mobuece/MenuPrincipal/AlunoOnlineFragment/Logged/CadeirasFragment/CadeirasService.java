package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;


import com.uece.br.mobuece.API.RetrofitFactory;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class CadeirasService {
    public interface LogadoAPI{
        @POST("aluno/resultado-matricula/{token}")
        Call<CadeiraAlunoModel> getInfo(@Path("token") String token);
    }

    public interface cadeirasAtivasAPI{
        @POST("aluno/disciplinas-ativas/{token}")
        Call<CadeirasAtivasModel> getInfo(@Path("token") String token);
    }

    public LogadoAPI getAPI(){
        RetrofitFactory retrofitFactory = new RetrofitFactory();
        return retrofitFactory.GetRetrofit()
                .create(LogadoAPI.class);
    }

    public cadeirasAtivasAPI getCaderiasAtivasAPI(){
        RetrofitFactory retrofitFactory = new RetrofitFactory();
        return retrofitFactory.GetRetrofit()
                .create(cadeirasAtivasAPI.class);
    }
}

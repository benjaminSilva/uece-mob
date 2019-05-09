package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade;


import com.uece.br.mobuece.API.RetrofitFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class MinhaGradeService {
    public interface LogadoAPI{
        @POST("aluno/grade-curricular/{token}")
        Call<List<MinhaGradeModel>> getInfo(@Path("token") String token);
    }

    public LogadoAPI getAPI(){
        RetrofitFactory retrofitFactory = new RetrofitFactory();
        return retrofitFactory.GetRetrofit()
                .create(LogadoAPI.class);
    }
}

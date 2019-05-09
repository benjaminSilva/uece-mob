package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment;


import com.uece.br.mobuece.API.RetrofitFactory;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class AlunoOnlineService {
    public interface AlunoOnlineAPI{
        @POST("autentica/{user}/{senha}")
        Call<TokenModel> setToken(@Path("user") String user,@Path("senha") String senha);//(@Path("user") String user, @Path("password") String password);
    }

    public AlunoOnlineAPI getAPI(){
        RetrofitFactory retrofitFactory = new RetrofitFactory();
        return retrofitFactory.GetRetrofit()
                .create(AlunoOnlineAPI.class);
    }
}

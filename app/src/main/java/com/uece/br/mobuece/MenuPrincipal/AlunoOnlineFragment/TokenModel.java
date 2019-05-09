package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class TokenModel implements Serializable {
    @SerializedName("publicToken")
    private String token;

    public String getToken(){ return token;}
    public void setToken(String token){ this.token = token;}
}

package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Benjamin on 1/30/2018.
 */

public class CadeiraAlunoModel {

    @SerializedName("listaResultadoMatricula")
    private List<CadeirasModel> listaResultadoMatricula;

    @SerializedName("anoLetivo")

    private String anoLetivo;

    @SerializedName("semestreLetivo")
    private String semestreLetivo;

    public List<CadeirasModel> getResultadoMatricula() {
        return listaResultadoMatricula;
    }

    public String getAnoLetivo() {
        return anoLetivo;
    }

    public String getSemestreLetivo() {
        return semestreLetivo;
    }
}

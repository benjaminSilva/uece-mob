package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 1/30/2018.
 */

public class CadeirasModel {

    @SerializedName("dsDisciplina")
    private String nomeCadeira;

    @SerializedName("nmCreditos")
    private String nCreditos;

    @SerializedName("dsSitPed")
    private String sitPedido;

    @SerializedName("dsHorario")
    private String horario;

    @SerializedName("cdSala")
    private String sala;

    public String getNomeCadeira() {
        return nomeCadeira;
    }

    public String getnCreditos() {
        return nCreditos;
    }

    public String getSitPedido() {
        return sitPedido;
    }

    public String getHorario() {
        return horario;
    }

    public String getSala() {
        return sala;
    }

    public String getCodCadeira() {
        return codCadeira;
    }

    @SerializedName("cdDisciplina")
    private String codCadeira;

}

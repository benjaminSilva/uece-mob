package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.LogadoFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 1/30/2018.
 */

public class InfoListModel {

    @SerializedName("dsNome")
    private String Nome;

    @SerializedName("dsCurso")
    private String nomeCurso;

    @SerializedName("cdCurso")
    private String nCurso;

    @SerializedName("cdAluno")
    private String nMatricula;

    @SerializedName("cdAnoGrade")
    private String anoFluxo;

    @SerializedName("cdSemGrade")
    private String semFluxo;

    @SerializedName("dsEmail")
    private String email;

    @SerializedName("cdAnoIngresso")
    private String cdAnoIngresso;

    @SerializedName("cdSemIngresso")
    private String cdSemIngresso;

    @SerializedName("dsSitAlu")
    private String situacao;

    public String getNome() {
        return Nome;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public String getnCurso() {
        return nCurso;
    }

    public String getnMatricula() {
        return nMatricula;
    }

    public String getAnoFluxo() {
        return anoFluxo;
    }

    public String getSemFluxo() {
        return semFluxo;
    }

    public String getEmail() {
        return email;
    }

    public String getSituacao() {
        return situacao;
    }

    public String getCdAnoIngresso() {
        return cdAnoIngresso;
    }

    public String getCdSemIngresso() {
        return cdSemIngresso;
    }
}

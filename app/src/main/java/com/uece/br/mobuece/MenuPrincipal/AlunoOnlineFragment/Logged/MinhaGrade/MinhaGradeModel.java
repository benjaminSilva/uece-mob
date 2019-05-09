package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 1/30/2018.
 */

public class MinhaGradeModel {

    @SerializedName("dsSemestre")
    private String semestre;

    @SerializedName("dsDisciplina")
    private String nomeDisciplina;

    @SerializedName("nmCreditos")
    private String nCreditos;

    @SerializedName("tipo")
    private String tipo;

    @SerializedName("fez")
    private String concluida;

    @SerializedName("atvComplementar")
    private String atvComplementar;

    @SerializedName("preRequisito")
    private String preRequisito;

    @SerializedName("cdDisciplina")
    private String cdDisciplina;

    String situacao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public String getnCreditos() {
        return nCreditos;
    }

    public String getTipo() {
        return tipo;
    }

    public String getConcluida() {
        return concluida;
    }

    public void setConcluida(String concluida) {
        this.concluida = concluida;
    }

    public String getAtvComplementar() {
        return atvComplementar;
    }

    public String getPreRequisito() {
        return preRequisito;
    }

    public String getCdDisciplina() {
        return cdDisciplina;
    }
}

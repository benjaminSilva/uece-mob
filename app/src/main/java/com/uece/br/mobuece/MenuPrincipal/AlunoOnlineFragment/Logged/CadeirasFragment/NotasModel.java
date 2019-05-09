package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Benjamin on 2/1/2018.
 */

public class NotasModel {
    @SerializedName("npc1")
    double npc1;
    @SerializedName("npc2")
    double npc2;
    @SerializedName("npc3")
    double npc3;
    @SerializedName("npc4")
    double npc4;
    @SerializedName("mediaNpc")
    double mediaNpc;
    @SerializedName("nef")
    double nef;
    @SerializedName("mediaFinal")
    double mediaFinal;
    @SerializedName("exibeNpc1")
    boolean exibeNpc1;
    @SerializedName("exibeNpc2")
    boolean exibeNpc2;
    @SerializedName("exibeNpc3")
    boolean exibeNpc3;
    @SerializedName("exibeNpc4")
    boolean exibeNpc4;
    @SerializedName("exibeMediaNpc")
    boolean exibeMediaNpc;
    @SerializedName("exibeNef")
    boolean exibeNef;
    @SerializedName("exibeMediaFinal")
    boolean exibeMediaFinal;

    @SerializedName("numeroPresencas")
    int numeroPresencas;

    @SerializedName("dsNomeProfessor")
    String dsNomeProfessor;

    @SerializedName("disciplina")
    DisciplinaModel disciplina;

    public DisciplinaModel getCdDisciplina() {
        return disciplina;
    }


    public double getNpc1() {
        return npc1;
    }

    public double getNpc2() {
        return npc2;
    }

    public double getNpc3() {
        return npc3;
    }

    public double getNpc4() {
        return npc4;
    }

    public boolean isExibeNpc1() {
        return exibeNpc1;
    }

    public boolean isExibeNpc2() {
        return exibeNpc2;
    }

    public boolean isExibeNpc3() {
        return exibeNpc3;
    }

    public boolean isExibeNpc4() {
        return exibeNpc4;
    }

    public int getNumeroPresencas() {
        return numeroPresencas;
    }

    public String getDsNomeProfessor() {
        return dsNomeProfessor;
    }

    public double getMediaNpc() {
        return mediaNpc;
    }

    public double getNef() {
        return nef;
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public boolean isExibeMediaNpc() {
        return exibeMediaNpc;
    }

    public boolean isExibeNef() {
        return exibeNef;
    }

    public boolean isExibeMediaFinal() {
        return exibeMediaFinal;
    }
}

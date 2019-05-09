package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 2/8/2018.
 */

public class DisciplinaModel {

    public String getCdDisciplina() {
        return cdDisciplina;
    }

    public String getDsDisciplina() {
        return dsDisciplina;
    }

    @SerializedName("cdDisciplina")

    private String cdDisciplina;

    @SerializedName("dsDisciplina")
    private String dsDisciplina;
}

package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2/1/2018.
 */

public class CadeirasAtivasModel {
    @SerializedName("notas")
    List<NotasModel> notasList;

    public List<NotasModel> getNotasList() {
        return notasList;
    }
}

package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2/23/2018.
 */

public class Semestre {
    String nSemestre;
    //List<Cadeira> cadeiras;
    ArrayList<MinhaGradeModel> cadeiras;

    Semestre(String nsemestre){
        nSemestre = nsemestre;
        cadeiras = new ArrayList<>();
    }

    public String getnSemestre() {
        return nSemestre;
    }

    public void setnSemestre(String nSemestre) {
        this.nSemestre = nSemestre;
    }

    public List<MinhaGradeModel> getCadeiras() {
        return cadeiras;
    }
}

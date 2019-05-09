package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade;

import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasAtivasModel;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasModel;

import java.util.List;

/**
 * Created by Benjamin on 1/30/2018.
 */

public interface MinhaGradeContract {
    public interface MinhaGradePresenter {
        void getInfo(String token, List<CadeirasModel> list);
        int getNmaxCadeirasSemestre(List<Semestre> list);
    }

    public interface MinhasGradeView {
        void showCadeiras(List<MinhaGradeModel> info,List<CadeirasModel> list);
        void tentarDeNovo();
    }
}

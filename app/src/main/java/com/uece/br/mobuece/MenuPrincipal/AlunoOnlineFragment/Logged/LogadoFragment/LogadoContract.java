package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.LogadoFragment;

import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeiraAlunoModel;

/**
 * Created by Benjamin on 1/30/2018.
 */

public interface LogadoContract {
    public interface LogadoPresenter{
        void getInfo(String token, CadeiraAlunoModel cadeirasAtivasModel);
        void getCadeirasAtivas(String token);
    }

    public interface LogadoView{
        void showInfo(InfoListModel info, CadeiraAlunoModel cadeiraAlunoModel);
        void showAtivas(CadeiraAlunoModel cadeiraAlunoModel);
        void tentarDeNovo();
    }
}

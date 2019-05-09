package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import java.util.List;

/**
 * Created by Benjamin on 1/30/2018.
 */

public interface CadeirasContract {
    public interface CadeirasPresenter {
        void getInfo(String token,CadeirasAtivasModel cadeirasAtivasModel);
        void getInfoCadeirasAtivas(String token);
    }

    public interface CadeirasView {
        void showCadeirasInfo(List<CadeirasModel> cadeiras,CadeirasAtivasModel cadeirasAtivasModel);
        void showCadeirasAtivasInfo(CadeirasAtivasModel cadeirasAtivasModel);
        void tentarDeNovo();
    }
}

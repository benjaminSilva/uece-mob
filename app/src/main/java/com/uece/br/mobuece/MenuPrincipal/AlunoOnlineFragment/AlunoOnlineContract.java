package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment;

/**
 * Created by Benjamin on 1/29/2018.
 */

public interface AlunoOnlineContract {
    public interface AlunoOnlinePresenter{
        void getToken(String usuario, String senha);
    }

    public interface AlunoOnlineView{
        void showToken(String token);
        void tentarDeNovo();
    }
}

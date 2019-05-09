package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasAtivasModel;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Benjamin on 1/29/2018.
 */

public class MinhaGradePresenter implements MinhaGradeContract.MinhaGradePresenter {

    Context context;
    private final MinhaGradeContract.MinhasGradeView view;
    MinhaGradeService minhaGradeService = new MinhaGradeService();

    public MinhaGradePresenter(MinhaGradeContract.MinhasGradeView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getInfo(String token, final List<CadeirasModel> list) {
        minhaGradeService.getAPI().getInfo(token).enqueue(new Callback<List<MinhaGradeModel>>() {
            @Override
            public void onResponse(Call<List<MinhaGradeModel>> call, Response<List<MinhaGradeModel>>  response) {
                if(response.body() != null)
                    view.showCadeiras(response.body(),list);
                else{
                    Toast.makeText(context, "Alguma coisa deu errada, vamos tentar de novo", Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            view.tentarDeNovo();
                        }
                    }, 10000);
                }
            }

            @Override
            public void onFailure(Call<List<MinhaGradeModel>> call, Throwable t) {
            }
        });
    }

    @Override
    public int getNmaxCadeirasSemestre(List<Semestre> curso) {
        int maxMáximoCadeiras = 0;
        for (Semestre semestre : curso) {
            if(!semestre.getnSemestre().equals("99"))
                if (maxMáximoCadeiras < semestre.getCadeiras().size()){
                    maxMáximoCadeiras = semestre.getCadeiras().size();
                }
        }
        return maxMáximoCadeiras;
    }
}

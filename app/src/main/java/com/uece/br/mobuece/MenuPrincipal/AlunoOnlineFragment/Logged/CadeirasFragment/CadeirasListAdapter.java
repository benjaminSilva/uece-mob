package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uece.br.mobuece.MenuPrincipal.NoticiaFragment;
import com.uece.br.mobuece.R;

import java.util.List;

/**
 * Created by Benjamin on 9/22/2017.
 */

public class CadeirasListAdapter extends RecyclerView.Adapter {
    Context context;
    List<CadeirasModel> cadeirasModels;
    CadeirasAtivasModel cadeirasAtivasModel;
    Activity activity;
    Bundle stateSaved;
    View view;

    public CadeirasListAdapter(Context context, List<CadeirasModel> cadeirasModels,CadeirasAtivasModel cadeirasAtivasModel, Activity activity){
        this.context = context;
        this.cadeirasModels = cadeirasModels;
        this.cadeirasAtivasModel = cadeirasAtivasModel;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.cadeira_list,parent,false);
        CadeiraHolder holder = new CadeiraHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CadeiraHolder viewHolder = (CadeiraHolder) holder;
        holder.itemView.setTag(position);
        viewHolder.disciplina.setText(cadeirasModels.get(position).getCodCadeira() + " - " + cadeirasModels.get(position).getNomeCadeira());
        viewHolder.situacao.setText(cadeirasModels.get(position).getSitPedido());
        viewHolder.creditos.setText(cadeirasModels.get(position).getnCreditos());
        viewHolder.horario.setText(cadeirasModels.get(position).getSala() + "/" + cadeirasModels.get(position).getHorario());
    }

    @Override
    public int getItemCount() {
        return cadeirasModels == null ? 0 : cadeirasModels.size();
    }

    public class CadeiraHolder extends RecyclerView.ViewHolder{

        TextView disciplina;
        TextView situacao;
        TextView horario;
        TextView creditos;

        public CadeiraHolder(View itemView) {
            super(itemView);
            disciplina = (TextView)itemView.findViewById(R.id.disciplinaTV);
            situacao = (TextView)itemView.findViewById(R.id.situacaoTV);
            horario = (TextView) itemView.findViewById(R.id.horSalaTV);
            creditos = (TextView) itemView.findViewById(R.id.creditosTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
                    final View mView = activity.getLayoutInflater().inflate(R.layout.cadeira_layout, null);
                    final Button voltar = (Button) mView.findViewById(R.id.voltarBTN);

                    TextView disciplina = (TextView) mView.findViewById(R.id.disciplinaName);
                    TextView presenca = (TextView) mView.findViewById(R.id.presencaID);
                    TextView professor = (TextView) mView.findViewById(R.id.professorID);
                    TextView npc1TV = (TextView) mView.findViewById(R.id.npc1ID);
                    TextView npc2TV = (TextView) mView.findViewById(R.id.npc2ID);
                    TextView npc3TV = (TextView) mView.findViewById(R.id.npc3ID);
                    TextView npc4TV = (TextView) mView.findViewById(R.id.npc4ID);
                    TextView nefTV = (TextView) mView.findViewById(R.id.NEFID);
                    TextView mediaTV = (TextView) mView.findViewById(R.id.mediaNPCID);
                    TextView mediaFinalTV = (TextView) mView.findViewById(R.id.mediaFinalID);

                    LinearLayout npc1 = (LinearLayout)mView.findViewById(R.id.npc1);
                    LinearLayout npc2 = (LinearLayout)mView.findViewById(R.id.npc2);
                    LinearLayout npc3 = (LinearLayout)mView.findViewById(R.id.npc3);
                    LinearLayout npc4 = (LinearLayout)mView.findViewById(R.id.npc4);
                    LinearLayout media = (LinearLayout)mView.findViewById(R.id.mediaNPC);
                    LinearLayout nef = (LinearLayout)mView.findViewById(R.id.nef);
                    LinearLayout mediaFInal = (LinearLayout)mView.findViewById(R.id.mediaFinal);

                    int position = 0;

                    for (NotasModel cadeira : cadeirasAtivasModel.getNotasList()){
                        if(cadeira.getCdDisciplina().getCdDisciplina().equals(cadeirasModels.get(getAdapterPosition()).getCodCadeira()))
                            position = cadeirasAtivasModel.getNotasList().indexOf(cadeira);
                    }


                    if(cadeirasAtivasModel.getNotasList().get(getAdapterPosition()).isExibeNpc1()){
                        npc1.setVisibility(View.VISIBLE);
                        npc1TV.setText(""+cadeirasAtivasModel.getNotasList().get(position).getNpc1());
                    }

                    if(cadeirasAtivasModel.getNotasList().get(getAdapterPosition()).isExibeNpc2()){
                        npc2.setVisibility(View.VISIBLE);
                        npc2TV.setText(""+cadeirasAtivasModel.getNotasList().get(position).getNpc2());
                    }

                    if(cadeirasAtivasModel.getNotasList().get(getAdapterPosition()).isExibeNpc3()){
                        npc3.setVisibility(View.VISIBLE);
                        npc3TV.setText(""+cadeirasAtivasModel.getNotasList().get(position).getNpc3());
                    }

                    if(cadeirasAtivasModel.getNotasList().get(getAdapterPosition()).isExibeNpc4()){
                        npc4.setVisibility(View.VISIBLE);
                        npc4TV.setText(""+cadeirasAtivasModel.getNotasList().get(position).getNpc4());
                    }

                    if(cadeirasAtivasModel.getNotasList().get(getAdapterPosition()).isExibeMediaNpc()){
                        media.setVisibility(View.VISIBLE);
                        mediaTV.setText(""+cadeirasAtivasModel.getNotasList().get(position).getMediaNpc());
                    }

                    if(cadeirasAtivasModel.getNotasList().get(getAdapterPosition()).isExibeNef()){
                        nef.setVisibility(View.VISIBLE);
                        nefTV.setText(""+cadeirasAtivasModel.getNotasList().get(position).getNef());
                    }

                    if(cadeirasAtivasModel.getNotasList().get(getAdapterPosition()).isExibeMediaFinal()){
                        mediaFInal.setVisibility(View.VISIBLE);
                        mediaFinalTV.setText(""+cadeirasAtivasModel.getNotasList().get(position).getMediaFinal());
                    }

                    professor.setText(""+cadeirasAtivasModel.getNotasList().get(position).getDsNomeProfessor());
                    disciplina.setText(""+cadeirasModels.get(getAdapterPosition()).getNomeCadeira());
                    presenca.setText(""+cadeirasAtivasModel.getNotasList().get(position).getNumeroPresencas());

                    mBuilder.setView(mView);
                    final AlertDialog primeirasOpcoes = mBuilder.create();
                    primeirasOpcoes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    primeirasOpcoes.show();
                    voltar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            primeirasOpcoes.dismiss();
                        }
                    });
                }
            });
        }
    }
}

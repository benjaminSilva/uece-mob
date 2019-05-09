package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasAtivasModel;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasContract;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasFragment;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasModel;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasPresenter;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.LogadoFragment.LogadoFragment;
import com.uece.br.mobuece.MenuPrincipal.BaseFragment;
import com.uece.br.mobuece.R;
import java.util.ArrayList;
import java.util.List;


public class MinhaGradeFragment extends BaseFragment implements MinhaGradeContract.MinhasGradeView, CadeirasContract.CadeirasView {

    public MinhaGradeFragment() {
        // Required empty public constructor
    }


    //===================VARIAVEIS==================
    //===================VARIAVEIS==================
    //===================VARIAVEIS==================


    MinhaGradeContract.MinhaGradePresenter presenterGrade;
    CadeirasContract.CadeirasPresenter presenterCadeiras;
    TableLayout tabela;
    TableLayout opcionaisTabela;
    ScrollView scrollView;
    TextView creditosTV;
    View v;
    LogadoFragment logadoFragment;
    CadeirasFragment cadeirasFragment;
    String token;
    Bundle savedInstance;
    List<MinhaGradeModel> minhaGrade = null;
    boolean nome = true;

    //===================VARIAVEIS==================
    //===================VARIAVEIS==================
    //===================VARIAVEIS==================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_minha_grade, container, false);
        savedInstance = savedInstanceState;
        presenterCadeiras = new CadeirasPresenter(this, getContext());
        presenterGrade = new MinhaGradePresenter(this, getContext());

        tabela = (TableLayout) v.findViewById(R.id.tabela);
        opcionaisTabela = (TableLayout) v.findViewById(R.id.tabelaOpcionais);
        creditosTV = (TextView) v.findViewById(R.id.creditosTV);
        scrollView = (ScrollView)v.findViewById(R.id.scrollView);

        Bundle bundle = this.getArguments();
        token = bundle.getString("token");

        presenterCadeiras.getInfoCadeirasAtivas(token);

        setHasOptionsMenu(true);

        return v;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.grade, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeAluno/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            final View mView = getActivity().getLayoutInflater().inflate(R.layout.help_grade_layout, null);
            final Button voltar = (Button) mView.findViewById(R.id.voltarBTN);
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
        if (id == R.id.action_change) {
            if (minhaGrade != null) {
                nome = !nome;
                gerarGrade(minhaGrade);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCadeiras(final List<MinhaGradeModel> info, List<CadeirasModel> cadeirasFazendo) {
        //Geração da Grade
        ArrayList<Semestre> curso = new ArrayList<>();
        Semestre primeiroSemestre = new Semestre(info.get(0).getSemestre());
        curso.add(primeiroSemestre);
        Boolean encontrado;
        for (MinhaGradeModel cadeira : info) {
            encontrado = false;
            for (int i = 0; i < curso.size(); i++) {
                if (curso.get(i).getnSemestre().equals(cadeira.getSemestre())) {
                    curso.get(i).getCadeiras().add(cadeira);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                Semestre novoSemestre = new Semestre(cadeira.getSemestre());
                novoSemestre.getCadeiras().add(cadeira);
                curso.add(novoSemestre);
            }
        }
        minhaGrade = info;
        int creditosTotais = 0;
        int creditosConcluidos = 0;
        int contador = 0;
        int nMaxCadeirasSemestre = presenterGrade.getNmaxCadeirasSemestre(curso);
        TableRow.LayoutParams params = new TableRow.LayoutParams(getDisplaySize() / nMaxCadeirasSemestre, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow tableRow;
        for (final Semestre semestre : curso) {
            TextView TVSemestre = new TextView(getContext());
            TVSemestre.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            TVSemestre.setTextSize(20);
            if (!semestre.getnSemestre().equals("99")){
                TVSemestre.setPadding(10, 10, 0, 10);
                TVSemestre.setText("Semestre " + semestre.getnSemestre());
            }
            else{
                TVSemestre.setPadding(10, 50, 0, 50);
                TVSemestre.setText("Opcionais (Clique para expandir)");
                TVSemestre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(opcionaisTabela.getVisibility()==View.VISIBLE){
                            opcionaisTabela.setVisibility(View.GONE);
                            tabela.requestFocus();
                        }
                        else{
                            opcionaisTabela.setVisibility(View.VISIBLE);
                            opcionaisTabela.requestFocus();
                        }
                    }
                });
            }
            tabela.addView(TVSemestre);
            tableRow = new TableRow(getContext());
            tableRow.setGravity(Gravity.CENTER_VERTICAL);
            for (final MinhaGradeModel cadeiras : semestre.getCadeiras()) {
                if (semestre.getnSemestre().equals("99"))
                    contador++;
                final Button button = new Button(getContext());
                button.setLayoutParams(params);
                button.setBackgroundResource(R.drawable.circle_shape);
                String abreviacao = cadeiras.getNomeDisciplina().substring(0, Math.min(cadeiras.getNomeDisciplina().length(), 10));
                button.setText(abreviacao);
                button.setTextSize(10);
                button.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                if (!semestre.getnSemestre().equals("99"))
                    creditosTotais += Integer.parseInt(cadeiras.getnCreditos());
                if (cadeiras.getConcluida().equals("T")) {
                    cadeiras.setSituacao("Concluída");
                    button.setBackgroundResource(R.drawable.buttonblue);
                    if (!semestre.getnSemestre().equals("99"))
                        creditosConcluidos += Integer.parseInt(cadeiras.getnCreditos());
                } else {
                    //Busca para saber se Pre-Requisitos foram concluídos
                    if (!cadeiras.getPreRequisito().equals("")) {
                        button.setBackgroundResource(R.drawable.buttonred);
                        cadeiras.setSituacao("Indisponível");
                        String[] parts = cadeiras.getPreRequisito().split("\\;");
                        int tamanho = parts.length;
                        boolean achou;
                        for (String preRequisito : parts) {
                            achou = false;
                            for (MinhaGradeModel preReqCheck : info) {
                                if (preRequisito.equals(preReqCheck.getCdDisciplina())) {
                                    achou = true;
                                    if (preReqCheck.getConcluida().equals("T")) {
                                        tamanho--;
                                        break;
                                    }
                                }
                            } if (!achou)
                                    tamanho--;
                        }
                        if (tamanho == 0) {
                            cadeiras.setSituacao("Disponível");
                            button.setBackgroundResource(R.drawable.buttongreen);
                        }
                    } else {
                        cadeiras.setSituacao("Disponível");
                        button.setBackgroundResource(R.drawable.buttongreen);
                    }
                    //Descobrir quais cadeiras estão sendo cursadas nesse semestre
                    for (CadeirasModel cadeiraOnGoing : cadeirasFazendo) {
                        if (cadeiraOnGoing.getNomeCadeira().equals(cadeiras.getNomeDisciplina())) {
                            cadeiras.setSituacao("Matriculado");
                            button.setBackgroundResource(R.drawable.buttonyellow);
                            break;
                        }
                    }
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cadeiras.getSituacao().equals("Disponível")) {
                            button.setBackgroundResource(R.drawable.buttonyellow);
                            cadeiras.setSituacao("Matriculado");
                        } else if (cadeiras.getSituacao().equals("Matriculado")) {
                            button.setBackgroundResource(R.drawable.buttonblue);
                            cadeiras.setSituacao("Concluída");
                            cadeiras.setConcluida("T");
                            gerarGrade(info);
                        } else if (cadeiras.getSituacao().equals("Concluída")) {
                            button.setBackgroundResource(R.drawable.buttonblue);
                            cadeiras.setSituacao("Concluída");
                            cadeiras.setConcluida("F");
                            gerarGrade(info);
                        }
                    }
                });

                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                        final View mView = getActivity().getLayoutInflater().inflate(R.layout.cadeiragrade_layout, null);
                        final Button voltar = (Button) mView.findViewById(R.id.voltarBTN);

                        TextView disciplina = (TextView) mView.findViewById(R.id.disciplinaName);
                        TextView codigoD = (TextView) mView.findViewById(R.id.cDisciplinaTV);
                        TextView creditos = (TextView) mView.findViewById(R.id.creditosTV);
                        TextView situacao = (TextView) mView.findViewById(R.id.situacaoTV);
                        TextView preReq = (TextView) mView.findViewById(R.id.preRequisitosTV);

                        LinearLayout preRequisito = (LinearLayout) mView.findViewById(R.id.preReq);

                        if (!cadeiras.getPreRequisito().equals("")) {
                            preRequisito.setVisibility(View.VISIBLE);
                            preReq.setText("" + cadeiras.getPreRequisito());
                        }

                        codigoD.setText("" + cadeiras.getCdDisciplina());
                        disciplina.setText("" + cadeiras.getNomeDisciplina());
                        creditos.setText("" + cadeiras.getnCreditos());
                        situacao.setText("" + cadeiras.getSituacao());

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
                        return true;
                    }
                });
                tableRow.addView(button);
                if (semestre.getnSemestre().equals("99")) {
                    if (contador % nMaxCadeirasSemestre == 0) {
                        opcionaisTabela.addView(tableRow);
                        tableRow = new TableRow(getContext());
                        tableRow.setGravity(Gravity.CENTER_VERTICAL);
                        contador = 0;
                    }
                }
            }
            if(semestre.getnSemestre().equals("99"))
                opcionaisTabela.addView(tableRow);
            else
                tabela.addView(tableRow);
        }
        creditosTV.setText("Créditos concluídos: " + creditosConcluidos + "/" + creditosTotais);
    }

    public void gerarGrade(final List<MinhaGradeModel> info) {
        tabela.removeAllViews();
        opcionaisTabela.removeAllViews();
        ArrayList<Semestre> curso = new ArrayList<>();
        Semestre primeiroSemestre = new Semestre(info.get(0).getSemestre());
        curso.add(primeiroSemestre);
        Boolean encontrado;
        for (MinhaGradeModel cadeira : info) {
            encontrado = false;
            for (int i = 0; i < curso.size(); i++) {
                if (curso.get(i).getnSemestre().equals(cadeira.getSemestre())) {
                    curso.get(i).getCadeiras().add(cadeira);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                Semestre novoSemestre = new Semestre(cadeira.getSemestre());
                novoSemestre.getCadeiras().add(cadeira);
                curso.add(novoSemestre);
            }
        }
        minhaGrade = info;
        int creditosTotais = 0;
        int creditosConcluidos = 0;
        int contador = 0;
        int nMaxCadeirasSemestre = presenterGrade.getNmaxCadeirasSemestre(curso);
        TableRow.LayoutParams params = new TableRow.LayoutParams(getDisplaySize() / nMaxCadeirasSemestre, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow tableRow;
        for (final Semestre semestre : curso) {
            TableRow nSemestre = new TableRow(getContext());
            nSemestre.setGravity(Gravity.CENTER);
            TextView TVSemestre = new TextView(getContext());
            TVSemestre.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            TVSemestre.setTextSize(20);
            if (!semestre.getnSemestre().equals("99")){
                TVSemestre.setPadding(10, 10, 0, 10);
                TVSemestre.setText("Semestre " + semestre.getnSemestre());
            }
            else{
                TVSemestre.setPadding(10, 50, 0, 50);
                TVSemestre.setText("Opcionais (Clique para expandir)");
                TVSemestre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(opcionaisTabela.getVisibility()==View.VISIBLE){
                            opcionaisTabela.setVisibility(View.GONE);
                            tabela.requestFocus();
                        }
                        else{
                            opcionaisTabela.setVisibility(View.VISIBLE);
                            opcionaisTabela.requestFocus();
                        }
                    }
                });
            }
            tabela.addView(TVSemestre);
            tableRow = new TableRow(getContext());
            tableRow.setGravity(Gravity.CENTER_VERTICAL);
            for (final MinhaGradeModel cadeiras : semestre.getCadeiras()) {
                if (semestre.getnSemestre().equals("99"))
                    contador++;
                final Button button = new Button(getContext());
                button.setLayoutParams(params);
                button.setBackgroundResource(R.drawable.circle_shape);
                String abreviacao = cadeiras.getNomeDisciplina().substring(0, Math.min(cadeiras.getNomeDisciplina().length(), 10));
                button.setText(abreviacao);
                button.setTextSize(10);
                if (nome)
                    button.setText(abreviacao);
                else
                    button.setText(cadeiras.getCdDisciplina());
                button.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                if (!semestre.getnSemestre().equals("99"))
                    creditosTotais += Integer.parseInt(cadeiras.getnCreditos());
                if (!cadeiras.getPreRequisito().equals("")) { // através da checagem se os pré-requisitos foram feitos ou não é que a tabela será gerada.
                    String[] parts = cadeiras.getPreRequisito().split("\\;");
                    int preReqPendente = parts.length;
                    boolean achou; // Há casos que o pré-requisito não existe na grade ou provavelmente o valor está errado, então checa-se se o valor existe.
                    for (String preRequisito : parts) {
                        achou = false;
                        for (MinhaGradeModel preReqCheck : info) {
                            if (preRequisito.equals(preReqCheck.getCdDisciplina())) {
                                achou = true;
                                if (preReqCheck.getConcluida().equals("T")) {
                                    preReqPendente--;
                                    break;
                                }
                            }
                        }
                        if (!achou)
                            preReqPendente--;
                    }
                    if (preReqPendente == 0) {
                        if (cadeiras.getConcluida().equals("F")) {
                            if (cadeiras.getSituacao().equals("Matriculado"))
                                button.setBackgroundResource(R.drawable.buttonyellow);
                            else {
                                cadeiras.setSituacao("Disponível");
                                button.setBackgroundResource(R.drawable.buttongreen);
                            }
                        } else {
                            cadeiras.setSituacao("Concluída");
                            button.setBackgroundResource(R.drawable.buttonblue);
                            if (!semestre.getnSemestre().equals("99"))
                                creditosConcluidos += Integer.parseInt(cadeiras.getnCreditos());
                        }
                    } else {
                        button.setBackgroundResource(R.drawable.buttonred);
                        cadeiras.setSituacao("Indisponível");
                        cadeiras.setConcluida("F");
                    }
                } else {
                    if (cadeiras.getConcluida().equals("F")) {
                        if (cadeiras.getSituacao().equals("Matriculado"))
                            button.setBackgroundResource(R.drawable.buttonyellow);
                        else {
                            cadeiras.setSituacao("Disponível");
                            button.setBackgroundResource(R.drawable.buttongreen);
                        }
                    } else {
                        cadeiras.setSituacao("Concluída");
                        button.setBackgroundResource(R.drawable.buttonblue);
                        if (!semestre.getnSemestre().equals("99"))
                            creditosConcluidos += Integer.parseInt(cadeiras.getnCreditos());
                    }

                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cadeiras.getSituacao().equals("Disponível")) {
                            button.setBackgroundResource(R.drawable.buttonyellow);
                            cadeiras.setSituacao("Matriculado");
                        } else if (cadeiras.getSituacao().equals("Matriculado")) {
                            cadeiras.setConcluida("T");
                            tabela.removeAllViews();
                            gerarGrade(info);
                        } else if (cadeiras.getSituacao().equals("Concluída")) {
                            cadeiras.setConcluida("F");
                            tabela.removeAllViews();
                            gerarGrade(info);
                        }
                    }
                });

                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                        final View mView = getActivity().getLayoutInflater().inflate(R.layout.cadeiragrade_layout, null);
                        final Button voltar = (Button) mView.findViewById(R.id.voltarBTN);

                        TextView disciplina = (TextView) mView.findViewById(R.id.disciplinaName);
                        TextView codigoD = (TextView) mView.findViewById(R.id.cDisciplinaTV);
                        TextView creditos = (TextView) mView.findViewById(R.id.creditosTV);
                        TextView situacao = (TextView) mView.findViewById(R.id.situacaoTV);
                        TextView preReq = (TextView) mView.findViewById(R.id.preRequisitosTV);

                        LinearLayout preRequisito = (LinearLayout) mView.findViewById(R.id.preReq);

                        if (!cadeiras.getPreRequisito().equals("")) {
                            preRequisito.setVisibility(View.VISIBLE);
                            preReq.setText("" + cadeiras.getPreRequisito());
                        }

                        codigoD.setText("" + cadeiras.getCdDisciplina());
                        disciplina.setText("" + cadeiras.getNomeDisciplina());
                        creditos.setText("" + cadeiras.getnCreditos());
                        situacao.setText("" + cadeiras.getSituacao());

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
                        return true;
                    }
                });
                tableRow.addView(button);
                if (semestre.getnSemestre().equals("99")) {
                    if (contador % nMaxCadeirasSemestre == 0) {
                        opcionaisTabela.addView(tableRow);
                        tableRow = new TableRow(getContext());
                        tableRow.setGravity(Gravity.CENTER_VERTICAL);
                        contador = 0;
                    }
                }
            }
            if(semestre.getnSemestre().equals("99"))
                opcionaisTabela.addView(tableRow);
            else
                tabela.addView(tableRow);

        }
        creditosTV.setText("Créditos concluídos: " + creditosConcluidos + "/" + creditosTotais);
    }

    @Override
    public void showCadeirasInfo(List<CadeirasModel> cadeiras, CadeirasAtivasModel cadeirasAtivasModel) {
        presenterGrade.getInfo(token, cadeiras);
    }

    @Override
    public void showCadeirasAtivasInfo(CadeirasAtivasModel cadeirasAtivasModel) {
        presenterCadeiras.getInfo(token, cadeirasAtivasModel);
    }

    @Override
    public void tentarDeNovo() {
        if (this.isVisible())
            presenterCadeiras.getInfoCadeirasAtivas(token);
    }

}

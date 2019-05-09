package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.LogadoFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.orhanobut.hawk.Hawk;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeiraAlunoModel;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.CadeirasFragment.CadeirasFragment;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.MinhaGrade.MinhaGradeFragment;
import com.uece.br.mobuece.MenuPrincipal.BaseFragment;
import com.uece.br.mobuece.R;

public class LogadoFragment extends BaseFragment implements LogadoContract.LogadoView {

    public LogadoFragment() {
        // Required empty public constructor
    }
    //===============

    String token;
    TextView matriculaTV;
    TextView emailTV;
    TextView nomeTV;
    TextView cursoTV;
    TextView anoTV;
    Button gerarMatriculaBTN;
    TextView loadTXT;
    View v;
    LogadoContract.LogadoPresenter logadoPresenter;
    CadeirasFragment cadeirasFragment;
    MinhaGradeFragment minhaGradeFragment;
    ImageView profilePic;
    ImageView capaPic;
    RelativeLayout relativeLayout;
    WebView webViewPDF;
    String url = "http://alunoonline.uece.br/alunoonline/login/login.jsf";


    //===============
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_logado, container, false);

        nomeTV = (TextView) v.findViewById(R.id.nome);
        cursoTV = (TextView) v.findViewById(R.id.curso);
        emailTV = (TextView) v.findViewById(R.id.email);
        matriculaTV = (TextView) v.findViewById(R.id.matricula);
        anoTV = (TextView) v.findViewById(R.id.ano);
        profilePic = (ImageView) v.findViewById(R.id.profile);
        capaPic = (ImageView) v.findViewById(R.id.capa);
        relativeLayout = (RelativeLayout) v.findViewById(R.id.layout);
        gerarMatriculaBTN = (Button) v.findViewById(R.id.gerarMatricula);
        webViewPDF = (WebView) v.findViewById(R.id.webViewPDF);
        loadTXT = (TextView) v.findViewById(R.id.loadTXT);
        logadoPresenter = new LogadoPresenter(this, getContext());
        gerarMatriculaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load_page();
            }
        });

        Bundle bundle = this.getArguments();
        token = bundle.getString("token");
        logadoPresenter.getCadeirasAtivas(token);

        return v;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void load_page() {
        webViewPDF.getTouchables();
        webViewPDF.getSettings().setJavaScriptEnabled(true);
        webViewPDF.getSettings().setBuiltInZoomControls(true);
        webViewPDF.getSettings().setDisplayZoomControls(false);
        webViewPDF.setScrollBarStyle(android.webkit.WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webViewPDF.loadUrl(url);
        webViewPDF.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
                gerarMatriculaBTN.setClickable(false);
                view.loadUrl("javascript:(function() { document.getElementById('matricula').value = '" + Hawk.get("matricula").toString() + "'; ;})()");
                view.loadUrl("javascript:(function() { document.getElementById('Senha').value = '" + Hawk.get("senha").toString() + "'; ;})()");
                view.loadUrl("javascript:(function() { var z = document.getElementsByName('j_id23');" + " z[0].click() })()");
                loadTXT.setText("Carregando: 30%");
                webViewPDF.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(android.webkit.WebView view, String url) {
                        super.onPageFinished(view, url);
                        view.loadUrl("javascript:(function() { var node = document.querySelector('[title=\"Permite visualizar a declaração do aluno\"]'); + node.click() })()");
                        loadTXT.setText("Carregando: 60%");
                        webViewPDF.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(android.webkit.WebView view, String url) {
                                super.onPageFinished(view, url);
                                view.loadUrl("javascript:(function() { var node = document.querySelector('[title=\"Imprimir Declaração de Matrícula do Aluno\"]'); + node.click() })()");
                                loadTXT.setText("Carregando: 90%");
                                webViewPDF.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public void onPageFinished(android.webkit.WebView view, String url) {
                                        loadTXT.setText("");
                                        gerarMatriculaBTN.setClickable(true);
                                        createWebPrintJob(webViewPDF);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }



    private void createWebPrintJob(android.webkit.WebView webView) {
        //create object of print manager in your device
        PrintManager printManager = (PrintManager) getActivity().getSystemService(Context.PRINT_SERVICE);

        //create object of print adapter
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        //provide name to your newly generated pdf file
        String jobName = getString(R.string.app_name) + " Print Test";

        //open print dialog
        printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showInfo(InfoListModel info, CadeiraAlunoModel cadeiraAlunoModel) {
        relativeLayout.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(800)
                .playOn(relativeLayout);
        matriculaTV.setText(info.getnMatricula());
        String[] parts = info.getNome().split("\\ ");
        String name = "";
        for (String nome : parts)
            name += nome.substring(0, 1).toUpperCase() + nome.substring(1) + " ";
        nomeTV.setText(name);
        anoTV.setText(cadeiraAlunoModel.getAnoLetivo()+"."+ cadeiraAlunoModel.getSemestreLetivo());
        cursoTV.setText(info.getnCurso() + " - " + info.getNomeCurso().replace("  ", "") + " " + info.getAnoFluxo() + "/" + info.getSemFluxo());
        emailTV.setText(info.getEmail());
    }

    @Override
    public void showAtivas(CadeiraAlunoModel cadeiraAlunoModel) {
        logadoPresenter.getInfo(token,cadeiraAlunoModel);
    }

    @Override
    public void tentarDeNovo() {
        if(this.isVisible())
            logadoPresenter.getCadeirasAtivas(token);
    }
}

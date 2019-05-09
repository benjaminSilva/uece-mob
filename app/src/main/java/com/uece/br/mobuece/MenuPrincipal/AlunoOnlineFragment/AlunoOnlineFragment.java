package com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.uece.br.mobuece.MenuPrincipal.AlunoOnlineFragment.Logged.AlunoOnlineFragments;
import com.uece.br.mobuece.MenuPrincipal.BaseFragment;
import com.uece.br.mobuece.MenuPrincipal.WebViewFragment;
import com.uece.br.mobuece.R;

public class AlunoOnlineFragment extends BaseFragment implements AlunoOnlineContract.AlunoOnlineView {

    EditText usuarioED;
    EditText senhaED;
    Button loginBTN;
    AlunoOnlineContract.AlunoOnlinePresenter presenter;
    View view;
    CheckBox salvarDadosCB;
    CheckBox webViewCB;
    AlunoOnlineFragments alunoOnlineFragment;
    WebViewFragment webViewFragment;
    String matricula;
    String senha;
    Bundle savedInstance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        savedInstance = savedInstanceState;
        view = inflater.inflate(R.layout.fragment_aluno_online_aut, container, false);
        usuarioED = view.findViewById(R.id.ETmatricula);
        senhaED = view.findViewById(R.id.ETsenha);
        loginBTN =  view.findViewById(R.id.logar);
        salvarDadosCB = view.findViewById(R.id.salvarDadosCB);
        webViewCB = view.findViewById(R.id.webviewCB);
        presenter = new AlunoOnlinePresenter(this, getContext());
        Hawk.init(getContext()).build();

        setHasOptionsMenu(true);
        usuarioED.setText("");
        senhaED.setText("");

        if(Hawk.get("salvarCB")!= null){
            if(Hawk.get("salvarCB"))
                salvarDadosCB.setChecked(true);
            else
                salvarDadosCB.setChecked(false);
        }

        if(Hawk.get("webViewCB")!= null){
            if(Hawk.get("webViewCB"))
                webViewCB.setChecked(true);
            else
                webViewCB.setChecked(false);
        }

        Boolean bol = Hawk.contains("SalvarCB");

        if(bol){
            if(Hawk.get("salvarCB").equals(true)){
                usuarioED.setText(Hawk.get("matricula").toString());
                senhaED.setText(Hawk.get("senha").toString());
            }
        }

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });
        return view;
    }

    public void logar(){
        matricula = usuarioED.getText().toString();
        senha = senhaED.getText().toString();
        Hawk.put("matricula",matricula);
        Hawk.put("senha",senha);
        if(salvarDadosCB.isChecked()){
            Hawk.put("salvarCB",true);
        }else {
            Hawk.put("salvarCB",false);
        }
        if(webViewCB.isChecked()){
            Hawk.put("webViewCB",true);
            webViewFragment = new WebViewFragment();
            Bundle args = new Bundle();
            args.putString("matricula",matricula);
            args.putString("senha",senha);
            webViewFragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.maincontent, webViewFragment,"WebView")
                    .addToBackStack("WebView")
                    .commit();
        }else {
            Hawk.put("webViewCB",false);
            presenter.getToken(usuarioED.getText().toString(),senhaED.getText().toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(salvarDadosCB.isChecked()){
            Hawk.put("salvarCB",true);
        }else {
            Hawk.put("salvarCB",false);
        }

        if(webViewCB.isChecked())
            Hawk.put("webViewCB",true);
        else
            Hawk.put("webViewCB",false);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        usuarioED.setText("");
        senhaED.setText("");

        Boolean bol = Hawk.get("salvarCB");
        if(Hawk.contains("salvarCB")){
            if(bol)
                salvarDadosCB.setChecked(true);
            else
                salvarDadosCB.setChecked(false);
        }
        if(Hawk.get("webViewCB")!= null){
            if(Hawk.get("webViewCB"))
                webViewCB.setChecked(true);
            else
                webViewCB.setChecked(false);
        }

        if(Hawk.contains("salvarCB")){
            if(Hawk.get("salvarCB")){
                usuarioED.setText(Hawk.get("matricula").toString());
                senhaED.setText(Hawk.get("senha").toString());
            }
        }
    }

    @Override
    public void showToken(String token) {
        if (token == null){
            Toast.makeText(getContext(), "Matrícula ou Senha estão errados.", Toast.LENGTH_SHORT).show();
        } else {
            alunoOnlineFragment = new AlunoOnlineFragments();
            Bundle args = new Bundle();
            args.putString("token",token);
            alunoOnlineFragment.setArguments(args);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.maincontent, alunoOnlineFragment,"Logado")
                    .commit();

        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.login, menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            final View mView = getActivity().getLayoutInflater().inflate(R.layout.help_grade_layout, null);
            TextView info = mView.findViewById(R.id.qualseudestion);
            Button disponiveis = mView.findViewById(R.id.disponiveis);
            Button matriculadas = mView.findViewById(R.id.matriculadas);
            Button concluidas = mView.findViewById(R.id.concluidas);
            Button indisponiveis = mView.findViewById(R.id.indisponiveis);
            info.setText("Consultar o Aluno Online pelo Uece Mobile consome menos dados, porém algumas funcionalidades são desativadas.\n\n Consultando o Aluno Online com a função 'Abrir pela Web' ativada, a experiência torna-se semelhante ao uso pelo Browser.");
            disponiveis.setVisibility(View.GONE);
            matriculadas.setVisibility(View.GONE);
            concluidas.setVisibility(View.GONE);
            indisponiveis.setVisibility(View.GONE);

            final Button voltar = mView.findViewById(R.id.voltarBTN);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void tentarDeNovo() {
        if(this.isVisible())
            logar();
    }

}

package com.uece.br.mobuece.MenuPrincipal;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.uece.br.mobuece.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class RUFragment extends BaseFragment{

    public RUFragment() {
        // Required empty public constructor
    }

    TextView data2;
    TextView data3;
    TextView data4;
    TextView data5;
    TextView data6;

    TextView carne2;
    TextView carne3;
    TextView carne4;
    TextView carne5;
    TextView carne6;

    TextView acomp2;
    TextView acomp3;
    TextView acomp4;
    TextView acomp5;
    TextView acomp6;

    TextView veg2;
    TextView veg3;
    TextView veg4;
    TextView veg5;
    TextView veg6;

    Intent share;

    Fragment fragment;

    ProgressBar progressBar;
    LinearLayout linearLayout;
    private Bundle savedState = null;
    private boolean isDestroyed = false;
    Bundle savedIntance;

    ArrayList<String> cardapio = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ru, container, false);

        savedIntance = savedInstanceState;

        data2 = (TextView)view.findViewById(R.id.date2);
        data3 = (TextView)view.findViewById(R.id.date3);
        data4 = (TextView)view.findViewById(R.id.date4);
        data5 = (TextView)view.findViewById(R.id.date5);
        data6 = (TextView)view.findViewById(R.id.date6);

        carne2 = (TextView)view.findViewById(R.id.carne2);
        carne3 = (TextView)view.findViewById(R.id.carne3);
        carne4 = (TextView)view.findViewById(R.id.carne4);
        carne5 = (TextView)view.findViewById(R.id.carne5);
        carne6 = (TextView)view.findViewById(R.id.carne6);

        acomp2 = (TextView) view.findViewById(R.id.acompanhamento2);
        acomp3 = (TextView) view.findViewById(R.id.acompanhamento3);
        acomp4 = (TextView) view.findViewById(R.id.acompanhamento4);
        acomp5 = (TextView) view.findViewById(R.id.acompanhamento5);
        acomp6 = (TextView) view.findViewById(R.id.acompanhamento6);

        veg2 = (TextView) view.findViewById(R.id.vegetariano2);
        veg3 = (TextView) view.findViewById(R.id.vegetariano3);
        veg4 = (TextView) view.findViewById(R.id.vegetariano4);
        veg5 = (TextView) view.findViewById(R.id.vegetariano5);
        veg6 = (TextView) view.findViewById(R.id.vegetariano6);

        progressBar = (ProgressBar) view.findViewById(R.id.progress_barRU);
        linearLayout = (LinearLayout)view.findViewById(R.id.linearLayoutRU);

        fragment = this;

        setHasOptionsMenu(true);
        share = new Intent(Intent.ACTION_SEND);
        share.setType("message/rfc822");

        return view;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        if (checkConnection())
            new doit().execute();
        else{
            Toast.makeText(getContext(), "Sem internet, tente mais tarde.", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    if (fragment.isVisible())
                        onCreate(savedInstanceState);
                }
            }, 10000);
        }

    }

    public class doit extends AsyncTask<Void,Void,Void>{
        Elements els;
        Document doc;
        @Override
        protected Void doInBackground(Void... params) {

            try {
                if(checkConnection())
                    doc = Jsoup.connect("http://www.uece.br/espaco-do-aluno/restaurante-universitario/").get();
            } catch (IOException e) {
                //Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(isDestroyed)
                return;
            if(doc==null)
                return;
            if(checkConnection()){
                els = doc.select("strong");

                for (int i=4;i<9;i++){
                    cardapio.add(els.get(i).text());
                }
                els = doc.getElementsByClass("dt-8 -padfix HasSidebar");

                els = els.select("p");

                int inicio = 0;

                for (int i = 0;i < els.size();i++){
                    String[] primeira = els.get(i).text().split(" ", 2);
                    if (primeira[0].equals("Segunda")){
                        inicio = i;
                        break;
                    }
                }

                for(int i = inicio;i<els.size();i++){
                    String teste = els.get(i).html();
                    if (teste.contains("<br>")){
                        String[] teste2 = teste.split("<br>",3);
                        Boolean adiciona;
                        for (int k = 0; k < teste2.length ; k++){
                            adiciona = true;
                            for (int j = 0; j<5 ; j++){
                                if (teste2[k].contains(cardapio.get(j))){
                                    adiciona = false;
                                }
                            }
                            if(adiciona){
                                if (els.get(i).ownText().equals("Feriado")||els.get(i).ownText().equals("Fechado")||els.get(i).ownText().equals("Vestibular")){
                                    cardapio.add("");
                                    cardapio.add(els.get(i).ownText());
                                    cardapio.add("");
                                }else {
                                        cardapio.add(teste2[k]);
                                }
                            }
                        }
                    } else {
                        if (els.get(i).ownText().equals("Feriado")||els.get(i).ownText().equals("Fechado")||els.get(i).ownText().equals("Vestibular")){
                            cardapio.add("");
                            cardapio.add(els.get(i).ownText());
                            cardapio.add("");
                        }
                        else {
                            if (!els.get(i).ownText().equals("\u00a0")&&!els.get(i).ownText().equals("")&&cardapio.size()<20)
                                cardapio.add(els.get(i).ownText());
                        }
                    }
                }


                Bundle bundle = new Bundle();
                bundle.putStringArrayList("Cardapio",cardapio);

                setaTexto(bundle);

                progressBar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(linearLayout);
            }else {
                onCreate(savedState);
            }


        }
    }

    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
        Bundle state = new Bundle();
        state.putStringArrayList("Cardapio", cardapio);
        return state;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.ru, menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            if(progressBar.getVisibility()!=View.VISIBLE){
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                final View mView = getActivity().getLayoutInflater().inflate(R.layout.share_ru_layout, null);
                final Button segunda = (Button) mView.findViewById(R.id.segunda);
                final Button terca = (Button) mView.findViewById(R.id.terca);
                final Button quarta = (Button) mView.findViewById(R.id.quarta);
                final Button quinta = (Button) mView.findViewById(R.id.quinta);
                final Button sexta = (Button) mView.findViewById(R.id.sexta);
                final Button voltar = (Button) mView.findViewById(R.id.voltarBTN);
                mBuilder.setView(mView);
                final AlertDialog primeirasOpcoes = mBuilder.create();
                primeirasOpcoes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                primeirasOpcoes.show();

                segunda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        share.putExtra(Intent.EXTRA_TEXT,"Cardápio para o dia - " + data2.getText() + "\n" + "Carne: " + carne2.getText() + "\n" + "Acompanhamento: " + acomp2.getText() + "\n" + "Vegetariana: " + veg2.getText());
                        startActivity(share);
                        primeirasOpcoes.dismiss();
                    }
                });
                terca.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        share.putExtra(Intent.EXTRA_TEXT,"Cardápio para o dia - " + data3.getText() + "\n" + "Carne: " + carne3.getText() + "\n" + "Acompanhamento: " + acomp3.getText() + "\n" + "Vegetariana: " + veg3.getText());
                        startActivity(share);
                        primeirasOpcoes.dismiss();
                    }
                });
                quarta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        share.putExtra(Intent.EXTRA_TEXT,"Cardápio para o dia - " + data4.getText() + "\n" + "Carne: " + carne4.getText() + "\n" + "Acompanhamento: " + acomp4.getText() + "\n" + "Vegetariana: " + veg4.getText());
                        startActivity(share);
                        primeirasOpcoes.dismiss();
                    }
                });
                quinta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        share.putExtra(Intent.EXTRA_TEXT,"Cardápio para o dia - " + data5.getText() + "\n" + "Carne: " + carne5.getText() + "\n" + "Acompanhamento: " + acomp5.getText() + "\n" + "Vegetariana: " + veg5.getText());
                        startActivity(share);
                        primeirasOpcoes.dismiss();
                    }
                });
                sexta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        share.putExtra(Intent.EXTRA_TEXT,"Cardápio para o dia - " + data6.getText() + "\n" + "Carne: " + carne6.getText() + "\n" + "Acompanhamento: " + acomp6.getText() + "\n" + "Vegetariana: " + veg6.getText());
                        startActivity(share);
                        primeirasOpcoes.dismiss();
                    }
                });

                voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        primeirasOpcoes.dismiss();
                    }
                });
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("Card", (savedState != null) ? savedState : saveState());
    }

    public void setaTexto(Bundle comida){
        ArrayList<String> menu = comida.getStringArrayList("Cardapio");
        if(menu.isEmpty())
            return;
        data2.setText(menu.get(0));
        data3.setText(menu.get(1));
        data4.setText(menu.get(2));
        data5.setText(menu.get(3));
        data6.setText(menu.get(4));

        if(menu.size()>5)
        carne2.setText(menu.get(5).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>6)
        acomp2.setText(menu.get(6).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>7)
        veg2.setText(menu.get(7).replace("Vegetariana: ","").replace("Vegetariana :","").replace(" Vegetariana :","").replaceAll("\\s+\\s+\\s+","").replace(".","").replace("Vegetariano:",""));

        if(menu.size()>8)
        carne3.setText(menu.get(8).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>9)
        acomp3.setText(menu.get(9).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>10)
        veg3.setText(menu.get(10).replace("Vegetariana: ","").replace("Vegetariana :","").replace(" Vegetariana :","").replaceAll("\\s+\\s+\\s+","").replace(".","").replace("Vegetariano:",""));

        if(menu.size()>11)
        carne4.setText(menu.get(11).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>12)
        acomp4.setText(menu.get(12).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>13)
        veg4.setText(menu.get(13).replace("Vegetariana: ","").replace("Vegetariana :","").replace(" Vegetariana :","").replaceAll("\\s+\\s+\\s+","").replace(".","").replace("Vegetariano:",""));

        if(menu.size()>14)
        carne5.setText(menu.get(14).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>15)
        acomp5.setText(menu.get(15).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>16)
        veg5.setText(menu.get(16).replace("Vegetariana: ","").replace("Vegetariana :","").replace(" Vegetariana :","").replaceAll("\\s+\\s+\\s+","").replace(".","").replace("Vegetariano:",""));

        if(menu.size()>17)
        carne6.setText(menu.get(17).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>18)
        acomp6.setText(menu.get(18).replaceAll("\\s+\\s+\\s+","").replace(".",""));
        if(menu.size()>19)
        veg6.setText(menu.get(19).replace("Vegetariana: ","").replace("Vegetariana :","").replace(" Vegetariana :","").replaceAll("\\s+\\s+\\s+","").replace(".","").replace("Vegetariano:",""));

        progressBar.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(600)
                .playOn(linearLayout);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedState!=null)
            if(savedState.getStringArrayList("Cardapio").isEmpty())
                if(checkConnection())
                    new doit().execute();

        if(savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle("Card");
        }
        if(savedState != null) {
            setaTexto(savedState);
        }
        savedState = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState();
    }
}

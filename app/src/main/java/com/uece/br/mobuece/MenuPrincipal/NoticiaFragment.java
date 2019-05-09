package com.uece.br.mobuece.MenuPrincipal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.uece.br.mobuece.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class NoticiaFragment extends BaseFragment {


    public NoticiaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        END_URL = bundle.getString("url");
        if (checkConnection())
            new loadNoticia().execute();
        else {
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

    View v;
    ProgressBar progressBar;
    ImageView newImg;
    TextView title;
    TextView newText;
    Intent share;
    Intent openBrowser;
    AppBarLayout appBarLayout;
    Fragment fragment;
    Bundle savedInstance = null;
    private boolean isDestroyed = false;

    String BASE_URL = "http://www.uece.br";
    String END_URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_noticia, container, false);
        savedInstance = savedInstanceState;
        fragment = this;
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_noticia);
        newImg = (ImageView) v.findViewById(R.id.mainImage);
        title = (TextView) v.findViewById(R.id.title_noticia);
        newText = (TextView) v.findViewById(R.id.noticia_text);
        appBarLayout = (AppBarLayout)v.findViewById(R.id.mainappbar);
        share = new Intent(Intent.ACTION_SEND);
        openBrowser = new Intent(Intent.ACTION_VIEW);
        share.setType("text/plain");

        setHasOptionsMenu(true);
        Bundle bundle = this.getArguments();
        END_URL = bundle.getString("url");

        return v;
    }

    public class loadNoticia extends AsyncTask<Void, Void, Void> {
        Elements els;
        Element el;
        Document doc;
        String noticia = "";

        @Override
        protected Void doInBackground(Void... params) {

            try {
                if(checkConnection())
                    doc = Jsoup.connect(END_URL).get();
                    //doc = Jsoup.connect(BASE_URL + END_URL).get();
            } catch (IOException e) {
                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                //el = doc.getElementById("content");

                els = doc.select("h1");
                title.setText(els.text());

                els = doc.select("p");
                for (int i = 0; i < els.size(); i++) {
                    if (!els.get(i).text().equals(" ")&&!els.get(i).text().equals("")) { //NO-BREAK SPACE [NBSP]
                        noticia = noticia + "\n";
                        noticia = noticia + els.get(i).text();
                        noticia = noticia + "\n";
                    }
                }
                newText.setText(noticia);
                els = doc.select("img");
                els = doc.getElementsByClass("tl-7  dt-8  SingleContent").select("img");
                if(els.size()>0){
                    Picasso.with(getContext()).load(els.get(0).absUrl("src")).into(newImg);
                    newImg.setVisibility(View.VISIBLE);
                }
                //Log.d("Teste",els.get(0).absUrl("src"));

                progressBar.setVisibility(View.GONE);
                appBarLayout.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(appBarLayout);
                title.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(title);
                newText.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeIn)
                        .duration(600)
                        .playOn(newText);

                share.putExtra(Intent.EXTRA_TEXT, BASE_URL+END_URL);
                openBrowser.setData(Uri.parse(BASE_URL+END_URL));
            }else{
                onCreate(savedInstance);
            }


        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.noticia, menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeAluno/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            if(progressBar.getVisibility()!=View.VISIBLE)
                startActivity(share);
            //mShareActionProvider.setShareIntent(share);
        }
        if(id==R.id.action_broswer){
            if(progressBar.getVisibility()!=View.VISIBLE)
                startActivity(openBrowser);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroyed = true;

    }


}

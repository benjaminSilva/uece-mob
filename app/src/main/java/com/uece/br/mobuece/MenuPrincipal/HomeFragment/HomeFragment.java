package com.uece.br.mobuece.MenuPrincipal.HomeFragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.uece.br.mobuece.MainActivity;
import com.uece.br.mobuece.R;
import com.uece.br.mobuece.MenuPrincipal.BaseFragment;
import com.uece.br.mobuece.MenuPrincipal.NoticiaFragment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;


public class HomeFragment extends BaseFragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    //Se houver internet, ele vai atualizar, se não, obterá informações salvas no Bundle.
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (checkConnection())
            new getInfoFromHTML().execute();
        else
        {
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

    //Variáveis

    View v;
    SliderLayout sliderLayout;
    ArrayList<String> jpgs = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> links = new ArrayList<>();
    ArrayList<String> newDate = new ArrayList<>();
    ArrayList<String> newTitle = new ArrayList<>();
    ArrayList<String> newLinks = new ArrayList<>();
    int size;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    NewsListAdapter newsListAdapter;
    private Bundle savedState = null;
    private boolean isDestroyed = false;
    AppBarLayout appBarLayout;
    ProgressBar progressBar;
    NoticiaFragment noticiaFragment;
    Fragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        fragment = this;
        sliderLayout = (SliderLayout) v.findViewById(R.id.image_slider);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        appBarLayout = (AppBarLayout) v.findViewById(R.id.mainappbar);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar_home);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        return v;
    }

    //Obter as informações do HTML

    public class getInfoFromHTML extends AsyncTask<Void, Void, Void> {
        Elements els;
        Element el;
        Document doc;

        @Override
        protected Void doInBackground(Void... params) {


            try {
                if (checkConnection()){
                    doc = Jsoup.connect("http://www.uece.br/").get();
                }
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
            if (checkConnection()) {

                els = doc.select("figure");

                /*for(int i=0; i<els.size();i++){
                    jpgs.add(els.get(i).select("img").attr("src"));
                    titles.add(els.get(i).select("h3").text());
                    Elements elements = els.get(i).select("a");
                    for (Element elemento : elements){
                        if(!elemento.hasClass("categoria")){
                            links.add(els.get(i).select("a").attr("href"));
                        }
                    }
                }*/

                for (Element element : els){
                    jpgs.add(element.select("img").attr("src"));
                    titles.add(element.select("h3").text());
                    Elements elementos = element.select("a");
                    links.add(elementos.get(1).attr("href"));
                }
                //el = doc.getElementById("");

                /*el = doc.getElementById("destaque");
                els = el.getElementsByClass("gk_news_image_3_slide");
                size = els.size();
                for (int i = 0; i < size; i++) {
                    els = el.getElementsByClass("gk_news_image_3_slide");
                    jpgs.add(els.get(i).text());
                    els = el.select("a");
                    titles.add(els.get(i).text());
                    links.add(els.get(i).attr("href"));
                }*/

                initializeSliderLayout();

                els = doc.getElementsByClass("row nested");

                for (int i = 0; i< els.size(); i++){
                    newDate.add(els.get(i).select("span").text());
                    newTitle.add(els.get(i).getElementsByClass("-bold").text());
                    newLinks.add(els.get(i).select("a").attr("href"));
                }


                /*el = doc.getElementById("noticias");
                els = el.getElementsByClass("dn-date");
                size = els.size();
                for (int i = 0; i < size; i++) {
                    els = el.getElementsByClass("dn-date");
                    newDate.add(els.get(i).text());
                    els = el.select("a");
                    newTitle.add(els.get(i + 1).text());
                    newLinks.add(els.get(i + 1).attr("href"));
                }*/

                recyclerView.setLayoutManager(layoutManager);
                newsListAdapter = new NewsListAdapter(getContext(), newTitle, newDate, newLinks, getActivity().getSupportFragmentManager());
                recyclerView.setAdapter(newsListAdapter);
                loaded();
            }else{
                onCreate(savedState);
            }
        }
    }

    //Tornar as coisas visíveis quando carregadas

    private void loaded() {
        progressBar.setVisibility(View.INVISIBLE);

        sliderLayout.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(600)
                .playOn(sliderLayout);

        recyclerView.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(600)
                .playOn(recyclerView);

        appBarLayout.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(600)
                .playOn(appBarLayout);
    }


    //Inicializar o Slider Layout
    private void initializeSliderLayout() {
        for (int i = 0; i < titles.size(); i++) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            final int finalI = i;
            textSliderView
                    .description(titles.get(i))
                    .image(jpgs.get(i))
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            String endUrl = links.get(finalI);
                            //if ("/".equals(endUrl.substring(0,1))){
                                Bundle args = new Bundle();
                                noticiaFragment = new NoticiaFragment();
                                args.putString("url", endUrl);
                                noticiaFragment.setArguments(args);
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.maincontent, noticiaFragment).addToBackStack("Noticia").commit();
                            //}
                        }
                    })
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            sliderLayout.addSlider(textSliderView);
        }
    }

    //Salvar os dados

    private Bundle saveState() {
        Bundle state = new Bundle();
        state.putStringArrayList("jpgs", jpgs);
        state.putStringArrayList("titles", titles);
        state.putStringArrayList("links", links);
        state.putStringArrayList("newDate", newDate);
        state.putStringArrayList("newLinks", newLinks);
        state.putStringArrayList("newTitle", newTitle);
        return state;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("Card", (savedState != null) ? savedState : saveState());
    }


    //Quando o aplicativo for revisitado
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedState != null)
            if (savedState.getStringArrayList("jpgs").isEmpty())
                if (checkConnection())
                    new getInfoFromHTML().execute();

        if (savedInstanceState != null && savedState == null) {
            savedState = savedInstanceState.getBundle("Card");
        }
        if (savedState != null) {
            links = savedState.getStringArrayList("links");
            jpgs = savedState.getStringArrayList("jpgs");
            titles = savedState.getStringArrayList("titles");

            newDate = savedState.getStringArrayList("newDate");
            newTitle = savedState.getStringArrayList("newTitle");
            newLinks = savedState.getStringArrayList("newLinks");

            initializeSliderLayout();

            recyclerView.setLayoutManager(layoutManager);
            newsListAdapter = new NewsListAdapter(getContext(), newTitle, newDate, newLinks, getActivity().getSupportFragmentManager());
            recyclerView.setAdapter(newsListAdapter);

            loaded();
        }
        savedState = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savedState = saveState();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

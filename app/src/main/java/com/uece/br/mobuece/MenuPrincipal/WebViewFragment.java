package com.uece.br.mobuece.MenuPrincipal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.uece.br.mobuece.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebViewFragment newInstance(String param1, String param2) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    LinearLayout login;
    EditText etMatricula;
    EditText etSenha;
    private android.webkit.WebView webViewNoticia;
    String url = "http://alunoonline.uece.br/alunoonline/login/login.jsf";
    String matricula;
    String senha;
    String pagina;


    public void voltarPagina() {
        webViewNoticia.goBack();
    }

    public String getCurrentURL() {
        return webViewNoticia.getUrl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, null);
        etMatricula = (EditText) view.findViewById(R.id.ETmatricula);
        etSenha = (EditText) view.findViewById(R.id.ETsenha);
        webViewNoticia = (android.webkit.WebView) view.findViewById(R.id.webView);
        login = (LinearLayout) view.findViewById(R.id.loginLL);

        Bundle bundle = this.getArguments();
        if (bundle.containsKey("matricula")){
            matricula = bundle.getString("matricula");
            senha = bundle.getString("senha");
        }
        if (bundle.containsKey("webPage"))
        {
            load_page(bundle.getString("webPage"));
        }else {
            load_page(url);
        }

        return view;
    }


    private void load_page(String webPage) {
        webViewNoticia.getTouchables();
        webViewNoticia.getSettings().setJavaScriptEnabled(true);
        webViewNoticia.getSettings().setBuiltInZoomControls(true);
        webViewNoticia.getSettings().setDisplayZoomControls(false);
        webViewNoticia.setWebViewClient(new WebViewClient());
        webViewNoticia.setScrollBarStyle(android.webkit.WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webViewNoticia.loadUrl(webPage);
        if(webPage == url){
            webViewNoticia.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(android.webkit.WebView view, String url) {
                    super.onPageFinished(view, url);
                    view.loadUrl("javascript:(function() { document.getElementById('matricula').value = '" + matricula + "'; ;})()");
                    view.loadUrl("javascript:(function() { document.getElementById('Senha').value = '" + senha + "'; ;})()");
                    view.loadUrl("javascript:(function() { var z = document.getElementsByName('j_id23');" + " z[0].click() })()");
                }
            });
        }
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


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
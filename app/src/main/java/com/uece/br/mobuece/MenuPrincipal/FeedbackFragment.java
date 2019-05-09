package com.uece.br.mobuece.MenuPrincipal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uece.br.mobuece.R;


public class FeedbackFragment extends BaseFragment {

    Button eviarEmail;
    EditText edTitulo;
    EditText edMensagem;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_feedback, container, false);

        edMensagem = (EditText)v.findViewById(R.id.edMensagem);
        edTitulo = (EditText)v.findViewById(R.id.edTitulo);
        eviarEmail = (Button)v.findViewById(R.id.btnSend);
        eviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        return v;
    }

    protected void sendEmail() {
        //Log.i("Send email", "");

        String[] TO = {"mobile.uece@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, edTitulo.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, edMensagem.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void finish() {
        edTitulo.setText("");
        edMensagem.setText("");
    }


}

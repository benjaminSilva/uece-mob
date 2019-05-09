package com.uece.br.mobuece.MenuPrincipal.HomeFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uece.br.mobuece.R;
import com.uece.br.mobuece.MenuPrincipal.NoticiaFragment;

import java.util.ArrayList;

/**
 * Created by Benjamin on 9/22/2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter {
    ArrayList<String> titulos;
    ArrayList<String> datas;
    ArrayList<String> links;
    Context context;
    NoticiaFragment noticiaFragment;
    FragmentManager fragmentManager;

    View view;

    public NewsListAdapter(Context context, ArrayList<String> titulos, ArrayList<String> datas, ArrayList<String> links,FragmentManager fm){
        this.fragmentManager = fm;
        this.context = context;
        this.titulos = titulos;
        this.datas = datas;
        this.links = links;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_newslist,parent,false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewsHolder viewHolder = (NewsHolder) holder;
        if(position%2==1){
            viewHolder.container.setBackgroundColor(context.getResources().getColor(R.color.lightGray));
        }else {
            viewHolder.container.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        viewHolder.data.setText(datas.get(position));
        viewHolder.noticia.setText(titulos.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return titulos == null ? 0 : titulos.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{

        TextView data;
        TextView noticia;
        RelativeLayout container;

        public NewsHolder(View itemView) {
            super(itemView);
            data = (TextView)itemView.findViewById(R.id.data);
            noticia = (TextView)itemView.findViewById(R.id.noticia);
            container = (RelativeLayout)itemView.findViewById(R.id.container);
            
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    noticiaFragment = new NoticiaFragment();
                    args.putString("url",links.get(getAdapterPosition()));
                    noticiaFragment.setArguments(args);
                    fragmentManager.beginTransaction()
                            .replace(R.id.maincontent, noticiaFragment).addToBackStack("Noticia").commit();
                }
            });
        }
    }
}

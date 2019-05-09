package com.uece.br.mobuece.MenuPrincipal.MapaFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uece.br.mobuece.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

/**
 * Created by Benjamin on 10/3/2017.
 */

public class PlacesListAdapter extends RecyclerView.Adapter {

    AlertDialog alertDialog;
    Context context;
    View view;
    ArrayList<Marker> markers = new ArrayList<>();
    GoogleMap googleMap;

    PlacesListAdapter (ArrayList<Marker> markers, GoogleMap googleMap, Context context, AlertDialog alertDialog){
        this.context = context;
        this.markers = markers;
        this.googleMap = googleMap;
        this.alertDialog = alertDialog;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_newslist,parent,false);
        PlacesListAdapter.PlacesHolder holder = new PlacesListAdapter.PlacesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PlacesHolder viewHolder = (PlacesHolder) holder;
        viewHolder.gone.setVisibility(View.GONE);
        if(position%2==1){
            viewHolder.container.setBackgroundColor(context.getResources().getColor(R.color.lightGray));
        }else {
            viewHolder.container.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        viewHolder.place.setText(markers.get(position).getTitle());
        viewHolder.place.setTextSize(15);
        viewHolder.place.setTextColor(Color.BLACK);
    }

    @Override
    public int getItemCount() {
        return markers == null ? 0 : markers.size();
    }

    public class PlacesHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView gone;
        TextView place;

        public PlacesHolder(View itemView) {
            super(itemView);
            container = (RelativeLayout)itemView.findViewById(R.id.container);
            gone = (TextView) itemView.findViewById(R.id.data);
            place = (TextView)itemView.findViewById(R.id.noticia);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers.get(getAdapterPosition()).getPosition(),19));
                    markers.get(getAdapterPosition()).showInfoWindow();
                }
            });
        }
    }
}

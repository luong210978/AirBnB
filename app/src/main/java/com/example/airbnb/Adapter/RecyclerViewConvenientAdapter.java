package com.example.airbnb.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airbnb.Model.Room;
import com.example.airbnb.R;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewConvenientAdapter extends RecyclerView.Adapter<RecyclerViewConvenientAdapter.RecyclerViewHolder> {

    Context context;
    List<String> conveniences;

    public RecyclerViewConvenientAdapter(Context context, List<String> conveniences)
    {
        this.context = context;
        this.conveniences = conveniences;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_convenient, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String convenience = conveniences.get(position);
        holder.convenience_tv.setText(convenience);

        switch (convenience)
        {
            case Room.CONVENIENCE_WIFI:
                holder.convenience_iv.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_wifi_24));
                break;
            case Room.CONVENIENCE_AIR_CONDITIONER:
                holder.convenience_iv.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_air_24));
                break;
            case Room.CONVENIENCE_KITCHEN:
                holder.convenience_iv.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_soup_kitchen_24));
                break;
            case Room.CONVENIENCE_PARKING:
                holder.convenience_iv.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_local_parking_24));
                break;
            case Room.CONVENIENCE_REFRIGERATOR:
                holder.convenience_iv.setImageDrawable(context.getDrawable(R.drawable.ic_baseline_kitchen_24));
                break;
        }
        holder.convenience_iv.setColorFilter(context.getColor(R.color.colorPrimary));
    }

    @Override
    public int getItemCount() {
        if(conveniences == null)
            return 0;
        return conveniences.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.convenience_iv)
        ImageView convenience_iv;
        @BindView(R.id.convenience_tv)
        TextView convenience_tv;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

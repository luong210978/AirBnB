package com.example.airbnb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airbnb.Model.Rate;
import com.example.airbnb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewRateAdapter extends RecyclerView.Adapter<RecyclerViewRateAdapter.RecyclerViewHolder> {

    Context context;
    List<Rate> rates;

    public RecyclerViewRateAdapter(Context context, List<Rate> rates, int type) {
        this.context = context;
        this.rates = new ArrayList<Rate>();
        if(type == 1) {
            this.rates.add(rates.get(0));
            this.rates.add(rates.get(1));
        }
        else
            this.rates = rates;
    }

    public void addRating(Rate rate)
    {
        rates.add(rate);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_rate, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String avatarUri = rates.get(position).getUser().getAvatar();
        String name = rates.get(position).getUser().getName();
        String date = rates.get(position).getDate();
        String comment = rates.get(position).getComment();

        Picasso.get().load(avatarUri).into(holder.avatar_iv);
        holder.name_tv.setText(name);
        holder.date_tv.setText(date);
        holder.comment_tv.setText(comment);
    }

    @Override
    public int getItemCount() {
        return rates.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.avatar_iv)
        CircleImageView avatar_iv;
        @BindView(R.id.name_tv)
        TextView name_tv;
        @BindView(R.id.date_tv)
        TextView date_tv;
        @BindView(R.id.comment_tv)
        TextView comment_tv;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}

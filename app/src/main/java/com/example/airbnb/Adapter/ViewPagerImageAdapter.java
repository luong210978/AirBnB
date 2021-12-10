package com.example.airbnb.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.airbnb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerImageAdapter extends PagerAdapter {

    private ArrayList<String> images;
    private Context context;

    public ViewPagerImageAdapter(Context context, ArrayList<String> images)
    {
        this.context = context;
        this.images = images;
    }
    @Override
    public int getCount() {
        if(images == null)
            return 0;
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_pager_image, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        String imageUri = images.get(position);
        Picasso.get().load(imageUri).into(imageView);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

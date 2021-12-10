package com.example.airbnb.View.Rating;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.airbnb.Adapter.RecyclerViewRateAdapter;
import com.example.airbnb.Model.Rate;
import com.example.airbnb.Model.Room;
import com.example.airbnb.R;
import com.example.airbnb.View.CreationRating.CreationRatingActivity;
import com.example.airbnb.View.RoomDetail.RoomDetailActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_CREATION_RATING = 1;
    List<Rate> rates;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.precision_pgb)
    ProgressBar precision_pgb;
    @BindView(R.id.attitude_pgb)
    ProgressBar attitude_pgb;
    @BindView(R.id.average_pgb)
    ProgressBar average_pgb;
    @BindView(R.id.convenience_pgb)
    ProgressBar convenience_pgb;
    @BindView(R.id.price_pgb)
    ProgressBar price_pgb;
    @BindView(R.id.location_pgb)
    ProgressBar location_pgb;
    @BindView(R.id.precision_tv)
    TextView precision_tv;
    @BindView(R.id.attitude_tv)
    TextView attitude_tv;
    @BindView(R.id.average_tv)
    TextView average_tv;
    @BindView(R.id.convenience_tv)
    TextView convenience_tv;
    @BindView(R.id.price_tv)
    TextView price_tv;
    @BindView(R.id.location_tv)
    TextView location_tv;
    @BindView(R.id.rating_rv)
    RecyclerView rating_rv;
    @BindView(R.id.write_btn)
    MaterialButton write_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        ButterKnife.bind(this);

        initIntent();
        initToolbar();
        initWrite_btn();
        setPoints();
        setRates();
    }

    private void initWrite_btn() {
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreationRatingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CREATION_RATING);
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorBackground));
        if(getSupportActionBar() != null)
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle("Danh sách đánh giá");
        }
    }

    void initIntent()
    {
        Room room = (Room) getIntent().getSerializableExtra(RoomDetailActivity.EXTRA_CURRENT_ROOM);
        rates = room.getRates();
    }

    void setPoints()
    {
        float point = 0, precision = 0, convenience = 0, located = 0, price = 0, attitude = 0;
        for(Rate item: rates) {
            point += item.getPoint();
            precision += item.getPrecision();
            convenience += item.getConvenience();
            located += item.getLocated();
            price += item.getPrice();
            attitude += item.getAttitude();
        }

        point = (float) (Math.round(point/rates.size() * 10.0)/10.0);
        precision = (float) (Math.round(precision/rates.size() * 10.0)/10.0);
        convenience = (float) (Math.round(convenience/rates.size() * 10.0)/10.0);
        located = (float) (Math.round(located/rates.size() * 10.0)/10.0);
        price = (float) (Math.round(price/rates.size() * 10.0)/10.0);
        attitude = (float) (Math.round(attitude/rates.size() * 10.0)/10.0);

        average_pgb.setProgress((int)(point/5 *100));
        precision_pgb.setProgress((int)(precision/5 *100));
        convenience_pgb.setProgress((int)(convenience/5 *100));
        location_pgb.setProgress((int)(located/5 *100));
        price_pgb.setProgress((int)(price/5 *100));
        attitude_pgb.setProgress((int)(attitude/5 *100));

        average_tv.setText(point + "");
        precision_tv.setText(precision + "");
        convenience_tv.setText(convenience + "");
        location_tv.setText(located + "");
        price_tv.setText(price + "");
        attitude_tv.setText(attitude + "");

    }

    void setRates()
    {
        RecyclerViewRateAdapter adapter = new RecyclerViewRateAdapter(this, rates, 0);
        rating_rv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        rating_rv.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_CREATION_RATING)
        {
            if(resultCode == RESULT_OK)
            {
                Rate rate = (Rate) data.getSerializableExtra(CreationRatingActivity.EXTRA_RATE);
                if(rate!=null)
                {
                    write_btn.setVisibility(View.GONE);
                    RecyclerViewRateAdapter adapter = (RecyclerViewRateAdapter) rating_rv.getAdapter();
                    adapter.addRating(rate);
                    setPoints();
                }
            }
        }
    }
}
package com.example.airbnb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.airbnb.View.HostingRoom.HostingRoomActivity;
import com.example.airbnb.View.RoomDetail.RoomDetailActivity;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_cv)
    CardView main_cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initMain_cv();
    }

    private void initMain_cv() {
        main_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RoomDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void toHostingRoom(View view) {
        Intent intent = new Intent(getApplicationContext(), HostingRoomActivity.class);
        startActivity(intent);
    }
}
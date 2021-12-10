package com.example.airbnb.View.CreationRating;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.airbnb.Model.Rate;
import com.example.airbnb.Model.User;
import com.example.airbnb.R;
import com.example.airbnb.View.Rating.RatingActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreationRatingActivity extends AppCompatActivity {

    public static final String EXTRA_RATE = "extra_rate";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.precision_rb)
    RatingBar precision_rb;
    @BindView(R.id.convenience_rb)
    RatingBar convenience_rb;
    @BindView(R.id.attitude_rb)
    RatingBar attitude_rb;
    @BindView(R.id.location_rb)
    RatingBar location_rb;
    @BindView(R.id.price_rb)
    RatingBar price_rb;
    @BindView(R.id.completed_btn)
    MaterialButton complete_btn;
    @BindView(R.id.rating_edt)
    TextInputEditText rating_edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_rating);
        ButterKnife.bind(this);

        initToolbar();
        initRatingBar();
        initComplete_btn();
    }

    private void initComplete_btn() {
        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rating_edt.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Chưa viết đánh giá", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), RatingActivity.class);
                Rate rate = createRate();
                intent.putExtra(EXTRA_RATE, rate);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    Rate createRate()
    {
        User user = new User("Khánh Bả", "https://scontent.fpnh22-4.fna.fbcdn.net/v/t39.30808-6/215664306_1957385397777741_2180411434192658599_n.jpg?_nc_cat=108&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=shV2NlRJPcUAX-lVvnD&_nc_ht=scontent.fpnh22-4.fna&oh=c4afc70e521462e61b5985ad00982255&oe=60FE9BA5");
        String comment = rating_edt.getText().toString();
        int precision = (int) precision_rb.getRating();
        int convenience = (int) convenience_rb.getRating();
        int location = (int) location_rb.getRating();
        int price = (int) price_rb.getRating();
        int attitude = (int) attitude_rb.getRating();
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        Long today = MaterialDatePicker.todayInUtcMilliseconds();
        calendar.setTimeInMillis(today);
        String date = "tháng " + (calendar.get(Calendar.MONTH) + 1) + " năm " + calendar.get(Calendar.YEAR);


        return new Rate(user, comment, precision, convenience, location, price, attitude, date);
    }

    private void initRatingBar() {
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle("Viết đánh giá");
        }
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
}
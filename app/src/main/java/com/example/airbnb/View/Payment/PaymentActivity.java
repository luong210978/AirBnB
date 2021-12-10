package com.example.airbnb.View.Payment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.airbnb.R;
import com.example.airbnb.Utils;
import com.example.airbnb.View.BookingInfo.BookingInfoActivity;
import com.example.airbnb.View.RoomDetail.RoomDetailActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity {

    Utils.LoadingDialog completeDialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.visa_btn)
    MaterialButton visa_btn;
    @BindView(R.id.payPal_btn)
    MaterialButton payPal_btn;
    @BindView(R.id.atm_btn)
    MaterialButton ATM_btn;
    @BindView(R.id.payPalTick_iv)
    ImageView payPalTick_iv;
    @BindView(R.id.visaTick_iv)
    ImageView visaTick_iv;
    @BindView(R.id.atmTick_iv)
    ImageView atmTick_iv;
    @BindView(R.id.visa_layout)
    RelativeLayout visa_layout;
    @BindView(R.id.payPal_layout)
    RelativeLayout payPal_layout;
    @BindView(R.id.atm_layout)
    RelativeLayout atm_layout;
    @BindView(R.id.chooseMethod_btn)
    MaterialButton chooseMethod_btn;
    @BindView(R.id.pay_btn)
    MaterialButton pay_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        initToolbar();
        initATM_btn();
        initVisa_btn();
        initPayPal_btn();
        initChooseMethod_btn();
        initPay_btn();
    }


    private void initChooseMethod_btn() {
        chooseMethod_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPayment_layout();
            }
        });
    }

    private void resetPayment_layout() {
        payPal_layout.setVisibility(View.VISIBLE);
        visa_layout.setVisibility(View.VISIBLE);
        atm_layout.setVisibility(View.VISIBLE);
        payPalTick_iv.setVisibility(View.INVISIBLE);
        visaTick_iv.setVisibility(View.INVISIBLE);
        atmTick_iv.setVisibility(View.INVISIBLE);
        payPal_btn.setEnabled(true);
        visa_btn.setEnabled(true);
        ATM_btn.setEnabled(true);
        chooseMethod_btn.setVisibility(View.GONE);
    }

    private void initATM_btn() {
        ATM_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisaDialogFragment fragment = VisaDialogFragment.getInstant();
                fragment.show(getSupportFragmentManager(), fragment.getTag());

            }
        });
    }

    private void initPayPal_btn()
    {
        payPal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.paypal.com/");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
    private void initVisa_btn()
    {
        visa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VisaDialogFragment fragment = VisaDialogFragment.getInstant();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
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
            setTitle("Thanh toán");
        }
    }

    private void initPay_btn()
    {
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseMethod_btn.getVisibility() == View.GONE)
                    Toast.makeText(getApplicationContext(), "Bạn chưa chọn hình thức thanh toán", Toast.LENGTH_SHORT).show();
                else
                {
                    completeDialog = new Utils.LoadingDialog(PaymentActivity.this, "Đang thanh toán ...");
                    completeDialog.startLoading();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            completeDialog.dismiss();
                            Intent intent = new Intent(PaymentActivity.this, RoomDetailActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "Đặt phòng thành công", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
            }
        });
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
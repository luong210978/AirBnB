package com.example.airbnb.View.HostingRoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.airbnb.MainActivity;
import com.example.airbnb.R;
import com.example.airbnb.Utils;
import com.example.airbnb.View.Payment.PaymentActivity;
import com.example.airbnb.View.RoomDetail.RoomDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomContactActivity extends AppCompatActivity {
    Utils.LoadingDialog completeDialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.contact_actv)
    AutoCompleteTextView contact_actv;
    @BindView(R.id.payment_actv)
    AutoCompleteTextView payment_actv;
    @BindView(R.id.bank_actv)
    AutoCompleteTextView bank_actv;

    ArrayAdapter ct_ad;
    ArrayAdapter pm_ad;
    ArrayAdapter bk_ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_contact);
        ButterKnife.bind(this);
        initToolbar();
        initOptions();
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            setTitle("Cho thuê Phòng/Nhà");
        }
    }
    private  void initOptions ()
    {
        String []contact_options = {"Qua Email", "Qua Facebook", "Qua Số Điện Thoại"};
        String []payment_options = {"Thanh Toán Qua PayPal", "Thanh Toán Qua MoMo", "Thanh Toán Internet Banking"};
        String []bank_options = {"Ngân Hàng TMCP Đông Nam Á (SeABank)", "Ngân hàng TMCP An Bình (ABBANK)", "Ngân hàng TMCP Bắc Á (BacABank)", "Ngân hàng TMCP Bản Việt (VietCapitalBank)", "Ngân hàng TMCP Hàng hải Việt Nam (MSB)", "Ngân hàng TMCP Kỹ Thương Việt Nam (Techcombank)", "Ngân hàng TMCP Kiên Long (KienLongBank)", "Ngân hàng TMCP Nam Á (Nam A Bank)", "Ngân hàng TMCP Quốc Dân (NCB)", "Ngân hàng TMCP Việt Nam Thịnh Vượng (VPBank)", "Ngân hàng TMCP Phát triển Thành phố Hồ Chí Minh (HDBank)", "Ngân hàng TMCP Phương Đông (OCB)", "Ngân hàng TMCP Quân đội (MB)", "Ngân hàng TMCP Đại chúng (PVcombank)", "Ngân hàng TMCP Quốc tế Việt Nam (VIB)", "Ngân hàng TMCP Sài Gòn (SCB)"};

        ct_ad = new ArrayAdapter(this, R.layout.item_option, contact_options);
        contact_actv.setText(ct_ad.getItem(0).toString(), true);
        contact_actv.setAdapter(ct_ad);

        pm_ad = new ArrayAdapter(this, R.layout.item_option, payment_options);
        payment_actv.setText(pm_ad.getItem(0).toString(), true);
        payment_actv.setAdapter(pm_ad);

        bk_ad = new ArrayAdapter(this, R.layout.item_option, bank_options);
        bank_actv.setText(bk_ad.getItem(0).toString(), true);
        bank_actv.setAdapter(bk_ad);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }
    public void Next(View view) {
        completeDialog = new Utils.LoadingDialog(RoomContactActivity.this, "Đang xử lý ...");
        completeDialog.startLoading();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                completeDialog.dismiss();

                Intent intent = new Intent(RoomContactActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Thêm phòng thành công", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
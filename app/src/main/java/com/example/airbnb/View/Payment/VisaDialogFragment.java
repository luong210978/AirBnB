package com.example.airbnb.View.Payment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.craftman.cardform.CardForm;
import com.example.airbnb.R;
import com.example.airbnb.Utils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisaDialogFragment extends BottomSheetDialogFragment {

    Utils.LoadingDialog dialog;

    @BindView(R.id.completed_btn)
    MaterialButton completed_btn;
    @BindView(R.id.cardForm)
    CardForm cardForm;
    @BindView(R.id.payment_amount)
    TextView payment_amount;
    @BindView(R.id.payment_amount_holder)
    TextView payment_amount_holder;
    @BindView(R.id.card_name)
    EditText card_name;
    @BindView(R.id.card_number)
    EditText card_number;
    @BindView(R.id.expiry_date)
    EditText expiry_date;
    @BindView(R.id.btn_pay)
    Button btn_pay;


    private static VisaDialogFragment _instant;

    public static VisaDialogFragment getInstant()
    {
        if(_instant == null)
            return new VisaDialogFragment();
        return _instant;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom_sheet_atm, null);
        bottomSheetDialog.setContentView(view);
        ButterKnife.bind(this, view);

        dialog = new Utils.LoadingDialog(getActivity(), "Đang kiểm tra...");
        setupCardForm(bottomSheetDialog);
        setupCancel_btn(bottomSheetDialog);
        setupComplete_btn(bottomSheetDialog);
        setupTextInputLayout();
        return bottomSheetDialog;
    }


    private void setupCardForm(BottomSheetDialog dialog) {
        payment_amount.setText("1.000.000 vnđ");
        payment_amount.setTextColor(getContext().getColor(R.color.colorPrimary));
        payment_amount.setTypeface(Typeface.DEFAULT_BOLD);
        payment_amount_holder.setText("");
        card_name.setHint("Tên thẻ");
        expiry_date.setHint("Ngày hết hạn");
        card_number.setHint("Số thẻ");
        btn_pay.setVisibility(View.GONE);
    }


    private void setupTextInputLayout() {
        //cardNumber_layout.setError(getString(R.string.empty_error));
    }

    private void setupCancel_btn(BottomSheetDialog bottomSheetDialog) {
        bottomSheetDialog.cancel();
    }

    private void setupComplete_btn(BottomSheetDialog bottomSheetDialog) {
        completed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.startLoading();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        bottomSheetDialog.cancel();

                        ((PaymentActivity) getActivity()).atm_layout.setVisibility(View.GONE);
                        ((PaymentActivity) getActivity()).payPal_layout.setVisibility(View.GONE);
                        ((PaymentActivity) getActivity()).visa_btn.setEnabled(false);
                        ((PaymentActivity) getActivity()).visaTick_iv.setVisibility(View.VISIBLE);
                        ((PaymentActivity) getActivity()).chooseMethod_btn.setVisibility(View.VISIBLE);
                    }
                }, 2000);

            }
        });
    }
}

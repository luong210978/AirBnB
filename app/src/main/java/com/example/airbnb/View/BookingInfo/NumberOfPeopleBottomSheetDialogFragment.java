package com.example.airbnb.View.BookingInfo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.airbnb.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberOfPeopleBottomSheetDialogFragment extends BottomSheetDialogFragment {

    @BindView(R.id.adultUp_btn)
    MaterialButton adultUp_btn;
    @BindView(R.id.kidUp_btn)
    MaterialButton kidUp_btn;
    @BindView(R.id.adultDown_btn)
    MaterialButton adultDown_btn;
    @BindView(R.id.kidDown_btn)
    MaterialButton kidDown_btn;
    @BindView(R.id.adultNumber_tv)
    TextView adultNumber_tv;
    @BindView(R.id.kidNumber_tv)
    TextView kidNumber_tv;
    @BindView(R.id.save_btn)
    MaterialButton save_btn;
    @BindView(R.id.delete_btn)
    MaterialButton delete_btn;

    private static NumberOfPeopleBottomSheetDialogFragment _instant;

    public static NumberOfPeopleBottomSheetDialogFragment getInstant()
    {
        if(_instant == null)
            return new NumberOfPeopleBottomSheetDialogFragment();
        return _instant;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom_sheet_number_of_people, null);
        bottomSheetDialog.setContentView(view);
        ButterKnife.bind(this, view);

        setupNumericUpDown();
        setupDelete_btn();
        setupSave_btn(bottomSheetDialog);
        return bottomSheetDialog;
    }


    private void setupDelete_btn() {
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adultNumber_tv.setText("1");
                kidNumber_tv.setText("0");
            }
        });
    }

    private void setupSave_btn(BottomSheetDialog dialog) {
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adultNumber = Integer.parseInt(adultNumber_tv.getText().toString());
                int kidNumber = Integer.parseInt(kidNumber_tv.getText().toString());
                ((BookingInfoActivity) getActivity()).number_of_people_tv.setText((adultNumber + kidNumber) + " người");

                dialog.cancel();
            }
        });
    }

    void setupNumericUpDown()
    {
        adultUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(adultNumber_tv.getText().toString());
                number += 1;
                adultNumber_tv.setText(number + "");
            }
        });

        adultDown_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(adultNumber_tv.getText().toString());
                number -= 1;
                adultNumber_tv.setText(number + "");
            }
        });

        adultNumber_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int adultNumber = Integer.parseInt(s.toString());
                int kidNumber = Integer.parseInt(kidNumber_tv.getText().toString());

                if(adultNumber <=1)
                    adultDown_btn.setEnabled(false);
                else
                    adultDown_btn.setEnabled(true);

                if(adultNumber + kidNumber >= 5)
                {
                    adultUp_btn.setEnabled(false);
                    kidUp_btn.setEnabled(false);
                }
                else
                {
                    adultUp_btn.setEnabled(true);
                    kidUp_btn.setEnabled(true);
                }
            }
        });

        kidUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(kidNumber_tv.getText().toString());
                number += 1;
                kidNumber_tv.setText(number + "");
            }
        });

        kidDown_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(kidNumber_tv.getText().toString());
                number -= 1;
                kidNumber_tv.setText(number + "");
            }
        });

        kidNumber_tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int kidNumber = Integer.parseInt(s.toString());
                int adultNumber = Integer.parseInt(adultNumber_tv.getText().toString());

                if(kidNumber <=0)
                    kidDown_btn.setEnabled(false);
                else
                    kidDown_btn.setEnabled(true);

                if(adultNumber + kidNumber >= 5)
                {
                    adultUp_btn.setEnabled(false);
                    kidUp_btn.setEnabled(false);
                }
                else
                {
                    adultUp_btn.setEnabled(true);
                    kidUp_btn.setEnabled(true);
                }
            }
        });
    }
}

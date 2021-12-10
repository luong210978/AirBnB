/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.example.airbnb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.core.util.Pair;
import androidx.core.view.ContentInfoCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }


    public static String formatPrice(int price)
    {
        String priceStr = price + "";
        if(priceStr.length() < 4)
            return priceStr;
        return formatPrice(price/1000) + "." + String.copyValueOf(priceStr.toCharArray(), priceStr.length() - 4, 3);
    }

    public static MaterialDatePicker<Pair<Long, Long>> createDateRangePicker()
    {
        //calendar
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        Long today = MaterialDatePicker.todayInUtcMilliseconds();
        Long thisMonth = MaterialDatePicker.thisMonthInUtcMilliseconds();
        //calendar.setTimeInMillis(today);

        //Calendar Constraint
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        //constraintsBuilder.setOpenAt(thisMonth);
        constraintsBuilder.setValidator(DateValidatorPointForward.now());


        //picker builder
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Chọn ngày nhận và trả phòng");
        builder.setCalendarConstraints(constraintsBuilder.build());


        //picker
        MaterialDatePicker<Pair<Long, Long>> datePicker = builder.build();
        return datePicker;
    }

    public static MaterialTimePicker createTimePicker()
    {
        MaterialTimePicker.Builder builder = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Chọn giờ nhận phòng");
        MaterialTimePicker timePicker = builder.build();
        return timePicker;
    }

    public static String getTimeStrFromTimePicker(MaterialTimePicker timePicker)
    {
        String hour = timePicker.getHour() + "";
        if(hour.length() < 2)
            hour = "0" + hour;

        String minute = timePicker.getMinute() + "";
        if(minute.length() < 2)
            minute = "0" + minute;

        return hour + ":" + minute;
    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText().toString());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                String text;
                int lineEndIndex;
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);

                if (maxLine == 0) {
                    lineEndIndex = tv.getLayout().getLineEnd(0);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() - 1).toString() + " " + expandText;
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() - 5).toString() + "... " + " " + expandText;
                } else {
                    lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    text = tv.getText().subSequence(0, lineEndIndex).toString() + " " + expandText;
                }
                tv.setText(text);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
                tv.clearComposingText();
                tv.setText(text);
//                tv.setText(addClickablePartTextViewResizable(text, tv, maxLine, expandText, viewMore),
//                        TextView.BufferType.NORMAL);
            }
        });
    }

    private static SpannableString addClickablePartTextViewResizable(final String text,
                                                                            final TextView tv,
                                                                            final int maxLine,
                                                                            final String spanableText,
                                                                            final boolean viewMore) {

        SpannableString spannableString = new SpannableString(text);

        if (text.contains(spanableText)) {
            spannableString.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                    tv.setLayoutParams(tv.getLayoutParams());
                    String tag = (String)(tv.getTag()) + "";
                    tv.setText(tag);
                    tv.invalidate();
                    if (viewMore) {
                        makeTextViewResizable(tv, -1, "Ẩn bớt", false);
                    } else {
                        makeTextViewResizable(tv, 5, "Xem thêm", true);
                    }

                }
            }, text.indexOf(spanableText), text.indexOf(spanableText) + spanableText.length(), 0);
        }
        return spannableString;
    }

    public static class LoadingDialog{
        Activity activity;
        AlertDialog dialog;
        String message;
        public LoadingDialog(Activity activity, String message)
        {
            this.activity = activity;
            this.message = message;
        }

        public void startLoading(){
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.dialog_loading, null);

            builder.setView(view);
            builder.setCancelable(true);

            dialog = builder.create();
            TextView textView = (TextView) view.findViewById(R.id.title_tv);
            textView.setText(message);
            dialog.show();
        }

        public void dismiss()
        {
            dialog.dismiss();
        }
    }

}


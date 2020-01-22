package com.sirio.remindme.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sirio.remindme.R;
import com.sirio.remindme.entities.Reminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReminderCreateUpdateFragment extends BaseFragment {
    int mYear;
    int mMonth;
    int mDay;
    int hour;
    int minute;
    private Reminder reminder;

    private TextView dateTextView;

    public ReminderCreateUpdateFragment(Reminder reminder) {
        super();
        this.reminder = reminder;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View searchView = inflater.inflate(R.layout.fragment_create_update_reminder, container, false);

        dateTextView = (TextView) searchView.findViewById(R.id.date);


        RelativeLayout asd = (RelativeLayout) searchView.findViewById(R.id.dateLayout);

        asd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.FRANCE);
                        String dateText = (sdf.format(myCalendar.getTime()));

                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;

                        Calendar mcurrentDate = Calendar.getInstance();
                        hour = mcurrentDate.get(Calendar.HOUR);
                        minute = mcurrentDate.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selecterMinute) {
                                Calendar myCalendar = Calendar.getInstance();
                                myCalendar.set(Calendar.HOUR, selectedHour);
                                myCalendar.set(Calendar.MINUTE, selecterMinute);
                                String myFormat = "HH:mm"; //Change as you need
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                                String timeText = (sdf.format(myCalendar.getTime()));

                                dateTextView.setText("Seted for " + dateText + " at " + timeText);

                                hour = selectedHour;
                                minute = selecterMinute;
                            }
                        }, hour, minute, true);
                        //mDatePicker.setTitle("Select date");
                        timePickerDialog.show();
                    }
                }, mYear, mMonth, mDay);
                //mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        if(this.reminder == null){
            this.reminder = new Reminder();
            return searchView;
        }
/*
        ((TextView) searchView.findViewById(R.id.name)).setText(bookDetail.getBook());
        ((TextView) searchView.findViewById(R.id.bid)).setText(bookDetail.getBid());
        ((TextView) searchView.findViewById(R.id.ask)).setText(bookDetail.getAsk());
        ((TextView) searchView.findViewById(R.id.low)).setText(bookDetail.getLow());
        ((TextView) searchView.findViewById(R.id.high)).setText(bookDetail.getHigh());
        ((TextView) searchView.findViewById(R.id.h24)).setText(bookDetail.getVolume());

        BigDecimal bid = new BigDecimal(bookDetail.getBid());
        BigDecimal ask = new BigDecimal(bookDetail.getAsk());


        System.out.println(bid);
        System.out.println(ask);
        System.out.println(bid.subtract(ask));
        String spread = bid.subtract(ask).toString();

        ((TextView) searchView.findViewById(R.id.spread)).setText(spread);

 */
        return searchView;
    }
}

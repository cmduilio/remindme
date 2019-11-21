package com.sirio.remindme.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.sirio.remindme.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Add2x1Fragment extends BaseFragment {
    String[] movies = {"Locademia de policia", "Locos del aire 2", "La pistola desnuda", "Rocky"};
    TextView dateText;
    TextView timeText;
    int mYear;
    int mMonth;
    int mDay;
    int hour;
    int minute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add2x1, container, false);

        Spinner dropdown = view.findViewById(R.id.movies);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, this.movies);
        dropdown.setAdapter(adapter);

        dateText = (TextView) view.findViewById(R.id.date);
        timeText = (TextView) view.findViewById(R.id.time);

        LinearLayout asd = (LinearLayout) view.findViewById(R.id.date_time);

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
                        String myFormat = "dd/MM/yy"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                        dateText.setText(sdf.format(myCalendar.getTime()));

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
                                timeText.setText(sdf.format(myCalendar.getTime()));

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

        return view;
    }
}

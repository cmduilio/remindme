package com.sirio.remindme.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sirio.remindme.R;
import com.sirio.remindme.entities.Reminder;
import com.sirio.remindme.events.OnReminderClick;

public class ReminderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView label;
    private TextView days;
    private TextView time;
    private OnReminderClick onReminderClick;
    private int position;
    private Reminder reminder;

    public ReminderViewHolder(View itemView, OnReminderClick onReminderClick) {
        super(itemView);
        this.label = (TextView) itemView.findViewById(R.id.label);
        this.days = (TextView) itemView.findViewById(R.id.days);
        this.time = (TextView) itemView.findViewById(R.id.time);
        this.onReminderClick = onReminderClick;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onReminderClick.onReminderClick(reminder);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLabelText(String name) {
        this.label.setText(name);
    }

    public void setDaysText(String last) {
        this.days.setText(last);
    }

    public void setTimeText(String time) {
        this.time.setText(time);
    }

    public void setReminder(Reminder reminder){
        this.reminder = reminder;
    }
}


package com.sirio.remindme.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sirio.remindme.R;
import com.sirio.remindme.entities.Reminder;
import com.sirio.remindme.events.OnReminderClick;

import java.util.List;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderViewHolder> {
    private List<Reminder> reminders;
    private OnReminderClick onReminderClick;

    public ReminderListAdapter(List<Reminder> reminders, OnReminderClick onReminderClick) {
        this.reminders = reminders;
        this.onReminderClick = onReminderClick;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LinearLayout mLayout = (LinearLayout)
                LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);

        ReminderViewHolder reminderViewHolder = new ReminderViewHolder(mLayout, onReminderClick);

        return reminderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminder reminder = this.reminders.get(position);

        holder.setLabelText(reminder.getLabel());
        holder.setDaysText(reminder.getDays());
        holder.setTimeText(reminder.getTime());
        holder.setPosition(position);

        holder.setReminder(reminders.get(position));
    }

    @Override
    public int getItemCount() {
        return this.reminders.size();
    }
}
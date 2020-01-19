package com.sirio.remindme.events;

import com.sirio.remindme.entities.Reminder;

public interface OnReminderClick {
    void onReminderClick(Reminder bookDetail);
}

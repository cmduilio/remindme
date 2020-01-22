package com.sirio.remindme.repositories.db.reminder;

import com.sirio.remindme.entities.Reminder;

public interface SaveReminder {

    void run(Reminder reminder);
}

package com.sirio.remindme.entities;

public class Reminder extends BaseEntity{

    private int id;
    private String time;
    private String label;
    private String days;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public <T> void update(T object) {
        Reminder reminder = (Reminder) object;
        setIfNotNull(this::setTime, reminder.getTime());
        setIfNotNull(this::setLabel, reminder.getLabel());
        setIfNotNull(this::setDays, reminder.getDays());
    }
}

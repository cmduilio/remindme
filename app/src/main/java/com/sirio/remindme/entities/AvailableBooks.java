package com.sirio.remindme.entities;

import java.util.List;

public class AvailableBooks {

    private String success;
    private List<Reminder> payload;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Reminder> getPayload() {
        return payload;
    }

    public void setPayload(List<Reminder> payload) {
        this.payload = payload;
    }

}

package com.sirio.remindme.entities;

import java.util.List;

public class AvailableBooks {

    private String success;
    private List<Book> payload;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Book> getPayload() {
        return payload;
    }

    public void setPayload(List<Book> payload) {
        this.payload = payload;
    }

}

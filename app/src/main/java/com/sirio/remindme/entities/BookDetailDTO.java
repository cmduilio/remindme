package com.sirio.remindme.entities;

public class BookDetailDTO {

    private String success;
    private BookDetail payload;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public BookDetail getPayload() {
        return payload;
    }

    public void setPayload(BookDetail payload) {
        this.payload = payload;
    }
}

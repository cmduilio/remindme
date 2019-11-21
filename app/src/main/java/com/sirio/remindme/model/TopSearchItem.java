package com.sirio.remindme.model;

public class TopSearchItem {

    String imageUri;
    String title;
    int resId;

    public TopSearchItem(String imageUri, String title, int resId){
        this.imageUri = imageUri;
        this.title = title;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

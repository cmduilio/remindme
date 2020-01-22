package com.sirio.remindme.repositories;

import com.android.volley.VolleyError;

public interface ServerCallback<T>{
    void onSuccess(T result);
    void onFail(VolleyError result);
}

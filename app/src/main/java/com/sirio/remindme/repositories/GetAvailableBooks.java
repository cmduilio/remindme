package com.sirio.remindme.repositories;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.sirio.remindme.entities.AvailableBooks;

public class GetAvailableBooks {

    public void run(final Context context, final ServerCallback callback){
        RequestQueue queue = AppClient.getInstanceQueue(context);
        String url ="https://api.bitso.com/v3/available_books/";

        // AppClient a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    AvailableBooks availableBooks = gson.fromJson(response, AvailableBooks.class);
                    callback.onSuccess(availableBooks);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onFail(error);
                }
            }
        );

        queue.add(stringRequest);
    }
}

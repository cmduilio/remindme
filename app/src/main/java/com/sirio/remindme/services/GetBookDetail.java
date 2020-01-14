package com.sirio.remindme.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.sirio.remindme.entities.Book;
import com.sirio.remindme.entities.BookDetailDTO;

import java.util.List;

public class GetBookDetail {

    private final Gson gson = new Gson();
    private String url = "https://api.bitso.com/v3/ticker/?book=";

    public void run(Context context, List<Book> books, final ServerCallback callback){
        // Instantiate the RequestQueue.
        RequestQueue queue = AppClient.getInstanceQueue(context);
        for (final Book book : books) {
            String tickerUrl = this.url + book.getBook();

            // AppClient a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, tickerUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            BookDetailDTO bookDetail = gson.fromJson(response, BookDetailDTO.class);
                            book.setBookDetail(bookDetail.getPayload());
                            callback.onSuccess(null);
                        }
                    }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
            );

            queue.add(stringRequest);
        }
    }
}

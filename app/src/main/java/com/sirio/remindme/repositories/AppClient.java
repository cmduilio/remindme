package com.sirio.remindme.repositories;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

public class AppClient {

    private static RequestQueue requestQueue;
    private static RequestFuture requestFuture;

    public static RequestQueue getInstanceQueue(Context context){
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);

        return requestQueue;
    }

    public static RequestFuture getInstanceFuture(){
        if (requestFuture == null)
            requestFuture = RequestFuture.newFuture();

        return requestFuture;
    }
}

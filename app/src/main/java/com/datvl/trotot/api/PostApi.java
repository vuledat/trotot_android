package com.datvl.trotot.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.datvl.trotot.OnEventListener;
import com.datvl.trotot.common.NameValuePair;

import org.json.JSONArray;

public class PostApi {
    public static String kq = null;
    private String url;
    private OnEventListener callback;
    private Context context;

    public PostApi(String url, Context context, JSONArray jsonArray, OnEventListener callback) {
        this.context = context;
        this.callback = callback;
        this.url = url;
        postApi(url, context, jsonArray, callback);
    }

    public static String postApi(String url, final Context context, JSONArray jsonArray, final OnEventListener callback){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Function GetApi:", "Can't connect " + error);
            }
        });
        queue.add(stringRequest);
        return kq;
    }
}

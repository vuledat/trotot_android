package com.datvl.trotot.fragment;

import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.datvl.trotot.MainActivity;
import com.datvl.trotot.R;
import com.datvl.trotot.adapter.ListPostAdapter;
import com.datvl.trotot.post.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    RecyclerView recyclerView;
    List<Post> listPost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home,container,false);

        Intent intent = getActivity().getIntent();
        listPost = (List<Post>) intent.getSerializableExtra("ListPost");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_home_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ListPostAdapter viewAdapter = new ListPostAdapter(listPost);
        recyclerView.setAdapter(viewAdapter);
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}

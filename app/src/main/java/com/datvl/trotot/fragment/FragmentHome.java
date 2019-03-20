package com.datvl.trotot.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.datvl.trotot.MainActivity;
import com.datvl.trotot.OnEventListener;
import com.datvl.trotot.R;
import com.datvl.trotot.adapter.ListPostAdapter;
import com.datvl.trotot.api.GetApi;
import com.datvl.trotot.common.Common;
import com.datvl.trotot.post.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentHome extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    List<Post> listPost;
    Common cm;
    public String url = cm.getUrlListPosts();

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_home,container,false);

        Intent intent = getActivity().getIntent();
        listPost = (List<Post>) intent.getSerializableExtra("ListPost");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_home_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ListPostAdapter viewAdapter = new ListPostAdapter(listPost);
        recyclerView.setAdapter(viewAdapter);


        final BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up
                    Log.d("scroll", "scroll ");
                    navigation.setBehaviorTranslationEnabled(true);

                } else {
                    // Scrolling down
                }
            }
        });

        //pull refresh
        final SwipeRefreshLayout swipeLayout;
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.contentViewList);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    List<Post> listPost;
                    @Override public void run() {
                        listPost = new ArrayList<>();
                        GetApi getApi = new GetApi(url, getActivity(), new OnEventListener() {
                            @Override
                            public void onSuccess(JSONArray object) {
                                for (int i=0 ; i< object.length() ; i++){
                                    try {
                                        JSONObject jsonObject = object.getJSONObject(i);
                                        listPost.add(new Post(
                                                Integer.parseInt(jsonObject.getString("id")),
                                                jsonObject.getString("name"),
                                                Integer.parseInt(jsonObject.getString("price")) ,
                                                jsonObject.getString("image"),
                                                jsonObject.getString("content"),
                                                jsonObject.getString("address"),
                                                jsonObject.getString("created_at_string"),
                                                Integer.parseInt(jsonObject.getString("scale"))
                                        ));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_home_view);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                ListPostAdapter viewAdapter = new ListPostAdapter(listPost);
                                recyclerView.setAdapter(viewAdapter);
                                swipeLayout.setRefreshing(false);
                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });
                    }
                }, 2000);
            }
        });
        //end pull refresh

        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onRefresh() {

    }

}

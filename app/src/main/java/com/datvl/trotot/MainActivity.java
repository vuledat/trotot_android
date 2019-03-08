package com.datvl.trotot;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.datvl.trotot.fragment.FragmentHome;
import com.datvl.trotot.fragment.FragmentSetting;
import com.datvl.trotot.post.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    List<Post> listPost;


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getIntent().putExtra("ListPost", (Serializable) listPost);

                    FragmentHome fragmentHome = new FragmentHome();
                    fragmentTransaction.replace(R.id.content,fragmentHome);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_message:
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_setting:
                    FragmentSetting fragmentSetting = new FragmentSetting();
                    fragmentTransaction.replace(R.id.content,fragmentSetting);
                    fragmentTransaction.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAPI();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void getAPI(){
        this.listPost = new ArrayList<>();
        String url ="http://192.168.43.230/trotot/public/list-posts";
        RequestQueue queue = Volley.newRequestQueue(getApplication());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            List<String> list = new ArrayList<String>();
                            JSONArray array = null;
                            array = obj.getJSONArray("data");

                            for(int i = 0 ; i < array.length() ; i++){
                                int price = array.getJSONObject(i).getString("price") == null ? 0 : Integer.parseInt(array.getJSONObject(i).getString("price"));
                                String name = array.getJSONObject(i).getString("name") == null ? "" : array.getJSONObject(i).getString("name");
                                String image = array.getJSONObject(i).getString("image") == null ? "": array.getJSONObject(i).getString("image");
                                String content = array.getJSONObject(i).getString("content") == null ? "" : array.getJSONObject(i).getString("content") ;
                                String address = array.getJSONObject(i).getString("address") == null ? "" : array.getJSONObject(i).getString("address");


                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date time = dateFormat.parse(array.getJSONObject(i).getString("created_at"));

                                listPost.add(new Post(i,name, price , image, content, address, time));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err API", "Can't connect ");
            }
        });
        queue.add(stringRequest);
    }

    public String getFormatedNum(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }
}

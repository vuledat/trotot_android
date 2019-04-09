package com.datvl.trotot;

import android.app.FragmentManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.datvl.trotot.common.Common;
import com.datvl.trotot.fragment.FragmentHome;
import com.datvl.trotot.fragment.FragmentMessage;
import com.datvl.trotot.fragment.FragmentNotice;
import com.datvl.trotot.fragment.FragmentSearch;
import com.datvl.trotot.fragment.FragmentSetting;
import com.datvl.trotot.model.Message;
import com.datvl.trotot.post.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.datvl.trotot.api.GetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    List<Post> listPost;
    Common cm;
    public String url = cm.getUrlListPosts();
    ProgressBar pb;
    GetApi getApi;
    String post = null;
    private FirebaseAuth mAuth;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    getIntent().putExtra("ListPost", (Serializable) listPost);
                    FragmentHome fragmentHome = new FragmentHome();
                    fragmentTransaction.replace(R.id.content,fragmentHome);
                    fragmentTransaction.commit();

                    return true;

                case R.id.navigation_message:
                    FragmentMessage fragmentMessage = new FragmentMessage();
                    fragmentTransaction.replace(R.id.content,fragmentMessage);
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_search:
                    FragmentSearch fragmentSearch = new FragmentSearch();
                    fragmentTransaction.replace(R.id.content,fragmentSearch);
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_notifications:
                    FragmentNotice fragmentNotice = new FragmentNotice();
                    fragmentTransaction.replace(R.id.content,fragmentNotice);
                    fragmentTransaction.commit();
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
    public void setHideProgress(){
        pb = findViewById(R.id.progressBarHome);
        pb.setVisibility(View.GONE);
    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        getIntent().putExtra("ListPost", (Serializable) listPost);
        FragmentHome fragmentHome = new FragmentHome();
        fragmentTransaction.replace(R.id.content,fragmentHome);
        fragmentTransaction.commit();

        GetApi getApi = new GetApi(url, getApplication(), new OnEventListener() {
            @Override
            public void onSuccess(JSONArray object) {

                listPost = new ArrayList<>();
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
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
////        FirebaseUser currentUser = mAuth.getCurrentUser();
////        updateUI(currentUser);
//    }

    public String getFormatedNum(int amount){
        return NumberFormat.getNumberInstance(Locale.US).format(amount);
    }
}

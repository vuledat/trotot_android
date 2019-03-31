package com.datvl.trotot;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.datvl.trotot.common.Common;
import com.datvl.trotot.library.NumberFormat;
import com.datvl.trotot.post.Post;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Chat extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Post> listPost;
    Post post;
    Common cm;
    public String url = cm.getUrlListPostsSaved()+ 1;
    ProgressBar pb;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        animation = AnimationUtils.loadAnimation(getApplication(), R.anim.scale_list);

        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("post");

        TextView txtUserName, txtPrice, txtAddress, txtTime;
        final ImageView imgPost, imgHeart;
        txtUserName = (TextView) findViewById(R.id.user_name);
        txtPrice = (TextView) findViewById(R.id.id_price);
        imgPost = (ImageView) findViewById(R.id.img_view);
        txtAddress = (TextView) findViewById(R.id.address);
        txtTime = (TextView) findViewById(R.id.time);
        imgHeart = (ImageView) findViewById(R.id.item_heart);

        String name = post.getName();
        String name_sub = name.length() > 40 ? name.substring(0,40) + "..." : name;
        int price = post.getPrice();
        String address = post.getAddress();
        String timeAgo = post.getTime();

        setTitle(name);

        txtUserName.setText(name_sub);
        txtPrice.setText("" + NumberFormat.getFormatedNum((int) price) + " đ");
        txtAddress.setText(address);
        txtTime.setText(timeAgo + " | ");

        Picasso.get()
                .load(post.getImage())
                .resize(60,60)
                .into(imgPost);

        imgHeart.setOnClickListener(new View.OnClickListener() {
            int checked = 0;
            @Override
            public void onClick(View v) {
                if (checked == 0){
                    imgHeart.setImageResource(R.drawable.heart_active);
                    checked = 1;
                    imgHeart.startAnimation(animation);
                }
                else{
                    imgHeart.setImageResource(R.drawable.heart);
                    checked =0;
                    imgHeart.startAnimation(animation);
                }
            }
        });
    }
}

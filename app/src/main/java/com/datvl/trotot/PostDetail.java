package com.datvl.trotot;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.datvl.trotot.post.Post;
import com.squareup.picasso.Picasso;

public class PostDetail extends AppCompatActivity {

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("post");

        final TextView namePost = (TextView) findViewById(R.id.text_message);
        final ImageView imgPost = (ImageView) findViewById(R.id.imgPost);
        final TextView txtPrice = (TextView) findViewById(R.id.price);
        final TextView txtContent = (TextView) findViewById(R.id.content);

        namePost.setText(post.getName());
        Picasso.get()
                .load(post.getImage())
                .into(imgPost);
        txtContent.setText(post.getContent());
        txtPrice.setText("" + post.getPrice());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_call:
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:191"));//change the number
                    startActivity(callIntent);
//                    return true;
                return true;
            case R.id.button_share:
//                showHelp();
                return true;
            case R.id.button_message:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","" + post.getName());
                intent.setData(Uri.parse("sms:191"));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

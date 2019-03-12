package com.datvl.trotot;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.datvl.trotot.api.GetApi;
import com.datvl.trotot.library.NumberFormat;
import com.datvl.trotot.post.Post;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostDetail extends AppCompatActivity {

    Post post;
    private String url = "http://192.168.0.108/trotot/public/post/";

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
        final TextView txtScale = (TextView) findViewById(R.id.post_scale);
        final TextView txtTimeAgo = (TextView) findViewById(R.id.timeAgo);

        GetApi getApi = new GetApi(url + post.getId(),getApplication(), new OnEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(JSONArray o) {
                try {
                    JSONObject jsonObject = o.getJSONObject(0);
                    namePost.setText(jsonObject.getString("name"));
                    Picasso.get()
                            .load(jsonObject.getString("image"))
                            .into(imgPost);
                    txtContent.setText(jsonObject.getString("content"));
                    int price = Integer.parseInt(jsonObject.getString("price"));
                    txtPrice.setText("" + NumberFormat.getFormatedNum(price) + " đ");
                    txtScale.setText("" + Integer.parseInt(jsonObject.getString("scale")) + ": " + getString(R.string.met));
                    txtTimeAgo.setText("" + jsonObject.getString("created_at_string"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
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
                    callIntent.setData(Uri.parse("tel:191"));
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

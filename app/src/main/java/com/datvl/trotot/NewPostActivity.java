package com.datvl.trotot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.datvl.trotot.api.GetApi;
import com.datvl.trotot.api.PostApi;
import com.datvl.trotot.common.Common;
import com.datvl.trotot.common.NameValuePair;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class NewPostActivity extends AppCompatActivity {

    private ImageView image1, image2, image3;
    private Bitmap bp1, bp2, bp3;
    private Button btnSendPost;
    private Common cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        image1  = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        btnSendPost = findViewById(R.id.btn_send_post);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoto(1);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoto(2);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoto(3);
            }
        });

        btnSendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPostToServer();
            }
        });
    }

    protected void makePhoto(int id) {
        Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.startActivityForResult(intentCam, id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                bp1 = (Bitmap) data.getExtras().get("data");
                this.image1.setImageBitmap(rotateBitmap(bp1, 90));
                this.image1.setPadding(5, 5, 5, 5);
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                bp2 = (Bitmap) data.getExtras().get("data");
                this.image2.setImageBitmap(rotateBitmap(bp2, 90));
                this.image2.setPadding(5, 5, 5, 5);
            }
        }

        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                bp3 = (Bitmap) data.getExtras().get("data");
                this.image3.setImageBitmap(rotateBitmap(bp3, 90));
                this.image3.setPadding(5, 5, 5, 5);
            }
        }
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    protected void sendPostToServer() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bp1.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte [] byte_arr = stream.toByteArray();
        String base64Img = Base64.encodeToString(byte_arr, Base64.DEFAULT);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(base64Img);

        PostApi postApi = new PostApi(cm.getUrlNewPost(), getApplication(), jsonArray, new OnEventListener() {
            @Override
            public void onSuccess(JSONArray jsonAray) {
                Log.d("post example", jsonAray.toString());
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("post error", e.toString());
            }
        });
    }
}

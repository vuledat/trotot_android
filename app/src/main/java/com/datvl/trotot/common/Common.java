package com.datvl.trotot.common;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.datvl.trotot.R;

public class Common {
//    static String ip = "192.168.1.78";
//    static String ip = "192.168.0.113";
//    static String ip = "192.168.0.108";
    static String ip = "192.168.43.230";
//    static String ip = "192.168.137.186";

    public static String getUrlLogin() {
        String url = "http://" + ip + "/trotot/public/login/";
        return url;
    }

    public static String getUrlListPosts() {
        String url = "http://" + ip + "/trotot/public/list-posts";
        return url;
    }

    public static String getUrlListPostsUser() {
        String url = "http://" + ip + "/trotot/public/list-posts-users/";
        return url;
    }

    public static String getUrlListPostsSaved() {
        String url = "http://" + ip + "/trotot/public/list-posts-saved/";
        return url;
    }

    public static String getUrlPost() {
        String url = "http://" + ip + "/trotot/public/post/";
        return url;
    }

    public static String getUrlPostSaved() {
        String url = "http://" + ip + "/trotot/public/post-saved/";
        return url;
    }

    public static String getUrlDelete() {
        String url = "http://" + ip + "/trotot/public/delete-post-saved/";
        return url;
    }

    public static String getMessageID() {
        String url = "http://" + ip + "/trotot/public/message-id/";
        return url;
    }

    public static void setHideProgress(View view, int id){
        ProgressBar pb = view.findViewById(id);
        pb.setVisibility(View.GONE);
    }

    public static void showToast(Context view, String message, int time){
        Toast.makeText(view, message, time).show();
    }

}

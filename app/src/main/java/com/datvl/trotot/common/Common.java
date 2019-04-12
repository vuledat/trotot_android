package com.datvl.trotot.common;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Common {
//    static String ip = "192.168.1.78";
//    static String ip = "192.168.0.113";
//    static String ip = "192.168.0.108";
//    static String ip = "192.168.56.1";
    static String ip = "192.168.0.186";
//    static String prefix = "/trotot/public/";
    static String prefix = "/";

    public static String getUrlLogin() {
        String url = "http://" + ip + prefix + "login/";
        return url;
    }

    public static String getUrlListPosts() {
        String url = "http://" + ip + prefix + "list-posts";
        return url;
    }

    public static String getUrlListPostsUser() {
        String url = "http://" + ip + prefix + "list-posts-users/";
        return url;
    }

    public static String getUrlListPostsSaved() {
        String url = "http://" + ip + prefix + "list-posts-saved/";
        return url;
    }

    public static String getUrlPost() {
        String url = "http://" + ip + prefix + "post/";
        return url;
    }

    public static String getUrlPostSaved() {
        String url = "http://" + ip + prefix +"post-saved/";
        return url;
    }

    public static String getUrlDelete() {
        String url = "http://" + ip + prefix + "delete-post-saved/";
        return url;
    }

    public static String getMessageID() {
        String url = "http://" + ip + prefix + "message-id/";
        return url;
    }

    public static void setHideProgress(View view, int id){
        ProgressBar pb = view.findViewById(id);
        pb.setVisibility(View.GONE);
    }

    public static void showToast(Context view, String message, int time){
        Toast.makeText(view, message, time).show();
    }

    public static String getUrlNewPost() {
        String url = "http://" + ip + prefix + "new-post";
        return url;
    }

}

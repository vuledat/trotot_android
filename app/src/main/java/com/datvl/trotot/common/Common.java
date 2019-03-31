package com.datvl.trotot.common;

import android.view.View;
import android.widget.ProgressBar;

import com.datvl.trotot.R;

public class Common {
//    static String ip = "192.168.1.78";
//    static String ip = "192.168.6.127";
//    static String ip = "192.168.0.108";
    static String ip = "192.168.43.230";
//    static String ip = "192.168.137.4";

    public static String getUrlLogin() {
        String url = "http://" + ip + "/trotot/public/login/";
        return url;
    }

    public static String getUrlListPosts() {
        String url = "http://" + ip + "/trotot/public/list-posts";
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

    public static void setHideProgress(View view, int id){
        ProgressBar pb = view.findViewById(id);
        pb.setVisibility(View.GONE);
    }
}

package com.datvl.trotot.common;

public class Common {
//    static String ip = "192.168.1.78";
    static String ip = "192.168.0.108";
//    static String ip = "192.168.43.230";

    public static String getUrlListPosts() {
        String url = "http://" + ip + "/trotot/public/list-posts";
        return url;
    }

    public static String getUrlPost() {
        String url = "http://" + ip + "/trotot/public/post/";
        return url;
    }
}

package com.assesment.util;

/**
 * Custom Log method which override android.util.Log function
 */
public class Log {



    public static void D(String tag,String message){
        android.util.Log.d(tag,message);
    }
    public static void E(String tag,String message){
        android.util.Log.e(tag,message);
    }
}

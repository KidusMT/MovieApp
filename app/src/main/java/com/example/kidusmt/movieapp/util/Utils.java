package com.example.kidusmt.movieapp.util;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

/**
 * Common class for functionality such as logging.
 */
public class Utils {

    /**
     * Global debug tag string - very useful for filtering everything out.
     */
    public static final String TAG = "DEV-MISIL-APP";

    /**
     * Show a debug message to the Android Logcat
     *
     * @param source  String source representing the object where the message is coming from.
     * @param message the message to show to the log
     */
    public static void d(String source, String message) {
        log(source, message, false);
    }

    /**
     * Show a debug message to the Android Logcat
     *
     * @param source  Object source representing the object where the message is coming from.
     * @param message the message to show to the log
     */
    public static void d(Object source, String message) {
        log(source.getClass().getSimpleName(), message, false);
    }

    /**
     * Show an error message to the Android Logcat
     *
     * @param source  String source representing the object where the message is coming from.
     * @param message the message to show to the log
     */
    public static void e(String source, String message) {
        log(source, message, true);
    }

    /**
     * Show an error message to the Android Logcat
     *
     * @param source  Object source representing the object where the message is coming from.
     * @param message the message to show to the log
     */
    public static void e(Object source, String message) {
        log(source.getClass().getSimpleName(), message, true);
    }

    /**
     * Show a debug, or error message to the Android Logcat
     * @param source String representing the source of the message.
     * @param message Message String to show on the log
     * @param isError represents if the message should be displayed as a Log.e error or not.
     */
    private static void log(String source, String message, boolean isError) {
        String output = source + " - " + message;
        if (isError) {
            Log.e(TAG, output);
        } else {
            Log.d(TAG, output);
        }
    }

}

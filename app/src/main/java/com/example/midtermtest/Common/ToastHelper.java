package com.example.midtermtest.Common;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
    public static void shortToast(Context context, CharSequence content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT);
    }

    public static void longToast(Context context, CharSequence content) {
        Toast.makeText(context, content, Toast.LENGTH_LONG);
    }
}

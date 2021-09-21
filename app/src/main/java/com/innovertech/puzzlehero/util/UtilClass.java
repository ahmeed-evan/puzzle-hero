package com.innovertech.puzzlehero.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class UtilClass {

    public static void makeToast(Context context, String  message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, BaseTransientBottomBar.LENGTH_LONG).setActionTextColor(Color.RED).show();
    }
}

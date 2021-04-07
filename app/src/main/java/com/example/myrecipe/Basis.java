package com.example.myrecipe;

import android.content.Context;
import android.widget.Toast;

public class Basis {

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static Integer getIntegerNumber(String input) {
        try {
            int zahl = Integer.parseInt(input);
            return new Integer(zahl);
        } catch (NumberFormatException e) {
            return null;
        }

    }
}

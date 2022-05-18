package com.genius.minds.Helper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.genius.minds.R;


public class ProgreesLoader {
    private static Dialog dialog;
    public static void hideProgreesDialog(Context context) {
        context = context;
        try {
            if (dialog.isShowing() == true) {
                dialog.dismiss();
            }
        } catch (Exception ex) {

        }
    }
    public static void showProgressDialog(Context context) {
        try {
            context = context;
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.progress_dialog);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            if (dialog.isShowing() != true) {
                dialog.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

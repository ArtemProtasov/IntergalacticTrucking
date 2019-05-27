package com.example.intergalactictrucking.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.text.Html;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intergalactictrucking.R;

public class UtilsDialog {

    public static String checkErrorMessage(Activity act, String msg) {
        if (msg == null) {
            return act.getString(R.string.check_connection);

        }
        if (msg.contains("UnknownHostException")) {
            return act.getString(R.string.check_connection);
        }
        if (msg.contains("EOFException")) {
            return act.getString(R.string.check_server_connection);
        }
        return msg;
    }

    public static AlertDialog showBasicDialog(final Activity act, String positive_name, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(act);

        msg = checkErrorMessage(act, msg);
        builder.setMessage(Html.fromHtml(msg))
                .setCancelable(false)
                .setPositiveButton(positive_name,
                        (dialog, id) -> dialog.cancel());
        final AlertDialog a = builder.create();
        String aplikasi = act.getResources().getString(R.string.app_name);
        a.setTitle(aplikasi);
        if (positive_name.toLowerCase().equals("error")) {
            a.setOnShowListener(arg0 -> a.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(act.getResources().getColor(R.color.colorRed)));
        }
        a.setIcon(R.mipmap.ic_launcher);
        return a;
    }


    public static void dismissLoading(ProgressDialog mProgressDialog) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            try {
                mProgressDialog.dismiss();

            } catch (Exception e) {
            }
        }
    }

    public static void dismissDialog(AlertDialog mProgressDialog) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public static AlertDialog showBasicDialogKonfirmation(Activity act, String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(act);

        msg = checkErrorMessage(act, msg);
        builder.setMessage(Html.fromHtml(msg))
                .setCancelable(false)
                .setNegativeButton("NO",
                        (dialog, id) -> dialog.cancel())
                .setPositiveButton("YES",
                        (dialog, id) -> dialog.cancel());
        AlertDialog a = builder.create();
        String aplikasi = act.getResources().getString(R.string.app_name);
        a.setTitle(aplikasi);
        a.setIcon(R.mipmap.ic_launcher);
        return a;
    }

    public static AlertDialog showBasicDialogKonfirmation(Activity act, String title, String msg, String mPositiveText, String mNegativeText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(act);

        msg = checkErrorMessage(act, msg);
        builder.setMessage(Html.fromHtml(msg))
                .setCancelable(false)
                .setNegativeButton(mNegativeText,
                        (dialog, id) -> dialog.cancel())
                .setPositiveButton(mPositiveText,
                        (dialog, id) -> dialog.cancel());
        AlertDialog a = builder.create();
        a.setTitle(title);
        return a;
    }

    public static AlertDialog showBasicDialogKonfirmationVersion(Activity act, String title, String msg, String mPositiveText) {

        AlertDialog.Builder builder = new AlertDialog.Builder(act);

        msg = checkErrorMessage(act, msg);
        builder.setMessage(Html.fromHtml(msg))
                .setCancelable(false)
                .setPositiveButton(mPositiveText,
                        (dialog, id) -> dialog.cancel());
        AlertDialog a = builder.create();
        a.setTitle(title);
        return a;
    }

    public static ProgressDialog showLoading(Activity act, ProgressDialog pDialog) {
        if (pDialog != null) {
            pDialog.dismiss();
        }
        pDialog = new ProgressDialog(act);
        pDialog.setTitle("Processing");
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.setIndeterminate(true);
        return pDialog;
    }

    public static String validateEditTextRequired(Activity act, EditText[] msg) {
        StringBuilder sb = new StringBuilder();
        int checkPoint = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[i].getText().toString().isEmpty()) {
                if (checkPoint != 0) {
                    sb.append("<br/>");
                }
                sb.append("- " + msg[i].getHint() + " " + act.getResources().getString(R.string.basic_required));
                checkPoint++;
            }
        }

        return sb.toString();

    }

    public static String validateTextViewRequired(Activity act, TextView[] msg) {
        StringBuilder sb = new StringBuilder();
        int checkPoint = 0;
        for (int i = 0; i < msg.length; i++) {
            if (msg[i].getText().toString().isEmpty()) {
                if (checkPoint != 0) {
                    sb.append("<br/>");
                }
                sb.append("- " + msg[i].getHint() + " " + act.getResources().getString(R.string.basic_required));
                checkPoint++;
            }
        }

        return sb.toString();

    }

}

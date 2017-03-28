package com.udacity.movieapp.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by ahmed agamy on 27/03/2017.
 */

public class Utility {


    public static ProgressDialog createProgressDialog(Context context, int message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(message));
        return progressDialog;
    }


    public static void openYoutubeVideo(Context context, String youtubeVideoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youtubeVideoId));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

}

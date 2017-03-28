package com.udacity.movieapp.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by ahmed agamy on 27/03/2017.
 */

public class ConnectionHelper {


    private Context context;

    public ConnectionHelper(Context context){
        this.context = context;
    }


    public boolean isInternetAvailable(){
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}

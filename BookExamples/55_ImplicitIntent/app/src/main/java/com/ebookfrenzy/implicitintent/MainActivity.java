package com.ebookfrenzy.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showWebPage(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ebookfrenzy.com"));

        if (isIntentAvailable(this, "android.intent.action.VIEW")) {
            startActivity(intent);
        }
        else {
            Log.i("scott","Activity not found");
        }

        //THIS WAS FROM THE DEVELOPMENT SITE DOES THE SAME THING (https://developer.android.com/guide/components/intents-filters)
        /*
        try {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e){
            Log.i("scott",e.getMessage());
        }*/
        //}
    }

    //THIS WAS FROM THE BOOK ON PAGE 443 BUT THEY DID NOT SHOW HOW TO
    public static boolean isIntentAvailable(Context context, String action){
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


}

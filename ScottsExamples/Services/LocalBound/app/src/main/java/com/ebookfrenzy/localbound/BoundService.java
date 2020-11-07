package com.ebookfrenzy.localbound;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;

public class BoundService extends Service {

    private final IBinder myBinder = new MyLocalBinder();

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);

        //IN A LOCAL BOUND SERVICE IT RUNS IN THE SAME PROCESS SO WHEN WE DELAY IT A FEW SECONDS THE BUTTON DOES NOT WORK.
        try {
            Thread.sleep(5000);
        }
        catch (Exception e) {
            return(e.getLocalizedMessage());
        }
        return (dateformat.format(new Date()));
    }

    public class MyLocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

}

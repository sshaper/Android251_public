package com.ebookfrenzy.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.os.AsyncTask;


//THIS JUST EXTENDS SERVICE SO IT WILL NOT BE ASYNC.
public class MyService extends Service {

    public MyService() {
    }

    private static final String TAG = "ServiceExample";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        AsyncTask task = new SrvTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, startId);

        //IF YOU COMMENT OUT THE ASYNCTASK AND UNCOMMENT THIS CODE THE SERVICE WILL BE ON THE MAIN THREAD.

        /*Log.i(TAG, "Service onStartCommand " + startId);
        int i = 0;
        while (i <= 3) {
            try {
                Thread.sleep(10000);
                i++;
            } catch (Exception e) {
            }
            Log.i(TAG, "Service running");
        }*/

        return Service.START_STICKY;
    }

    private class SrvTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected String doInBackground(Integer... params) {

            int startId = params[0];
            int i = 0;
            while (i <= 3) {

                params[0] = params[0];
                publishProgress(params[0]);
                try {
                    Thread.sleep(1000);
                    i++;
                } catch (Exception e) {
                }
            }
            return("Service complete " + startId);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, result);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Service Running " + values[0]);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy");
    }

}

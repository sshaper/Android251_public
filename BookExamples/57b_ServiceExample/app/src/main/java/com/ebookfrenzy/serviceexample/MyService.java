package com.ebookfrenzy.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.os.AsyncTask;


//THIS IS A SERVICE NOT AN INTENT SERVICE (INTENT SERVICE IS A SUBCLASS OF A SERVICE).  A SERVICE DOES NOT
//RUN THINGS ON A SEPARATE THREAD SO IT NEEDS TO ALSO HAVE AN ASYNCHRONOUS CLASS ADDED FOR LONG PROCESSES
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

        //BECAUSE I WANTED TO SEND MORE THAN ONE INTEGER I CREATED THIS ARRAY OF INTEGERS
        Integer [] numbers = {startId, 0};

        //ORIGINAL CODE
        //AsyncTask task = new SrvTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, startId);

        //MY MODIFICATION NOTICE I SENT THE NUMBERS ARRAY OF INTEGERS, THIS WAY I COULD SEND MORE ITEMS TO THE ASYNC
        AsyncTask task = new SrvTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, numbers);

        return Service.START_STICKY;
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

    private class SrvTask extends AsyncTask<Integer, Integer, String> {

        @Override
        //THE PARAMS ARE ALWAYS ARRAYS EVEN WITH JUST ONE VALUE
        protected String doInBackground(Integer... params) {

            //STARTID NEEDS TO BE THE FIRST PARAM
            int startId = params[0];

            int i = 0;
            while (i <= 3) {
                params[1] = i;
                //THE LINE BELOW WOULD ONLY SEND THE FIRST PARAMETER AS THEIR WAS ONLY ONE PARAMETER.
                //publishProgress(params[0]);

                //HERE I AM SENDING THE WHOLE PARAM ARRAY
                publishProgress(params);
                try {
                    //I REDUCED THE SLEEP TIME TO 1 SECOND SO IT WOULD BE QUICKER
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
            Log.i(TAG, "Service Running: start id is: " + values[0] + " Iteration is: " + values[1]);
        }
    }

}

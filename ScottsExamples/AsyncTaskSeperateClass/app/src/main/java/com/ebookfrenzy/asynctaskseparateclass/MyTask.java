package com.ebookfrenzy.asynctaskseparateclass;

import android.os.AsyncTask;

import static com.ebookfrenzy.asynctaskseparateclass.MainActivity.addText;

public class MyTask extends  AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... names) {
            int i, j;
            i = 0;
            while (i <= 5) {
                //HERE I ADD SOME NUMBERS TO THE NAMES ON EACH ITERATION JUST TO HAVE THE VALUES
                //GET INCREMENTED

                for(j = 0; j < names.length; j++){
                    names[j] = names[j] + " - " + (i + j);
                }

                //ORIGINAL CODE FROM BOOK
                //publishProgress(i);

                //SENDING THE NAMES ARRAY TO THE ONPROGRESSUPDATE METHOD
                publishProgress(names);


                try {
                    Thread.sleep(1000);
                    i++;
                }
                catch (Exception e) {
                    return(e.getLocalizedMessage());
                }

            }
            return "On post execute was now called";
        }

        @Override
        protected void onProgressUpdate(String... values) {

            //ORIGINAL CODE FROM BOOK
            //myTextView.setText("Counter = " + values[0]);

            int i;
            String output = "";
            for(i = 0; i < values.length; i++){
                output += values[i] + "\n";
            }
            addText(output);


        }

        @Override
        protected void onPostExecute(String result) {
            
            //IMPORTANT NOTE!!! IF YOU WERE TO USE A RECYCLE VIEW YOU COULD ADD VALUES TO THE ARRAY IN THE DO IN BACKGROUND AND THEN CALL ADAPTER.NOTIFYDATASETCHANGE HERE.
            addText(result);
        }
    }





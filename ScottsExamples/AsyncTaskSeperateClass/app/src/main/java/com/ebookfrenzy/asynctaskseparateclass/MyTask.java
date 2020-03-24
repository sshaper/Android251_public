package com.ebookfrenzy.asynctaskseparateclass;

import android.os.AsyncTask;

import static com.ebookfrenzy.asynctaskseparateclass.MainActivity.addText;

public class MyTask extends  AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... names) {
            int i = 0;
            while (i <= 5) {
                //HERE I ADD SOME NUMBERS TO THE NAMES ON EACH ITERATION JUST TO HAVE THE VALUES
                //GET INCREMENTED
                names[0] = names[0] + " - " + i;
                names [1] = names[1] + " - " + (i + 1);

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

            //OUTPUTTING ARRAY OF STRING VALUES
            //myTextView.setText(values[0] + "\n" + values[1]);
            addText(values[0] + "\n" + values[1]);
        }

        @Override
        protected void onPostExecute(String result) {
            //THIS WORKS FINE AND GOOD BECAUSE THIS IS IN THE MAIN ACTIVITY SO I CAN GRAB THE
            //TEXTVIEW AS WE CANNOT RETURN FROM HERE

            //IMPORTANT NOTE!!! YOU WERE TO USE A RECYCLE VIEW YOU COULD ADD VALUES TO THE ARRAY IN THE DO IN BACKGROUND AND THEN CALL ADAPTER.NOTIFYDATASETCHANGE HERE.
            //myTextView.setText(result);
            addText(result);
        }
    }





package com.ebookfrenzy.asyncdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.AsyncTask;

//I MODIFIED THIS FROM THE BOOK EXAMPLE TO SHOW HOW TO PASS DATA TO THE ASYNC CLASS
public class MainActivity extends AppCompatActivity {

    private TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.myTextView);
    }

    public void buttonClick(View view)
    {
        //I CREATE THIS ARRAY OF STRINGS THAT CONTAINS TWO NAMES
        String [] names = {"scott","karen"};

        //HERE I PASS IT TO THE DOINBACKGROUND METHOD
        AsyncTask task = new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, names);

    }

    //ORIGINAL CODE FROM BOOK
    //private class MyTask extends AsyncTask<Void, Integer, String> {

    //I HAD TO CHANGE THE VOID PARAMETER TO STRING BECAUSE I WAS SENDING STRING
    //I ALSO HAD TO CHANGE THE INTEGER PARAMETER TO STRING BECAUSE I WAS SENDING A STRING TO THE
    //ONPROGRESSUPDATE METHOD.
    private class MyTask extends AsyncTask<String, String, String> {

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
            return "On Post Execute was now called";
        }

        @Override
        protected void onProgressUpdate(String... values) {

            //ORIGINAL CODE FROM BOOK
            //myTextView.setText("Counter = " + values[0]);

           //OUTPUTTING ARRAY OF STRING VALUES
            myTextView.setText(values[0] + "\n" + values[1]);
        }

        @Override
        protected void onPostExecute(String result) {
            //THIS WORKS FINE AND GOOD BECAUSE THIS IS IN THE MAIN ACTIVITY SO I CAN GRAB THE
            //TEXTVIEW AS WE CANNOT RETURN FROM HERE
            myTextView.setText(result);
        }
    }

}

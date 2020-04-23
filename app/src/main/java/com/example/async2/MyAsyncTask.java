package com.example.async2;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mResultView;
    private WeakReference<ProgressBar> mProgressBar;
    private static final int LOAD = 15;

    MyAsyncTask(TextView tv, TextView result, ProgressBar bar) {

        mTextView = new WeakReference<>(tv);
        mResultView = new WeakReference<>(result);
        mProgressBar = new WeakReference<>(bar);
    }

    @Override
    protected String doInBackground(Void... voids) {

        //Generate random number
        Random r = new Random();
        int n = r.nextInt(11);

        //make the task take long enough se we can rotate the device while thread is running
        int s = n * 500;

        //Load size per increment
        int load = s/LOAD;


        //Sleep for a random amount of time and divide into parts based on the load size
        for (int i = 0; i < LOAD; i++){
            try {
                Thread.sleep(load);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            //Sends progressbar data
            publishProgress(((i + 1) * 100 / LOAD));
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {

        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setProgress(values[0]);
        mResultView.get().setText("Sleeping in Progress: " + values[0] + "%");
    }
}


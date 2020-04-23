package com.example.async2;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mResultView;

    MyAsyncTask(TextView tv, TextView result) {

        mTextView = new WeakReference<>(tv);
        mResultView = new WeakReference<>(result);
    }

    @Override
    protected String doInBackground(Void... voids) {

        //Generate random number
        Random r = new Random();
        int n = r.nextInt(11);

        //make the task take long enough se we can rotate the device while thread is running
        int s = n * 500;

        //postPublish
        publishProgress(s);

        //Sleep for a random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {

        mTextView.get().setText(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mResultView.get().setText("Current sleep time: " + values[0] + " ms");
    }
}


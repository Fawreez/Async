package com.example.async2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView mProgressView;
    private ProgressBar mProgressBar;
    private static final String TEXT_STATE = "currentText";
    private static final String TEXT_PROGRESS = "CurrentSleepDuration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgressView = findViewById(R.id.textViewResult);
        mProgressBar = findViewById(R.id.progressBar);

        if(savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgressView.setText(savedInstanceState.getString(TEXT_PROGRESS));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
        outState.putString(TEXT_PROGRESS, mProgressView.getText().toString());
    }

    public void startTask(View view) {

        mTextView.setText(R.string.nap);
        new MyAsyncTask(mTextView, mProgressView, mProgressBar).execute();
    }
}

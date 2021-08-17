package com.example.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button startButton, stopButton;
    private TextView resultTextView;
    int seconds;
    boolean running;
    boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        resultTextView = findViewById(R.id.resultTextView);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(wasRunning){
            running = true;
        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
    public void onClickStart(View view)
    {
        running = true;
    }
    public void onClickStop(View view)
    {
        running = false;
    }
    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }
    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                resultTextView.setText(time);
                if(running){
                    seconds ++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }


}



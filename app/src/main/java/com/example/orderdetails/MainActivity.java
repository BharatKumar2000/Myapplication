package com.example.orderdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity {

    ImageView[] stages;
    ProgressBar progress;
    CountDownTimer timer;

    void reset_stages() {
        for(ImageView stage : stages) stage.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = findViewById(R.id.progress);
        stages = new ImageView[] {
                findViewById(R.id.icon_stage_1), findViewById(R.id.icon_stage_2), findViewById(R.id.icon_stage_3),
                findViewById(R.id.icon_stage_4), findViewById(R.id.icon_stage_5)
        };
        reset_stages(); progress.setProgress(0);
        if(timer != null) timer.cancel();
        timer = new CountDownTimer(5 * 5000, 5 * 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                progress.setProgress(progress.getProgress() + 1);
                if(progress.getProgress() >= 0) stages[0].setVisibility(View.VISIBLE);
                if(progress.getProgress() >= (25 - 1)) stages[1].setVisibility(View.VISIBLE);
                if(progress.getProgress() >= (50 - 1)) stages[2].setVisibility(View.VISIBLE);
                if(progress.getProgress() >= (75 - 1)) stages[3].setVisibility(View.VISIBLE);
            }
            @Override
            public void onFinish() {
                stages[4].setVisibility(View.VISIBLE);
            }
        };
        timer.start();
        Button B=(Button)findViewById(R.id.bt1);
        B.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Hno 12+Road number 17+West punjabi bagh+delhi");

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
    }
}


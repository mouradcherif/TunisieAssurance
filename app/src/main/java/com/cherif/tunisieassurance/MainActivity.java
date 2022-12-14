package com.cherif.tunisieassurance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MotionLayout motion_layout = findViewById(R.id.motion_layout);


        motion_layout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
                String FirstTime = preferences.getString("FirstTimeOpen","");
                if (FirstTime.equals("")) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("FirstTimeOpen","No");
                    editor.apply();
                    Intent intent = new  Intent(getBaseContext(), IntroActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new  Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                }


            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }
}
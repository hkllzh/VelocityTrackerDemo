package com.hkllzh.velocitytrackerdemo;

import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private View activity_main;
    private TextView tvText;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = findViewById(R.id.activity_main);
        tvText = (TextView) findViewById(R.id.tvText);

        activity_main.setOnTouchListener(new View.OnTouchListener() {
            VelocityTracker tracker = null;

            @Override
            public boolean onTouch(View view, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (null == tracker) {
                            tracker = VelocityTracker.obtain();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        tracker.addMovement(event);
                        tracker.computeCurrentVelocity(1000);
                        float x = VelocityTrackerCompat.getXVelocity(tracker, 0);
                        float y = VelocityTrackerCompat.getYVelocity(tracker, 0);

                        tvText.setText("x:" + x + "\ny:" + y);

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (null != tracker) {
                            tracker.clear();
                            tracker.recycle();
                        }
                        break;
                }
                return true;
            }
        });
    }
}

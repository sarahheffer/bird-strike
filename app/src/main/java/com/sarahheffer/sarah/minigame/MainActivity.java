package com.sarahheffer.sarah.minigame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.sarahheffer.sarah.minigame.drawing.GameCanvas;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Handler frame = new Handler();
    private static final int FRAME_RATE = 20;
    private SensorManager mSensorManager;

    int mScreenWidth, mScreenHeight;
    android.graphics.PointF mPlayerPosition;
    android.graphics.PointF mPlayerSpeed;


    // SETUP
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setupWindow();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initGfx();
            }
        }, 1000);

        init();
    }

    private void setupWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(0xFFFFFFFF, WindowManager.LayoutParams.FLAG_FULLSCREEN |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void init() {
        Display display = getWindowManager().getDefaultDisplay();
        mScreenWidth = display.getWidth();
        mScreenHeight = display.getHeight();
        mPlayerPosition = new android.graphics.PointF();
        mPlayerSpeed = new android.graphics.PointF();

        mPlayerPosition.x = mScreenWidth /2;
        mPlayerPosition.y = mScreenHeight /2;
        mPlayerSpeed.x = 0;
        mPlayerSpeed.y = 0;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    synchronized public void initGfx() {
        frame.removeCallbacks(frameUpdate);
        frame.postDelayed(frameUpdate, FRAME_RATE);
    }

    private Runnable frameUpdate = new Runnable() {
        @Override
        synchronized public void run() {
            frame.removeCallbacks(frameUpdate);
            mPlayerPosition.x += mPlayerSpeed.x;
            mPlayerPosition.y += mPlayerSpeed.y;

            if (mPlayerPosition.x > mScreenWidth) mPlayerPosition.x=0;
            if (mPlayerPosition.y > mScreenHeight) mPlayerPosition.y=0;
            if (mPlayerPosition.x < 0) mPlayerPosition.x= mScreenWidth;
            if (mPlayerPosition.y < 0) mPlayerPosition.y= mScreenHeight;
            ((GameCanvas)findViewById(R.id.game_canvas)).movePlayerTo((int) mPlayerPosition.x, (int) mPlayerPosition.y);

            ((GameCanvas)findViewById(R.id.game_canvas)).invalidate();
            frame.postDelayed(frameUpdate, FRAME_RATE);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }




    // ACCELERATION SENSOR EVENTS
    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("SARAH", "Sensor change! " + event.values[0] + "     " + event.values[1]);
        mPlayerSpeed.x = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }
}
package com.sarahheffer.sarah.minigame.activities;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;

import com.sarahheffer.sarah.minigame.R;
import com.sarahheffer.sarah.minigame.drawing.GameCanvas;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Handler frame = new Handler();
    private static final int FRAME_RATE = 20;
    private SensorManager mSensorManager;
    private GameCanvas mGameCanvas;

    int mScreenWidth, mScreenHeight;
    android.graphics.PointF mPlayerPosition;
    android.graphics.PointF mPlayerSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    synchronized public void initGfx() {
        mGameCanvas = (GameCanvas) findViewById(R.id.game_canvas);
        frame.removeCallbacks(frameUpdate);
        frame.postDelayed(frameUpdate, FRAME_RATE);
    }

    private void init() {
        Display display = getWindowManager().getDefaultDisplay();
        mScreenWidth = display.getWidth();
        mScreenHeight = display.getHeight();
        ((GameCanvas) findViewById(R.id.game_canvas)).setScreenParams(mScreenWidth, mScreenHeight);

        mPlayerPosition = new android.graphics.PointF();
        mPlayerSpeed = new android.graphics.PointF();

        mPlayerPosition.x = mScreenWidth /2;
        mPlayerPosition.y = mScreenHeight - 300;
        mPlayerSpeed.x = 0;
        mPlayerSpeed.y = 0;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private Runnable frameUpdate = new Runnable() {
        @Override
        synchronized public void run() {
            frame.removeCallbacks(frameUpdate);
            mPlayerPosition.x += mPlayerSpeed.x;

            if (mPlayerPosition.x > mScreenWidth) mPlayerPosition.x=-50;
            if (mPlayerPosition.x < -50) mPlayerPosition.x= mScreenWidth;
            Point p = new Point((int) mPlayerPosition.x, (int) mPlayerPosition.y);
            mGameCanvas.movePlayerTo(p);

            mGameCanvas.invalidate();
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
        hideActionAndStatusBar();
        mSensorManager.registerListener(this, mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    private void hideActionAndStatusBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    // ACCELERATION SENSOR EVENTS
    @Override
    public void onSensorChanged(SensorEvent event) {
        mPlayerSpeed.x = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing
    }
}
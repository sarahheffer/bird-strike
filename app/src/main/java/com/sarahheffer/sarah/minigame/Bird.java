package com.sarahheffer.sarah.minigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class Bird extends View {

    private enum BirdState {UP, NEUTRAL, DOWN}
    private BirdState mBirdState;

    private int frame = 0;
    private Bitmap mBirdBitmap = null;
    private Rect mBirdBounds = new Rect(0,0,0,0);
    private Point mBirdLocation;

    public Bird(Context context) {
        super(context);
        mBirdLocation = new Point(-1,-1);
        mBirdState = BirdState.NEUTRAL;
        updateBitmap();
        mBirdBounds = new Rect(0,0, mBirdBitmap.getWidth(), mBirdBitmap.getHeight());
    }

    synchronized public void setBirdLocation(Point p) {
        mBirdLocation = p;
    }

    synchronized public Point getBirdLocation() {
        return mBirdLocation;
    }

    synchronized public Bitmap getBirdBitmap() {
        return mBirdBitmap;
    }

    public void updateBitmap() {
        switch (mBirdState) {
            case UP:
                mBirdBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.animalscrbetween1);
                break;
            case NEUTRAL:
                mBirdBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.animalscrbetween2);
                break;
            case DOWN:
                mBirdBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.animalscrbetween3);
                break;
        }
    }

    public void updateBirdState() {
        if (frame % 7 == 0) {
            switch (mBirdState) {
                case UP:
                    mBirdState = BirdState.NEUTRAL;
                    break;
                case NEUTRAL:
                    mBirdState = BirdState.DOWN;
                    break;
                case DOWN:
                    mBirdState = BirdState.UP;
                    break;
            }
        }
        frame++;
    }
}

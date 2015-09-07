package com.sarahheffer.sarah.minigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class Bird extends View {

    private int mStarsCollected = 0;

    private enum BirdState {UP, NEUTRAL, DOWN}
    private BirdState mBirdState;

    private int frame = 0;
    private Bitmap mBirdBitmap = null;
    private Rect mBirdBounds = new Rect(0,0,0,0);
    private Point mBirdLocation;

    public Bird(Context context) {
        super(context);
        mBirdLocation = new Point(-100,-100);
        mBirdState = BirdState.NEUTRAL;
        updateBitmap();
        mBirdBounds = new Rect(-1,-1, 0, 0);
    }

    synchronized public void updateStarsCollected() {
        mStarsCollected++;
    }

    synchronized public int getStarsCollected() {
        return mStarsCollected;
    }

    synchronized public void setLocation(Point p) {
        mBirdLocation = p;
        updateBounds();
    }

    synchronized public Point getLocation() {
        return mBirdLocation;
    }

    private void updateBounds() {
        int left = mBirdLocation.x + 18;
        int top = mBirdLocation.y + 20;
        int right = left + mBirdBitmap.getWidth() - 36;
        int bottom = top + mBirdBitmap.getHeight()-20;
        mBirdBounds = new Rect(left, top, right, bottom);
    }

    synchronized public Rect getBounds() {
        return mBirdBounds;
    }

    synchronized public Bitmap getBitmap() {
        return mBirdBitmap;
    }

    public void updateBitmap() {
        switch (mBirdState) {
            case UP:
                mBirdBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird_up);
                break;
            case NEUTRAL:
                mBirdBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird_neutral);
                break;
            case DOWN:
                mBirdBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bird_down);
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

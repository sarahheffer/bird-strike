package com.sarahheffer.sarah.minigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class Star extends View {

    private Bitmap mStarBitmap = null;
    private Rect mStarBounds = new Rect(0,0,0,0);
    private Point mStarLocation;

    public Star(Context context, Point p) {
        super(context);
        mStarLocation = p;
        mStarBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star);
        mStarBounds = new Rect(p.x+2, p.y+2, p.x + mStarBitmap.getWidth()-2, p.y + mStarBitmap.getHeight()-2);
    }

    synchronized public Point getLocation() {
        return mStarLocation;
    }

    synchronized public Bitmap getBitmap() {
        return mStarBitmap;
    }

    synchronized public Rect getBounds() {
        return mStarBounds;
    }

    public void moveStar() {
        mStarLocation.y += 3;
        updateBounds();
    }

    private void updateBounds() {
        int left = mStarLocation.x +2;
        int top = mStarLocation.y +2;
        int right = left + mStarBitmap.getWidth()-4;
        int bottom = top + mStarBitmap.getHeight()-4;
        mStarBounds = new Rect(left, top, right, bottom);
    }
}

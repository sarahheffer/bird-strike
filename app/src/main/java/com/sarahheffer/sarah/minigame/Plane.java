package com.sarahheffer.sarah.minigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class Plane extends View {

    private Bitmap mPlaneBitmap = null;
    private Rect mPlaneBounds = new Rect(0,0,0,0);
    private Point mPlaneLocation;
    private Matrix mMatrix;

    public Plane(Context context) {
        super(context);
        mPlaneLocation = new Point(200,200);
        mPlaneBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plane_3);
        mPlaneBounds= new Rect(0,0,mPlaneBitmap.getWidth(),mPlaneBitmap.getHeight());
        updateMatrix();
    }

    synchronized public void setLocation(Point p) {
        mPlaneLocation = p;
        updateBounds();
    }

    synchronized public Point getLocation() {
        return mPlaneLocation;
    }

    synchronized public Bitmap getBitmap() {
        return mPlaneBitmap;
    }

    private void updateBounds() {
        int left = mPlaneLocation.x;
        int top = mPlaneLocation.y;
        int right = left + mPlaneBitmap.getWidth();
        int bottom = top + mPlaneBitmap.getHeight();
        mPlaneBounds = new Rect(left, top, right, bottom);
    }

    public void updateMatrix() {
        int rotation = 90;
        Matrix m = new Matrix();
        m.postRotate(rotation, mPlaneBitmap.getWidth() / 2, mPlaneBitmap.getHeight() / 2);
        m.postTranslate(mPlaneLocation.x, mPlaneLocation.y);

        mMatrix = m;
    }

    public Matrix getMatrix(){
        return mMatrix;
    }
}

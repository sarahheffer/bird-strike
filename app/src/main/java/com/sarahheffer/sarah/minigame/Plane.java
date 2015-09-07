package com.sarahheffer.sarah.minigame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import com.sarahheffer.sarah.minigame.drawing.DrawingUtils;

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
        mMatrix = DrawingUtils.getMatrix(0, mPlaneBitmap, mPlaneLocation);
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

    public Matrix getMatrix(){
        return mMatrix;
    }
}

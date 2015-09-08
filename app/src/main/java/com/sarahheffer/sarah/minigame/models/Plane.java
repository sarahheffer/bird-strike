package com.sarahheffer.sarah.minigame.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import com.sarahheffer.sarah.minigame.R;
import com.sarahheffer.sarah.minigame.drawing.DrawingUtils;

public class Plane extends View {

    private int mScreenWidth;
    private int mScreenHeight;

    private enum DirectionFacing {SOUTH_EAST, DOWN, SOUTH_WEST}
    private DirectionFacing mDirectionFacing;

    private Bitmap mPlaneBitmap = null;
    private Rect mPlaneBounds = new Rect(0,0,0,0);
    private Point mPlaneLocation;
    private Matrix mMatrix;

    public Plane(Context context) {
        super(context);
        mPlaneBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plane_2);
        mPlaneLocation = new Point(-1000,-1000);
        mDirectionFacing = DirectionFacing.DOWN;
    }

    private void resetPlane() {
        setPlaneDirectionAndLocation();
    }

    public Bitmap getBitmap() {
        return mPlaneBitmap;
    }

    public Matrix getMatrix(){
        return mMatrix;
    }

    public Point getLocation() {
        return mPlaneLocation;
    }

    public Rect getBounds() {
        return mPlaneBounds;
    }

    private void updateBounds() {
        int left = mPlaneLocation.x;
        int top = mPlaneLocation.y;
        int right = left + mPlaneBitmap.getWidth();
        int bottom = top + mPlaneBitmap.getHeight();
        mPlaneBounds = new Rect(left, top, right, bottom);
    }

    public void movePlane() {
        switch (mDirectionFacing) {
            case SOUTH_EAST:
                mPlaneLocation.y+=4;
                mPlaneLocation.x+=4;
                mMatrix.postTranslate(4,4);
                break;
            case DOWN:
                mPlaneLocation.y+=5;
                mMatrix.postTranslate(0,5);
                break;
            case SOUTH_WEST:
                mPlaneLocation.y+=4;
                mPlaneLocation.x-=4;
                mMatrix.postTranslate(-4,4);
                break;
        }
        if (mPlaneLocation.y > mScreenHeight+100) {
            resetPlane();
        } else {
            updateBounds();
        }
    }

    private void setPlaneDirectionAndLocation() {
        int x, y;
        int direction = DrawingUtils.randInt(0,2);
        mDirectionFacing = DirectionFacing.values()[direction];
        switch (mDirectionFacing) {
            case SOUTH_EAST:
                x = -600;
                y = -500 + DrawingUtils.randInt(0,500);
                mPlaneLocation = new Point(x,y);
                mMatrix = DrawingUtils.getMatrix(315, mPlaneBitmap, mPlaneLocation);
                break;
            case DOWN:
                x = DrawingUtils.randInt(0,mScreenWidth-100);
                y = -800;
                mPlaneLocation = new Point(x,y);
                mMatrix = DrawingUtils.getMatrix(0, mPlaneBitmap, mPlaneLocation);
                break;
            case SOUTH_WEST:
                x = mScreenWidth+100;
                y = -500 + DrawingUtils.randInt(0,500);
                mPlaneLocation = new Point(x,y);
                mMatrix = DrawingUtils.getMatrix(45, mPlaneBitmap, mPlaneLocation);
                break;
        }
        updateBounds();
    }

    public void setScreenParams(int screenWidth, int screenHeight){
        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;
        resetPlane();
    }
}

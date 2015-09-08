package com.sarahheffer.sarah.minigame.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sarahheffer.sarah.minigame.Bird;
import com.sarahheffer.sarah.minigame.Cloud;
import com.sarahheffer.sarah.minigame.Plane;
import com.sarahheffer.sarah.minigame.R;
import com.sarahheffer.sarah.minigame.Star;

import java.util.ArrayList;

public class GameCanvas extends View {

    int mScreenWidth, mScreenHeight;
    private Context mContext;

    private Paint paint;
    private Bird bird;
    private Cloud cloud1;
    private Cloud cloud2;
    private Cloud cloud3;
    private Plane plane;

    private ArrayList<Star> mStarList = null;
    private int mNumStars = 250;

    public GameCanvas(Context context, AttributeSet aSet) {
        super(context, aSet);
        mContext = context;
        paint = new Paint();
        bird = new Bird(context);
        cloud1 = new Cloud(context);
        cloud2 = new Cloud(context);
        cloud3 = new Cloud(context);
        plane = new Plane(context);
    }

    public void setScreenParams(int screenWidth, int screenHeight){
        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;
        initializeStars(mContext, mScreenWidth);
        cloud1.setScreenParams(mScreenWidth, mScreenHeight);
        cloud2.setScreenParams(mScreenWidth, mScreenHeight);
        cloud3.setScreenParams(mScreenWidth, mScreenHeight);
        plane.setScreenParams(mScreenWidth, mScreenHeight);
    }

    @Override
    synchronized public void onDraw(Canvas canvas) {
        drawCanvas(canvas);
        drawClouds(canvas);
        drawStars(canvas);
        drawPlane(canvas);
        drawBird(canvas);
        drawStats(canvas);
        checkBirdStarCollision();
        checkBirdPlaneCollision();
    }

    private void checkBirdPlaneCollision() {
        if (CollisionUtils.isPlaneCollisionDetected(bird, plane)) {
            Log.d("SARAH_DEBUG", "BIRD/PLANE COLLISION");
        }
    }

    public void drawCanvas(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setAlpha(255);
        paint.setStrokeWidth(1);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    private void drawClouds(Canvas canvas) {
        canvas.drawBitmap(cloud1.getBitmap(), cloud1.getLocation().x, cloud1.getLocation().y, null);
        canvas.drawBitmap(cloud2.getBitmap(), cloud2.getLocation().x, cloud2.getLocation().y, null);
        canvas.drawBitmap(cloud3.getBitmap(), cloud3.getLocation().x, cloud3.getLocation().y, null);
        cloud1.moveCloud();
        cloud2.moveCloud();
        cloud3.moveCloud();
    }

    private void drawStars(Canvas canvas) {
        for (int i=0; i< mNumStars; i++) {
            Star star = mStarList.get(i);
            canvas.drawBitmap(star.getBitmap(), star.getLocation().x, star.getLocation().y, null);
            star.moveStar();
        }
    }

    private void drawPlane(Canvas canvas) {
        canvas.drawBitmap(plane.getBitmap(), plane.getMatrix(), paint);
        plane.movePlane();
    }

    private void drawBird(Canvas canvas) {
            canvas.drawBitmap(bird.getBitmap(), bird.getLocation().x, bird.getLocation().y, null);
            bird.updateBirdState();
            bird.updateBitmap();
    }

    private void drawStats(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Stars: " + bird.getStarsCollected(), 40, 65, paint);
    }

    private void checkBirdStarCollision() {
        for (int i=0; i<mNumStars; i++){
            Star star = mStarList.get(i);
            if (isCollision(star.getBounds(), bird.getBounds())) {
                bird.updateStarsCollected();
                mStarList.remove(i);
                mNumStars--;
            }
        }
    }

    public void movePlayerTo(Point p) {
        bird.setLocation(p);
    }

    private boolean isCollision(Rect r1, Rect r2) {
        return r1.intersect(r2);
    }

    public void initializeStars(Context context, int maxX) {
        mStarList = new ArrayList<Star>();
        for (int i = 0; i < mNumStars; i++) {
            int x = DrawingUtils.randInt(10, maxX - 10);
            int y = -50 - DrawingUtils.randInt(0, 100000);
            Point p = new Point(x,y);
            mStarList.add(new Star(context, p));
        }
    }
}

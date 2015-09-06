package com.sarahheffer.sarah.minigame.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.sarahheffer.sarah.minigame.Bird;
import com.sarahheffer.sarah.minigame.Cloud;
import com.sarahheffer.sarah.minigame.R;

public class GameCanvas extends View {

    int mScreenWidth, mScreenHeight;

    private Paint paint;
    private Bird bird;
    private Cloud cloud1;
    private Cloud cloud2;
    private Cloud cloud3;

    public GameCanvas(Context context, AttributeSet aSet) {
        super(context, aSet);
        paint = new Paint();
        bird = new Bird(context);
        cloud1 = new Cloud(context);
        cloud2 = new Cloud(context);
        cloud3 = new Cloud(context);
    }

    public void setScreenParams(int screenWidth, int screenHeight){
        mScreenWidth = screenWidth;
        mScreenHeight = screenHeight;
        cloud1.setScreenParams(mScreenWidth, mScreenHeight);
        cloud2.setScreenParams(mScreenWidth, mScreenHeight);
        cloud3.setScreenParams(mScreenWidth, mScreenHeight);
    }

    @Override
    synchronized public void onDraw(Canvas canvas) {
        drawCanvas(canvas);
        drawClouds(canvas);
        drawBird(canvas);
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

    private void drawBird(Canvas canvas) {
        if (bird.getBirdLocation().x >= 0) {
            canvas.drawBitmap(bird.getBirdBitmap(), bird.getBirdLocation().x, bird.getBirdLocation().y, null);
            bird.updateBirdState();
            bird.updateBitmap();
        }
    }

    public void movePlayerTo(Point p) {
        bird.setBirdLocation(p);
    }
}
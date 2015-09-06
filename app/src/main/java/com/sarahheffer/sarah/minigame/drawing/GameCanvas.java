package com.sarahheffer.sarah.minigame.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.sarahheffer.sarah.minigame.Bird;
import com.sarahheffer.sarah.minigame.R;

public class GameCanvas extends View {

    private Paint paint;
    private Bird bird;

    public GameCanvas(Context context, AttributeSet aSet) {
        super(context, aSet);
        paint = new Paint();
        bird = new Bird(context);
    }

    @Override
    synchronized public void onDraw(Canvas canvas) {
        drawCanvas(canvas);
        drawBird(canvas);
    }

    public void drawCanvas(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setAlpha(255);
        paint.setStrokeWidth(1);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    private void drawBird(Canvas canvas) {
        if (bird.getBirdLocation().x>=0) {
            canvas.drawBitmap(bird.getBirdBitmap(), bird.getBirdLocation().x, bird.getBirdLocation().y, null);
            bird.updateBirdState();
            bird.updateBitmap();
        }
    }

    public void movePlayerTo(Point p) {
        bird.setBirdLocation(p);
    }
}
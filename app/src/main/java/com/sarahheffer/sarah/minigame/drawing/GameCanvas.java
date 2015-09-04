package com.sarahheffer.sarah.minigame.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.sarahheffer.sarah.minigame.Player;

public class GameCanvas extends View {
    private Paint paint;
    private Player player;

    public GameCanvas(Context context, AttributeSet aSet) {
        super(context, aSet);

        paint = new Paint();
        player = new Player(context);
    }

    public void movePlayerTo(int x, int y) {
        player.updatePosition(x,y);
    }

    @Override
    synchronized public void onDraw(Canvas canvas) {
        drawCanvas(canvas);
        drawPlayer(canvas);
    }

    public void drawCanvas(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setAlpha(255);
        paint.setStrokeWidth(1);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    private void drawPlayer(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setAlpha(255);
        paint.setStrokeWidth(20);
        canvas.drawPoint(player.getX(), player.getY(), paint);
    }
}

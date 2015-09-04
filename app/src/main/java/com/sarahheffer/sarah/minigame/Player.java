package com.sarahheffer.sarah.minigame;

import android.content.Context;
import android.view.View;

/**
 * Created by sarah on 9/2/15.
 */
public class Player extends View {

    private android.graphics.PointF mPosition;

    public Player(Context context) {
        super(context);
        this.mPosition = new android.graphics.PointF();
        this.mPosition.x = 500;
        this.mPosition.y = 500;
    }

    public void updatePosition(int newX, int newY) {
        mPosition.x = newX;
        mPosition.y = newY;
    }

    public float getX() {
        return mPosition.x;
    }

    public float getY() {
        return mPosition.y;
    }
}

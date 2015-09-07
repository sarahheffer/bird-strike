package com.sarahheffer.sarah.minigame.drawing;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;

import java.util.Random;

public class DrawingUtils {

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static Matrix getMatrix(int rotation, Bitmap bitmap, Point location) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        matrix.postTranslate(location.x, location.y);

        return matrix;
    }
}

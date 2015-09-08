package com.sarahheffer.sarah.minigame.drawing;

import android.graphics.Color;
import android.graphics.Rect;

import com.sarahheffer.sarah.minigame.models.Bird;
import com.sarahheffer.sarah.minigame.models.Plane;

public class CollisionUtils {

    public static boolean isPlaneCollisionDetected(Bird bird, Plane plane){
        Rect birdBounds = bird.getBounds();
        Rect planeBounds = plane.getBounds();

        if( Rect.intersects(birdBounds, planeBounds) ){
            Rect collisionBounds = getCollisionBounds(birdBounds, planeBounds);
            for (int i = collisionBounds.left; i < collisionBounds.right; i++) {
                for (int j = collisionBounds.top; j < collisionBounds.bottom; j++) {
                    int birdPixel = getBitmapPixel(bird, i, j);
                    int planePixel = getBitmapPixel(plane, i, j);
                    if (isFilled(birdPixel) && isFilled(planePixel)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Rect getCollisionBounds(Rect rect1, Rect rect2) {
        int left = Math.max(rect1.left, rect2.left);
        int top = Math.max(rect1.top, rect2.top);
        int right = Math.min(rect1.right, rect2.right);
        int bottom = Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }

    private static int getBitmapPixel(Bird bird, int i, int j) {
        return bird.getBitmap().getPixel(i-bird.getLocation().x, j-bird.getLocation().y);
    }

    private static int getBitmapPixel(Plane plane, int i, int j) {
        return plane.getBitmap().getPixel(i-plane.getLocation().x, j-plane.getLocation().y);
    }

    private static boolean isFilled(int pixel) {
        return pixel != Color.TRANSPARENT;
    }
}

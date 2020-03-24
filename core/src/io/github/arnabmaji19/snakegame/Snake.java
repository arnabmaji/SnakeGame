package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.graphics.Texture;

public class Snake {

    private static final Texture texture = new Texture("snake.png");
    private static final int MAX_SNAKE_POSITION_CHANGE_COUNT = 8;
    private static final float MAX_VELOCITY = 20.0f;
    private static float velocity = MAX_VELOCITY;
    private float xPos = 10.0f;
    private int snakePositionChangeCount = 0;
    private float yPos = 10.0f;
    private boolean isMovingVertically = false;


    public static Texture getTexture() {
        return texture;
    }

    public void move() {
        if (snakePositionChangeCount > MAX_SNAKE_POSITION_CHANGE_COUNT) {  // change snake position in a delay
            snakePositionChangeCount = 0;
            // move position according to its direction
            if (isMovingVertically) yPos += velocity; // increase snake's x position
            else xPos += velocity;  // increase snake's y position

        } else snakePositionChangeCount++;

    }

    private boolean isMovingForward() {
        return velocity > 0;
    }

    public void toggleDirection(boolean forward, boolean upward) {
        // change snake direction: forward or backward
        if (forward) velocity = MAX_VELOCITY;
        else velocity = (-MAX_VELOCITY);

        // change direction: upward or downward
        isMovingVertically = upward;
    }

    public boolean isTowardsPositiveXAxis() {
        return !isMovingVertically && isMovingForward();
    }

    public boolean isTowardsNegativeXAxis() {
        return !isMovingVertically && !isMovingForward();
    }

    public boolean isTowardsPositiveYAxis() {
        return isMovingVertically && isMovingForward();
    }

    public boolean isTowardsNegativeYAxis() {
        return isMovingVertically && !isMovingForward();
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }
}

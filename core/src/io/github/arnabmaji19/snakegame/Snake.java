package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.graphics.Texture;

public class Snake {

    private static final Texture texture = new Texture("snake.png");
    private static final float MAX_VELOCITY = 3.0f;

    private float velocity = MAX_VELOCITY;
    private float xPos;
    private float yPos;
    private boolean isMovingVertically = false;
    private int screenHeight;
    private int screenWidth;

    public Snake(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.xPos = screenWidth / 2.0f;
        this.yPos = screenHeight / 2.0f;
    }

    public static Texture getTexture() {
        return texture;
    }

    public void move() {
        // move position according to its direction
        if (isMovingVertically) yPos += velocity; // increase snake's x position
        else xPos += velocity;  // increase snake's y position

        if (isOnEnd()) {
            xPos = screenWidth / 2.0f;
            yPos = screenHeight / 2.0f;
        }
    }

    public void toggleDirection(boolean forward, boolean upward) {
        // change snake direction: forward or backward
        if (forward) velocity = MAX_VELOCITY;
        else velocity = (-MAX_VELOCITY);

        // change direction: upward or downward
        isMovingVertically = upward;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public boolean isMovingVertically() {
        return isMovingVertically;
    }

    private boolean isOnEnd() {
        return (xPos + texture.getWidth() >= screenWidth) ||
                (xPos < texture.getWidth()) ||
                (yPos + texture.getHeight() >= screenHeight) ||
                (yPos < texture.getHeight());
    }
}

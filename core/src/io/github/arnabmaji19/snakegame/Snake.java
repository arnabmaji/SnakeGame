package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.graphics.Texture;

public class Snake {

    private static final Texture texture = new Texture("snake.png");
    private static final int MAX_SNAKE_POSITION_CHANGE_COUNT = 8;
    private static final float VELOCITY = 10.0f;

    private float xPos;
    private float yPos;
    private int snakePositionChangeCount = 0;

    public Snake() {
        this.xPos = 10.0f;
        this.yPos = 10.0f;
    }

    public static Texture getTexture() {
        return texture;
    }

    public void move() {
        if (snakePositionChangeCount > MAX_SNAKE_POSITION_CHANGE_COUNT) {  // change snake position in a delay
            snakePositionChangeCount = 0;
            xPos += VELOCITY;  // increase snake's positions
        } else snakePositionChangeCount++;

    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }
}

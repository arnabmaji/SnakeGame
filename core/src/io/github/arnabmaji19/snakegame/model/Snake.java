package io.github.arnabmaji19.snakegame.model;

import com.badlogic.gdx.graphics.Texture;

import java.util.LinkedList;
import java.util.Queue;

public class Snake {

    private static final Texture texture = new Texture("snake.png");
    private static final float MAX_VELOCITY = 3.0f;
    private static final int MAX_SNAKE_TEXTURE_CHANGE_DELAY = 5;

    private LinkedList<Position> snakeBody;
    private float velocity = MAX_VELOCITY;
    private boolean isMovingVertically = false;
    private boolean isMovingForward = true;
    private int screenHeight;
    private int screenWidth;
    private int textureChangeDelay = 0;

    public Snake(int screenHeight, int screenWidth) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.snakeBody = getInitialSnakePositions();
    }

    public static Texture getTexture() {
        return texture;
    }

    public void move() {
        if (textureChangeDelay < MAX_SNAKE_TEXTURE_CHANGE_DELAY) textureChangeDelay++;
        else {
            Position newGrid;
            Position firstGrid = snakeBody.getFirst();  // get coordinates of snake's head

            float newXPos = firstGrid.x;
            float newYPos = firstGrid.y;
            int mul = isMovingForward ? 1 : -1;  // determine moving direction
            if (isMovingVertically) {
                newYPos += (mul * Snake.getTexture().getHeight());
            } else {
                newXPos += (mul * Snake.getTexture().getWidth());
            }
            newGrid = new Position(newXPos, newYPos);
            snakeBody.removeLast();  // remove snake's last part
            snakeBody.addFirst(newGrid);  // add new positions to snake's head
            textureChangeDelay = 0;
        }
    }

    public void toggleDirection(boolean forward, boolean upward) {
        // change snake direction: forward or backward
        isMovingForward = forward;

        // change direction: upward or downward
        isMovingVertically = upward;
    }

    public boolean isMovingVertically() {
        return isMovingVertically;
    }

    public Queue<Position> getSnakeBody() {
        return snakeBody;
    }

    private LinkedList<Position> getInitialSnakePositions() {
        LinkedList<Position> list = new LinkedList<>();
        float yPos = screenHeight / 2.0f;

        for (int i = 0; i < 5; i++) {
            float xPos = (screenWidth / 2.0f) - (i * Snake.getTexture().getWidth());
            list.add(new Position(xPos, yPos));
        }
        return list;
    }

}

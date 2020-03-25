package io.github.arnabmaji19.snakegame.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.LinkedList;

public class Snake {

    private static final Texture texture = new Texture("snake.png");
    private static final int snakeHeight = texture.getHeight();
    private static final int snakeWidth = texture.getWidth();
    private static final int MAX_SNAKE_TEXTURE_CHANGE_DELAY = 5;
    private static final int INITIAL_SNAKE_LENGTH = 3;

    private LinkedList<Position> snakeBody;
    private Position snakeHead;
    private boolean isMovingVertically = false;
    private boolean isMovingForward = true;
    private int screenHeight;
    private int screenWidth;
    private int textureChangeDelay = 0;
    private boolean incrementBody = false;

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

            Position newHead = createNewHead();  // create new head for snake
            if (!incrementBody) {  // if no need to increment body
                snakeBody.removeLast();  // remove snake's last part
            } else incrementBody = false;  // otherwise do not remove snake's tail
            snakeHead = newHead;  // make the new position snake's head
            snakeBody.addFirst(snakeHead);  // add new snake head to its body
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

    public LinkedList<Position> getSnakeBody() {
        return snakeBody;
    }

    public boolean eatsFood(Food food) {  // determines if snake and food intersects
        Rectangle snakeHeadRectangle = new Rectangle(
                snakeHead.x,
                snakeHead.y,
                snakeWidth,
                snakeHeight
        );
        Rectangle foodRectangle = new Rectangle(
                food.getXPos(),
                food.getYPos(),
                Food.getTexture().getWidth(),
                Food.getTexture().getWidth()
        );

        return Intersector.overlaps(snakeHeadRectangle, foodRectangle);
    }

    public void increaseBody() {
        incrementBody = true;
    }

    public boolean hitsEnd() {  // determines if snake hits any of the four ends
        return (snakeHead.x < 0) ||
                (snakeHead.y < 0) ||
                ((snakeHead.x + snakeWidth) > screenWidth) ||
                ((snakeHead.y + snakeHeight) > screenHeight);
    }

    public boolean hitsSelf() {
        boolean hitsSelf = false;
        Rectangle snakeHeadRectangle = new Rectangle(
                snakeHead.x,
                snakeHead.y,
                snakeWidth,
                snakeHeight
        );
        for (Position position : snakeBody) {
            if (position != snakeHead) {  // check if any of its part overlaps with head except the head
                Rectangle rectangle = new Rectangle(
                        position.x,
                        position.y,
                        snakeWidth,
                        snakeHeight
                );
                if (Intersector.overlaps(snakeHeadRectangle, rectangle)) {  // if snake head hits its body
                    hitsSelf = true;
                    break;
                }
            }
        }
        return hitsSelf;
    }

    public void reset() {
        snakeBody = getInitialSnakePositions();
        isMovingVertically = false;
        isMovingForward = true;
    }

    private LinkedList<Position> getInitialSnakePositions() {
        LinkedList<Position> list = new LinkedList<>();
        float yPos = screenHeight / 2.0f;

        for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
            float xPos = (screenWidth / 2.0f) - (i * snakeWidth);
            list.add(new Position(xPos, yPos));
        }
        this.snakeHead = list.getFirst();
        return list;
    }

    private Position createNewHead() {

        float newXPos = snakeHead.x;
        float newYPos = snakeHead.y;
        int mul = isMovingForward ? 1 : -1;  // determine moving direction
        if (isMovingVertically) {
            newYPos += (mul * snakeHeight);
        } else {
            newXPos += (mul * snakeWidth);
        }
        return new Position(newXPos, newYPos);
    }

}

package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Food {

    public static final Texture texture = new Texture("food.png");

    private Random random;
    private float xPos;
    private float yPos;
    private int screenHeight;
    private int screenWidth;

    public Food(int screenHeight, int screenWidth) {
        this.random = new Random();
        this.xPos = 0.0f;
        this.yPos = 0.0f;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public static Texture getTexture() {
        return texture;
    }

    public float getXPos() {
        return xPos;
    }

    public float getYPos() {
        return yPos;
    }

    public void create() {
        int randomXPos = random.nextInt(screenWidth);
        int randomYPos = random.nextInt(screenHeight);

        while ((randomXPos + texture.getWidth() > screenWidth) ||
                (randomYPos + texture.getHeight() > screenHeight)) {
            randomXPos = random.nextInt(screenWidth);
            randomYPos = random.nextInt(screenHeight);
        }
        this.xPos = randomXPos;
        this.yPos = randomYPos;
    }
}

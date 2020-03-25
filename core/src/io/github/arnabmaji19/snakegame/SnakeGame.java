package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.arnabmaji19.snakegame.model.Food;
import io.github.arnabmaji19.snakegame.model.Position;
import io.github.arnabmaji19.snakegame.model.Snake;

public class SnakeGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture backgroundTexture;
	private int screenHeight;
	private int screenWidth;

	private Snake snake;
	private Food food;

	@Override
	public void create() {
		batch = new SpriteBatch();
		backgroundTexture = new Texture("background.png");
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		snake = new Snake(screenHeight, screenWidth);
		food = new Food(screenHeight, screenWidth);
		food.create();
	}

	@Override
	public void render() {
        batch.begin();
        // draw background
        batch.draw(backgroundTexture, 0, 0);

        snake.move();
        // draw snake
        drawSnake();

        // listen for key presses
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !snake.isMovingVertically())
            snake.toggleDirection(true, true);  // make it towards positive y axis

        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !snake.isMovingVertically())
            snake.toggleDirection(false, true);  // make it towards negative y axis

        else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.isMovingVertically())
            snake.toggleDirection(false, false);  // make it towards negative x axis

		else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.isMovingVertically())
			snake.toggleDirection(true, false);  // make it towards positive x axis

		// draw food randomly on screen
		batch.draw(Food.getTexture(), food.getXPos(), food.getYPos());

//		if (snakeAndFoodOverlaps()) food.create();  // create new food

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

//	private boolean snakeAndFoodOverlaps() {
//		Rectangle snakeRectangle = new Rectangle(  // create rectangle for current snake position
//				snake.getXPos(),
//				snake.getYPos(),
//				Snake.getTexture().getWidth(),
//				Snake.getTexture().getHeight()
//		);
//		Rectangle foodRectangle = new Rectangle(  // create rectangle for current food position
//				food.getXPos(),
//				food.getYPos(),
//				Food.getTexture().getWidth(),
//				Food.getTexture().getHeight()
//		);
//
//		return Intersector.overlaps(snakeRectangle, foodRectangle);
//	}

    private void drawSnake() {
        for (Position position : snake.getSnakeBody()) {
            batch.draw(Snake.getTexture(), position.x, position.y);
        }
    }
}

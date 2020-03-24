package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture backgroundTexture;

	private Snake snake;

	@Override
	public void create() {
		batch = new SpriteBatch();
		backgroundTexture = new Texture("background.png");
		snake = new Snake(Gdx.graphics.getHeight(), Gdx.graphics.getWidth());
	}

	@Override
	public void render() {
		batch.begin();
		// draw background
		batch.draw(backgroundTexture, 0, 0);

		snake.move();
		// draw snake
		batch.draw(Snake.getTexture(), snake.getXPos(), snake.getYPos());

		// listen for key presses
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !snake.isMovingVertically())
			snake.toggleDirection(true, true);  // make it towards positive y axis

		else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !snake.isMovingVertically())
			snake.toggleDirection(false, true);  // make it towards negative y axis

		else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.isMovingVertically())
			snake.toggleDirection(false, false);  // make it towards negative x axis

		else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.isMovingVertically())
			snake.toggleDirection(true, false);  // make it towards positive x axis

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}

package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.ApplicationAdapter;
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
		snake = new Snake();
	}

	@Override
	public void render() {
		batch.begin();
		// draw background
		batch.draw(backgroundTexture, 0, 0);

		snake.move();
		// draw snake
		batch.draw(Snake.getTexture(), snake.getXPos(), snake.getYPos());
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}

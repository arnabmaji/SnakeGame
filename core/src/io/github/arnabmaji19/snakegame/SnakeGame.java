package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends ApplicationAdapter {
	private SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		batch.begin();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}

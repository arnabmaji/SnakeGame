package io.github.arnabmaji19.snakegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.arnabmaji19.snakegame.model.Food;
import io.github.arnabmaji19.snakegame.model.Position;
import io.github.arnabmaji19.snakegame.model.ScoreBoard;
import io.github.arnabmaji19.snakegame.model.Snake;

public class SnakeGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture backgroundTexture;
    private BitmapFont scoreBitmapFont;

    private Snake snake;
    private Food food;
    private ScoreBoard scoreBoard;
    private GameState gameState;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("background.png");
        scoreBitmapFont = new BitmapFont();
        scoreBitmapFont.setColor(Color.WHITE);
        scoreBitmapFont.getData().scale(1);

        int screenHeight = Gdx.graphics.getHeight();
        int screenWidth = Gdx.graphics.getWidth();
        snake = new Snake(screenHeight, screenWidth);
        food = new Food(screenHeight, screenWidth);
        food.create();

        scoreBoard = new ScoreBoard();
        gameState = GameState.Running;
    }

	@Override
	public void render() {
        batch.begin();
        // draw background
        batch.draw(backgroundTexture, 0, 0);

        if (gameState.equals(GameState.Running)) snake.move();
        if (gameState.equals(GameState.Game_OVER) && Gdx.input.justTouched()) {
            // create new game
            scoreBoard.reset();  // reset score
            snake.reset();  // reset snake body
            food.create();  // create new food
            gameState = GameState.Running;  // change game state to running
        }

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

        if (snake.eatsFood(food)) {  // if snake eats food
            snake.increaseBody();  // increase its body
            food.create();  // create new food
            scoreBoard.increment();  // increase score
        }

        if (snake.hitsEnd() || snake.hitsSelf()) {  // game over
            gameState = GameState.Game_OVER;  // set game state to game over
        }

        scoreBitmapFont.draw(batch, scoreBoard.get() + "", 10, 30);  // draw score

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void drawSnake() {
        for (Position position : snake.getSnakeBody()) {
            batch.draw(Snake.getTexture(), position.x, position.y);
        }
    }

    public enum GameState {Running, Game_OVER}
}

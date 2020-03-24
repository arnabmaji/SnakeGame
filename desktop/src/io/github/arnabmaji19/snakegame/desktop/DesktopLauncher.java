package io.github.arnabmaji19.snakegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.arnabmaji19.snakegame.SnakeGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 600;
		config.width = 1000;
		config.resizable = false;
		new LwjglApplication(new SnakeGame(), config);
	}
}

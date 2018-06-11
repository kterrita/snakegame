package ru.benitsyn.snakegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.benitsyn.snakegame.business.engine.GameEngine;
import ru.benitsyn.snakegame.business.views.SnakeView;

public class MainMenuActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private SnakeView snakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        gameEngine = new GameEngine();
        gameEngine.initGame();

        snakeView = (SnakeView) findViewById(R.id.snake);
        snakeView.setSnakeViewMap(gameEngine.getMap());
        snakeView.invalidate();

    }
}

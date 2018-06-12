package ru.benitsyn.snakegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import ru.benitsyn.snakegame.business.engine.GameEngine;
import ru.benitsyn.snakegame.business.views.SnakeView;

public class MainMenuActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private GestureDetector simpleOnGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        gameEngine = new GameEngine();
        gameEngine.initGame();

        snakeView = findViewById(R.id.snake);
        snakeView.setSnakeViewMap(gameEngine.getMap());
        snakeView.invalidate();
        simpleOnGestureListener = new GestureDetector(this, new TapUpListener());
    }

    //todo: we have to implement method which restores data after alt+tab

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        simpleOnGestureListener.onTouchEvent(event);
        return true;
    }

    private class TapUpListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent event) {
            return super.onDown(event);
        }
    }
}

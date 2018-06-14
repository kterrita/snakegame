package ru.benitsyn.snakegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ru.benitsyn.snakegame.business.engine.GameEngine;
import ru.benitsyn.snakegame.business.enums.Direction;
import ru.benitsyn.snakegame.business.views.SnakeView;

public class MainMenuActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private GestureDetector simpleOnGestureListener;
    private ScheduledExecutorService scheduledExecutorService;
    private long delayMillis = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = findViewById(R.id.snake);
        snakeView.setSnakeViewMap(gameEngine.getMap());


        simpleOnGestureListener = new GestureDetector(this, new TapDownListener());
        execute();
    }

    private void execute() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                gameEngine.updateSnake();
                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.postInvalidate();

            }
        }, delayMillis, 500, TimeUnit.MILLISECONDS);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        simpleOnGestureListener.onTouchEvent(event);
        return true;
    }

    private class TapDownListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent down, MotionEvent up, float velocityX, float velocityY) {
            float deltaX = Math.abs(up.getX() - down.getX());
            float deltaY = Math.abs(up.getY() - down.getY());

            if (deltaX > deltaY) {
                //east or west
                if (up.getX() > down.getX()) {
                    gameEngine.setCurrentDirection(Direction.EAST);
                } else {
                    gameEngine.setCurrentDirection(Direction.WEST);
                }
            } else {
                //south or north
                if (up.getY() > down.getY()) {
                    gameEngine.setCurrentDirection(Direction.SOUTH);
                } else {
                    gameEngine.setCurrentDirection(Direction.NORTH);
                }
            }
            return true;
        }
    }
}

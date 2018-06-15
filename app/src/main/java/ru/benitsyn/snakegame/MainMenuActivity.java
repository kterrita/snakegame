package ru.benitsyn.snakegame;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import ru.benitsyn.snakegame.business.engine.GameEngine;
import ru.benitsyn.snakegame.business.enums.Direction;
import ru.benitsyn.snakegame.business.enums.GameState;
import ru.benitsyn.snakegame.business.views.SnakeView;

public class MainMenuActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private SnakeView snakeView;
    private GestureDetector simpleOnGestureListener;
    final Handler handler = new Handler();
    private long delayMillis = 500L;

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
        Runnable task = new Runnable() {
            @Override
            public void run() {
                gameEngine.updateSnake();
                if (gameEngine.getGameState().equals(GameState.END)) {
                    Toast.makeText(getApplicationContext(), "The End", Toast.LENGTH_SHORT).show();
                    handler.removeCallbacks(this);
                }
                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.postInvalidate();
                handler.postDelayed(this, delayMillis);
            }
        };
        handler.post(task);
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
                if (up.getX() > down.getX() && !gameEngine.getCurrentDirection().equals(Direction.WEST)) {
                    gameEngine.setCurrentDirection(Direction.EAST);
                } else if (up.getX() < down.getX() && !gameEngine.getCurrentDirection().equals(Direction.EAST)) {
                    gameEngine.setCurrentDirection(Direction.WEST);
                }
            } else {
                //south or north
                if (up.getY() > down.getY() && !gameEngine.getCurrentDirection().equals(Direction.NORTH)) {
                    gameEngine.setCurrentDirection(Direction.SOUTH);
                } else if (up.getY() < down.getY() && !gameEngine.getCurrentDirection().equals(Direction.SOUTH)) {
                    gameEngine.setCurrentDirection(Direction.NORTH);
                }
            }
            return true;
        }
    }

}

package ru.benitsyn.snakegame;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


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

    public void endGameDialog() {
        //dialog
        final Dialog dialog = new Dialog(MainMenuActivity.this);
        dialog.setContentView(R.layout.end_game_gialog);

        //dialog's window
        Window windowDialog = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = windowDialog.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.alpha = 0.8f;
        windowDialog.setAttributes(layoutParams);

        //text view
        TextView textView = dialog.findViewById(R.id.tv_end_game);
        String msg = getString(R.string.the_end);
        textView.setText(msg);


        //button "yes"
        Button button_yes = dialog.findViewById(R.id.button_end_game_yes);
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameEngine.setGameState(GameState.RUNNING);
                gameEngine.setCurrentDirection(Direction.EAST);
                execute();
                dialog.dismiss();
            }
        });

        //button "no"
        Button button_no = dialog.findViewById(R.id.button_end_game_no);
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }


    private void execute() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.updateSnake();
                if (gameEngine.getGameState().equals(GameState.RUNNING)) {
                    handler.postDelayed(this, delayMillis);
                }
                if (gameEngine.getGameState().equals(GameState.END)) {
                    endGameDialog();
                }
                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.postInvalidate();
            }
        }, delayMillis);
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

package ru.benitsyn.snakegame.business.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import ru.benitsyn.snakegame.business.engine.GameEngine;
import ru.benitsyn.snakegame.business.enums.TileType;

public class SnakeView extends View {
    private Paint mPaint;
    private TileType[][] snakeViewMap;

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public void setSnakeViewMap(TileType[][] map) {
        this.snakeViewMap = map;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (snakeViewMap != null) {
            float circleSizeX = canvas.getWidth() / GameEngine.getFieldWidth(); //38.0
            float circleSizeY = canvas.getHeight() / GameEngine.getFieldHeight();//37.0
            float circleRadius = Math.min(circleSizeX, circleSizeY) / 2f; //18.5

            for (int x = 0; x < GameEngine.getFieldWidth(); x++) {
                for (int y = 0; y < GameEngine.getFieldHeight(); y++) {
                    switch (snakeViewMap[x][y]) {
                        case NOTHING:
                            mPaint.setColor(Color.WHITE);
                            break;
                        case WALL:
                            mPaint.setColor(Color.GRAY);
                            break;
                        case SNAKE_HEAD:
                            mPaint.setColor(Color.BLACK);
                            break;
                        case SNAKE_TAIL:
                            mPaint.setColor(Color.GREEN);
                            break;
                        case FRUIT:
                            mPaint.setColor(Color.RED);
                            break;
                    }
                    canvas.drawCircle(x * circleSizeX + circleRadius, y * circleSizeY + circleRadius, circleRadius, mPaint);
                }
            }
        }
    }
}

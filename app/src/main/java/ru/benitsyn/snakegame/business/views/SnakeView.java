package ru.benitsyn.snakegame.business.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import ru.benitsyn.snakegame.business.enums.TileType;

public class SnakeView extends View {
    private Paint mPaint = new Paint();
    private TileType[][] snakeViewMap;

    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSnakeViewMap(TileType[][] map) {
        this.snakeViewMap = map;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (snakeViewMap != null) {
            float circleSizeX = canvas.getWidth() / snakeViewMap.length; //38
            float circleSizeY = canvas.getHeight() / snakeViewMap[0].length;//38
            float circleRadius = Math.min(circleSizeX, circleSizeY) / 2f; //38

            for (int x = 0; x < snakeViewMap.length; x++) {
                for (int y = 0; y < snakeViewMap[x].length; y++) {
                    switch (snakeViewMap[x][y]) {
                        case Nothing:
                            mPaint.setColor(Color.WHITE);
                            break;
                        case Wall:
                            mPaint.setColor(Color.GRAY);
                            break;
                        case SnakeHead:
                            mPaint.setColor(Color.BLACK);
                            break;
                        case SnakeTail:
                            mPaint.setColor(Color.GREEN);
                            break;
                        case Fruit:
                            mPaint.setColor(Color.RED);
                            break;
                    }
                    canvas.drawCircle(x * circleSizeX + circleSizeX, y * circleSizeY + circleSizeY, circleRadius, mPaint);
                }
            }
        }


    }
}

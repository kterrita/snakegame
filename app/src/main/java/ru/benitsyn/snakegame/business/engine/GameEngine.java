package ru.benitsyn.snakegame.business.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.benitsyn.snakegame.business.enums.Direction;
import ru.benitsyn.snakegame.business.position.Coordinates;
import ru.benitsyn.snakegame.business.enums.TileType;

public class GameEngine {
    private static final int FIELD_WIDTH = 30;
    private static final int FIELD_HEIGHT = 44;
    private static int COUNT = 0;
    private Direction lastDirection = Direction.EAST;
    private Direction currentDirection = Direction.EAST;

    private List<Coordinates> walls = new ArrayList<>();

    private List<Coordinates> fruits = new ArrayList<>();
    private List<Coordinates> snake = new ArrayList<>();

    public GameEngine() {
    }

    public void initGame() {
        addWalls();
        addSnake();
        addFruits();
    }

    private void addSnake() {
        Coordinates coordStart = new Coordinates(FIELD_WIDTH / 2, FIELD_HEIGHT / 2);

        snake.add(coordStart);
        snake.add(new Coordinates(coordStart.getX() - 1, coordStart.getY()));
        snake.add(new Coordinates(coordStart.getX() - 2, coordStart.getY()));
    }

    public void moveSnake() {

        for (int i = snake.size() - 1; i >= 0; i--) {
            snake.get(i).setX(snake.get(i).getX() + 1);
            snake.get(i).setY(snake.get(i).getY());
        }
    }

    public void updateSnake() {
        switch (currentDirection) {
            case SOUTH:
                for (int i = 1; i < snake.size(); i++) {
                    Coordinates coordinates = snake.get(i - 1);
                    snake.get(i).setX(coordinates.getX());
                    snake.get(i).setY(coordinates.getY());
                }
                snake.get(0).setY(snake.get(0).getY() + 1);
                break;
            case NORTH:
                snake.get(0).setY(snake.get(0).getY() - 1);
                break;
            case EAST:
                snake.get(0).setX(snake.get(0).getX() + 1);
                break;
            case WEST:
                snake.get(0).setX(snake.get(0).getX() - 1);
                break;
        }
    }


    private void addFruits() {
        Random random = new Random();
        while (COUNT < 2) {
            int coordX = random.nextInt(FIELD_WIDTH - 2) + 1;
            int coordY = random.nextInt(FIELD_HEIGHT - 2) + 1;

            Coordinates coordinateFruit = new Coordinates(coordX, coordY);
            if (!fruits.contains(coordinateFruit) && !snake.contains(coordinateFruit)) {
                fruits.add(coordinateFruit);
                COUNT++;
            }
        }
    }

    private void addWalls() {
        //add top and bottom walls
        for (int x = 0; x < FIELD_WIDTH; x++) {
            walls.add(new Coordinates(x, 0));
            walls.add(new Coordinates(x, FIELD_HEIGHT - 1));
        }
        //add left and right walls
        for (int y = 1; y < FIELD_HEIGHT; y++) {
            walls.add(new Coordinates(0, y));
            walls.add(new Coordinates(FIELD_WIDTH - 1, y));
        }

    }

    public TileType[][] getMap() {
        TileType[][] map = new TileType[FIELD_WIDTH][FIELD_HEIGHT];
        for (int x = 0; x < FIELD_WIDTH; x++) {
            for (int y = 0; y < FIELD_HEIGHT; y++) {
                map[x][y] = TileType.NOTHING;
            }
        }

        for (Coordinates wall : walls) {
            map[wall.getX()][wall.getY()] = TileType.WALL;
        }

        for (Coordinates fruit : fruits) {
            map[fruit.getX()][fruit.getY()] = TileType.FRUIT;
        }

        for (int i = 0; i < snake.size(); i++) {
            if (i == 0) {
                map[snake.get(i).getX()][snake.get(i).getY()] = TileType.SNAKE_HEAD;
            } else {
                map[snake.get(i).getX()][snake.get(i).getY()] = TileType.SNAKE_TAIL;
            }
        }
        return map;
    }

    public static int getFieldWidth() {
        return FIELD_WIDTH;
    }

    public static int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    public List<Coordinates> getSnake() {
        return snake;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }
}

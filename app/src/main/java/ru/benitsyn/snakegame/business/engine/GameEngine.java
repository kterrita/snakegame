package ru.benitsyn.snakegame.business.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.benitsyn.snakegame.business.coordinates.Coordinates;
import ru.benitsyn.snakegame.business.enums.TileType;

public class GameEngine {
    private static final int GAMEWIDTH = 28;
    private static final int GAMEHEIGHT = 42;

    private List<Coordinates> walls = new ArrayList<>();
    private List<Coordinates> fruits = new ArrayList<>();
    private List<Coordinates> snake = new ArrayList<>();
    private static int count = 0;

    public GameEngine() {
    }

    public List<Coordinates> getSnake() {
        return snake;
    }

    public void initGame() {
        addWalls();
        addSnake();
        addFruits();


    }

    private void addSnake() {
        Coordinates coordStart = new Coordinates(GAMEWIDTH / 2, GAMEHEIGHT / 2);

        snake.add(coordStart);
        snake.add(new Coordinates(coordStart.getX() - 1, coordStart.getY()));
        snake.add(new Coordinates(coordStart.getX() - 2, coordStart.getY()));

    }

    private void addFruits() {
        Random random = new Random();
        while (count < 2) {
            int coordX = random.nextInt(GAMEWIDTH - 2) + 1;
            int coordY = random.nextInt(GAMEHEIGHT - 2) + 1;

            Coordinates coordinateFruit = new Coordinates(coordX, coordY);
            if (!fruits.contains(coordinateFruit) && !snake.contains(coordinateFruit)) {
                fruits.add(coordinateFruit);
                count++;
            }
        }
    }

    private void addWalls() {
        //add top and bottom walls
        for (int x = 0; x < GAMEWIDTH; x++) {
            walls.add(new Coordinates(x, 0));
            walls.add(new Coordinates(x, GAMEHEIGHT - 1));
        }
        //add left and right walls
        for (int y = 1; y < GAMEHEIGHT; y++) {
            walls.add(new Coordinates(0, y));
            walls.add(new Coordinates(GAMEWIDTH - 1, y));
        }

    }

    public TileType[][] getMap() {
        TileType[][] map = new TileType[GAMEWIDTH][GAMEHEIGHT];
        for (int x = 0; x < GAMEWIDTH; x++) {
            for (int y = 0; y < GAMEHEIGHT; y++) {
                map[x][y] = TileType.Nothing;
            }
        }

        for (Coordinates wall : walls) {
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }

        for (Coordinates fruit : fruits) {
            map[fruit.getX()][fruit.getY()] = TileType.Fruit;
        }

        for (int i = 0; i < snake.size(); i++) {
            if (i == 0) {
                map[snake.get(i).getX()][snake.get(i).getY()] = TileType.SnakeHead;
            } else {
                map[snake.get(i).getX()][snake.get(i).getY()] = TileType.SnakeTail;
            }
        }
        return map;
    }


}

package ru.benitsyn.snakegame.business.engine;

import java.util.ArrayList;
import java.util.List;

import ru.benitsyn.snakegame.business.coordinates.Coordinates;
import ru.benitsyn.snakegame.business.enums.TileType;

public class GameEngine {
    public static final int GAMEWIDTH = 28;
    public static final int GAMEHEIGHT = 42;

    public List<Coordinates> walls = new ArrayList<>();

    public GameEngine() {
    }

    public void initGame() {
        addWalls();

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
            for (int y = 0; y < GAMEHEIGHT ; y++) {
                map[x][y] = TileType.Nothing;
            }
        }
        for (Coordinates wall : walls) {
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }
        return map;
    }


}

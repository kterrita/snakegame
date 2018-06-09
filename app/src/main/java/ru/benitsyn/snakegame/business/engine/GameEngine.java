package ru.benitsyn.snakegame.business.engine;

import java.util.ArrayList;
import java.util.List;

import ru.benitsyn.snakegame.business.coordinates.Coordinates;

public class GameEngine {
    public static final int GAMEWIDTH = 28;
    public static final int GAMELENGTH = 42;

    public List<Coordinates> walls = new ArrayList<>();

    public GameEngine() {
    }

    public void initGame() {
        addWalls();

    }

    private void addWalls() {
        for (int x = 0; x < GAMEWIDTH; x++) {
            //add top and bottom walls
            walls.add(new Coordinates(x, 0));
            walls.add(new Coordinates(x, GAMELENGTH - 1));

        }
    }


}

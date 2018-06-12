package ru.benitsyn.snakegame.business.position;

/**
 * Class contains info about velocity and move direction
 *
 * @author beleychev
 */
public class Velocity {
    private static final int MOVE_UP = -1;
    private static final int MOVE_DOWN = 1;
    private static final int MOVE_LEFT = -1;
    private static final int MOVE_RIGHT = 1;
    private int direction;
    private int velocityX = 0;
    private int velocityY = 0;
    private int posX;
    private int posY;

    public Velocity() {
        this.direction = 1;
        this.velocityX = 1;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

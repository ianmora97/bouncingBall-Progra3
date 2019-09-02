
package juego.logic;

import juego.presentation.Model;

public abstract class Actor {
    public int x;
    public int y;
    
    public double dx;
    public double dy;

    public Actor(int x, int y, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
    
    public abstract void move(Model m);
}

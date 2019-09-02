package juego;

public class Circulo {

    public int x;
    public int y;
    public int r;

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public Circulo(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

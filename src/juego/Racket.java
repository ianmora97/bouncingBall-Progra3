package juego;

public class Racket extends Actor {

    public int w;
    public int h;

    public Racket(int x, int y, int w, int h, double dx, double dy) {
        super(x, y, dx, dy);
        this.w = w;
        this.h = h;
    }
    @Override
    public void move(Model m) {

        
        
        x += dx;
        y += dy;
        
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

}

package juego;

public class Racket extends Actor {

    public int w;
    public int h;

    public Racket(int x, int y, int w, int h, int dx, int dy) {
        super(x, y, dx, dy);
        this.w = w;
        this.h = h;
    }
    @Override
    public void move(Model m) {

        
        
        x = x + dx;

        y = y + dy;
        
       

    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

}

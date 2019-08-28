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
        int suma,resta;
        suma = m.r.x + m.r.r;
        resta = m.r.y - m.r.r;
        System.out.println("X: "+ x + " Y: " + y + " W: " + w + " H:" + h + " x-R: "+ (resta) + " x+R: " + suma );

    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

}

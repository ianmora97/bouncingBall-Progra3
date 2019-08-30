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
        suma = x + w;
        resta = y+h;
        int radioGrande = m.c.r ;
        System.out.println("R + X: "+ radioGrande + " Y: " + y + " W: " + w + " H:" + h + " x+w: "+ (suma) + " y+h: " + resta );

    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

}

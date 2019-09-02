package juego;

import java.awt.Rectangle;

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
        System.out.println("x: " + x + " y: "+ y);
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
    public Rectangle getBounds() {
	return new Rectangle(x, y, w, w);
    }
}

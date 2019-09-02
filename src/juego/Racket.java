package juego;

import java.awt.Rectangle;
import java.lang.Math;

public class Racket extends Actor {

    public int w;
    public int h;

    public Racket(int x, int y, int w, int h, double dx, double dy) {
        super(x, y, dx, dy);
        this.w = w;
        this.h = h;
    }
    
           
    public boolean colision(Model b) {
        return (Math.sqrt(Math.pow(this.x+this.dx - b.c.x, 2) + Math.pow(this.y+this.dy - b.c.y, 2)) >( b.c.r));
    }
    public boolean colision2(Model b) {
        return Math.sqrt(Math.pow(this.w+x+this.dx - b.c.x, 2) + Math.pow(this.h+y+this.dy - b.c.y, 2)) >( b.c.r);
    }
    public boolean colision3(Model b) {
        return Math.sqrt(Math.pow(this.x+w+this.dx - b.c.x, 2) + Math.pow(this.y+this.dy - b.c.y, 2)) >( b.c.r);
    }
    public boolean colision4(Model b) {
        return Math.sqrt(Math.pow(this.x+this.dx - b.c.x, 2) + Math.pow(this.y+h+this.dy - b.c.y, 2)) >( b.c.r);
    }
    
    @Override
    public void move(Model m) {
        if(colision(m) || colision2(m) || colision3(m) || colision4(m)){
            dx=0;
            dy=0;
        }
        x +=  dx;
        y +=  dy;
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

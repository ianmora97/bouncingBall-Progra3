package juego;
import java.lang.Math;

public class Ball extends Actor {

    public int r;
    
    
    @Override
    public void move(Model b) {
//        if((y < b.a.y) && (dy > 0) && (y + r >= b.a.y) && (b.a.x < x+r )&& x-r < b.a.x + b.a.w){
//            dy = -dy;
//        }
//        if((y > b.a.y) && (dy < 0) && (y + r >= b.a.y) && (b.a.x > x+r )&& x-r > b.a.x + b.a.w){
//            dy = -dy;
//        }
//        if (x + dx + r > b.r.r + b.r.getX() +19|| x + dx + r -70 < b.r.getX()) {
//            dx = dx - (dx * 2);
//            
//        }
//        if (y + dy + r > b.r.r + b.r.getY() +15|| y + dy + r -75 < b.r.getY()) {
//            dy = dy - (dy * 2);
//            
//        }
//        
//        x += dx;
//        y += dy;
        if(this.pitagoras(b.b.x - b.c.x, b.b.y - b.c.y) > Math.abs(b.b.r - b.c.r)){
            dx = -dx;
            dy = -dy;
        }
           x += dx;
           y += dy;
    }

    public Ball(int x, int y, int r, int dx, int dy) {
        super(x, y, dx, dy);
        this.r = r;
    }

    public void setR(int r) {
        this.r = r;
    }
    public double pitagoras(int x, int y){
        return Math.sqrt(x*x + y*y);
    }
    public int getR() {
        return r;
    }

}

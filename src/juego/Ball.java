package juego;
import java.lang.Math;

public class Ball extends Actor {

    public int r;
    double dx1 = x - 300;
    double dy1 = y - 300;
    double distanceFromCenter = Math.sqrt(dx * dx + dy * dy);
    
    
    @Override
    public void move(Model b) {
//        if((y < b.a.y) && (dy > 0) && (y + r >= b.a.y) && (b.a.x < x+r )&& x-r < b.a.x + b.a.w){
//            dy = -dy;
//        }
//        if((y > b.a.y) && (dy < 0) && (y + r >= b.a.y) && (b.a.x > x+r )&& x-r > b.a.x + b.a.w){
//            dy = -dy;
//        }
//        if (x + dx + r > b.r.getW() + b.r.getX() +19|| x + dx + r -70 < b.r.getX()) {
//            dx = dx - (dx * 2);
//            
//        }
//        if (y + dy + r > b.r.getH() + b.r.getY() +15|| y + dy + r -75 < b.r.getY()) {
//            dy = dy - (dy * 2);
//            
//        }
//        
//        x += dx;
//        y += dy;



            if (distanceFromCenter >= 300 - r) {
                double normalMagnitude = distanceFromCenter;
                double normalX = dx / normalMagnitude;
                double normalY = dy / normalMagnitude;
                double tangentX = -normalY;
                double tangentY = normalX;
                double normalSpeed = -(normalX * dx + normalY * dy);
                double tangentSpeed = tangentX * dx + tangentY * dy;
                dx = normalSpeed * normalX + tangentSpeed * tangentX;
                dx = normalSpeed * normalY + tangentSpeed * tangentY;
            }

        
        x += dx;
        y += dy;
    }

    public Ball(int x, int y, int r, double dx, double dy) {
        super(x, y, dx, dy);
        this.r = r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

}

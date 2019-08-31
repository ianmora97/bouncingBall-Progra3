package juego;
import java.lang.Math;
import java.util.Random;

public class Ball extends Actor {

    public int r;
    
    
    @Override
    public void move(Model b) {
        
        
        boolean chocar = Math.sqrt( Math.pow((x + dx) - b.c.x, 2) + Math.pow((y + dy) - b.c.y, 2)) > (b.c.r - r);
        
        this.x += this.dx;
        this.y += this.dy;
        
        
        if(chocar){
            double dxa = this.x - b.c.x;
            double dya = this.y - b.c.y;

            double distanceFromCenter = Math.sqrt(dxa * dxa + dya * dya);
            
            double normalMagnitude = distanceFromCenter;
            double normalX = dxa / normalMagnitude;
            double normalY = dya / normalMagnitude;
            double tangentX = -normalY;
            double tangentY = normalX;
            double normalSpeed = -(normalX * this.dx + normalY * this.dy);
            double tangentSpeed = tangentX * this.dx + tangentY * this.dy;
            this.dx = normalSpeed * normalX + tangentSpeed * tangentX;
            this.dy = normalSpeed * normalY + tangentSpeed * tangentY;
              
        }
        if (((y + dy + this.getR() > b.a.getY()) || y + dy + this.getR() > b.a.getY() + b.a.getH()) 
                && x + dx > b.a.getX() && x + dx < b.a.getX() + b.a.getW()) {
            dy = dy - (dy * 2);
        }
        
        
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

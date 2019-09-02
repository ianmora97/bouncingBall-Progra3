package juego;
import java.awt.Rectangle;
import java.lang.Math;
import java.util.Random;

public class Ball extends Actor {

    public int r;
    public boolean chocharRed;
    public boolean chocharGreen = false;
    public int vel = 10;
    
    public void cambiaDireccion(Model b){
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
        this.dx = normalSpeed * normalX + tangentSpeed *  tangentX ;
        this.dy = normalSpeed * normalY + tangentSpeed *  tangentY ;
        
        if(distanceFromCenter >= 300){
            this.x = 300;
            this.y = 300;
        }
        
    }
    @Override
    public void move(Model b) {
        
        
        boolean chocar = Math.sqrt( Math.pow((x + dx) - b.c.x, 2) + Math.pow((y + dy) - b.c.y, 2)) >= (b.c.r - r);
        
        this.x += this.dx;
        this.y += this.dy;
        boolean collision = b.a.getBounds().intersects(getBounds());
        
        if(chocar){
            winning(b);
            losing(b);
            cambiaDireccion(b);
        }        
        else if (collision && y < b.a.y && x - r < b.a.x) { 
            dy = -vel;
            dx = -vel;
        } else if (collision && y < b.a.y && x- r > b.a.x) { 
            dy = -vel;
            dx = vel;
        } else if (collision && y > b.a.y && x- r < b.a.x) { 
            dy = vel;
            dx = -vel;
        } else if (collision && y > b.a.y && x- r > b.a.x) { 
            dy = vel;
            dx = vel;
        }
        
    }
    public void winning(Model b){
        
        if((x  < 420 && x > 250 && y-r >= 60 ) || (y-r <= 620  && x>240 && x<430)){
            b.s.sc += 1;
            chocharGreen = true;
        }
        if((x-r-dx <= 95 && y >= 250 && y<= 465)||(x-r-dx >=550 && y >= 250 && y <= 465)){
            b.s.sc += 1;
            chocharGreen = true;
        }
            
    }
    public void losing(Model b){
        if((x  < 155 && x > 50 && y-r >= 110 && y-r <=210 ) || (x  < 560 && x > 480 && y-r >= 110 && y-r <= 200 )){
            b.s.sc -= 1;
            chocharRed = true;
        }
        if((x  < 570 && x > 500 && y-r >= 480 && y-r <= 580 ) || (x  < 130 && x > 60 && y-r >= 480 && y-r <= 580 )){
            b.s.sc -= 1;
            chocharRed = true;
        }

    }
    public Rectangle getBounds() {
	return new Rectangle(x, y, r*2, r*2);
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
    public boolean gana(){
        if((x  < 420 && x > 250 && y-r >= 60 ) || (y-r <= 620  && x>240 && x<430)){
            return true;
        }
        else if((x-r-dx <= 95 && y >= 250 && y<= 465)||(x-r-dx >=550 && y >= 250 && y <= 465)){
            return true;
        }
        return false;
    }
}

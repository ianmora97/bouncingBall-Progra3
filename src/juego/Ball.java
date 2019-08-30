package juego;
import java.lang.Math;
import java.util.Random;

public class Ball extends Actor {

    public int r;
    
    
    @Override
    public void move(Model b) {
        Random r = new Random();
        int valorDado;
        
        double dxa = this.x - b.c.r;
        double dya = this.y - b.c.r;
        double distanceFromCenter = Math.sqrt(dxa * dxa + dya * dya);
        
        
        if(distanceFromCenter >= b.c.r - this.r){
            valorDado = r.nextInt(5) + 1;
            System.out.println("Rand: " + valorDado+ " Dx: " + dx + " Dy: " + dy);
//            this.dx = this.dx - (this.dx * 2);
//            this.dy = this.dy - (this.dy * 2);
            switch(valorDado){
                case 1: dx = -dx;  dy = -dy ; break;
                case 2: 
                    if(dy != 0){dx = 0; dy = dy; }
                    else{break;}
                    break;
                case 3:
                    if(dx != 0){dx = dx; dy = 0;}
                    else{break;}
                    break;
                case 4:
                    if(dy == 0){dx = dx; dy = 2;}
                    else{break;}
                    break;
                case 5:
                    if(dx == 0){dx = 2; dy = dy;}
                    else{break;}
                    break;
                    
            }
        }
        if((y+dy+this.getR()>b.a.getH()+b.a.getY())||y+dy-this.getR()<b.a.getY()){
            dy= dy-(dy * 2);
        }
        
        this.x += this.dx;
        this.y += this.dy;
    }

    public Ball(int x, int y, int r, int dx, int dy) {
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

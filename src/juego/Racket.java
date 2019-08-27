package juego;

public class Racket extends Actor {

    public int w;
    public int h;

    public Racket(int x, int y, int w, int h, int dx, int dy) {
        super(x, y, dx, dy);
        this.w = w;
        this.h = h;
    }

    public void move(Model m) {
        if((x+dx+w>m.r.getW()+m.r.getX())){
            dx= 0;
        }
        else if(x+dx<m.r.getX()){
            dx= 0;
        }
        x = x + dx;

        if(y+dy+h>m.r.getH()+m.r.getX()){
            dy =0;
        }
        else if(y+dy<m.r.getY()){
            dy=0;
        }
        y = y + dy;
        
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
    
    

}

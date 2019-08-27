
package juego;

import java.util.Observable;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

public class Model extends Observable {
    
    public Rectangle r;
    public Ball b;
    public Racket a;
    
    public static final int ARR = 1;
    public static final int ABA = 2;
    public static final int IZQ = 3;
    public static final int DER = 4;
    
    public Model(){
        r = new Rectangle(50,50,600,600);
        b = new Ball(200,150,40,5,5);
        a = new Racket(150,430,75,20,0,0);
    }
    public void start(){
        final int delay = 90;
        Runnable code = new Runnable() {
            @Override
            public void run() {
                while(true){
                    step();
                    setChanged();
                    notifyObservers();
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        //Logger.getLogger(Model.class.getName());
                    }
                }
            }
        };
        Thread thread = new Thread(code);
        thread.start();
    }
    public void move(int flecha){
        switch(flecha){
            case ARR: a.dy= -10; break;
            case ABA: a.dy= 10; break;
            case IZQ: a.dx= -10; break;
            case DER: a.dx= 10; break;
        }
    }
    public void stopVer(){
        a.dy = 0;
    }
    public void stopHor(){
        a.dx = 0;
    }
    public void step(){
        b.move(this);
        a.move(this);
        this.setChanged();
        this.notifyObservers();
    }
    
    @Override
    public void addObserver(java.util.Observer o){
        super.addObserver(o);
        this.setChanged();
        this.notifyObservers();
    }

    public Ball getB() {
        return b;
    }

    public Rectangle getR() {
        return r;
    }

    public Racket getA() {
        return a;
    }
    
    
}

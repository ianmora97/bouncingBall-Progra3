
package juego;

import java.util.ArrayList;
import java.util.Observable;


public class Model extends Observable {
    
    public Circulo c;
    public Ball b;
    public Racket a;
    public ArrayList<Ball> listabolas;
    
    public static final int ARR = 1;
    public static final int ABA = 2;
    public static final int IZQ = 3;
    public static final int DER = 4;
    
    public Model(){
        c = new Circulo(350,350,300);
        b = new Ball(200,200,40,10,-10);
        a = new Racket(300,300,100,30,0,0);
        
    }
    public void reset(int esferas, int velocidad){
        listabolas = new ArrayList<>();
        for(int i=0; i<esferas; i++){
            listabolas.add(new Ball(200,200, 40, velocidad, velocidad));
        }
        
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

    public Circulo getC() {
        return c;
    }

    public Racket getA() {
        return a;
    }
}

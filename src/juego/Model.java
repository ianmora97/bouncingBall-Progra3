
package juego;

import java.util.ArrayList;
import java.util.Observable;


public class Model extends Observable {
    
    public Circulo c;
    public Ball b;
    public Racket a;
    public ArrayList<Ball> listabolas;
    public ArrayList<Arcos> listaArcos;
    public score s;
    
    public static final int ARR = 1;
    public static final int ABA = 2;
    public static final int IZQ = 3;
    public static final int DER = 4;
    
    public Model(){
        c = new Circulo(350,350,310);
        b = new Ball(350,350,40,2,2);
        a = new Racket(300,300,100,30,0,0);
        s = new score(0);
        setArcos();
    }
    public void reset(int esferas, int velocidad){
        listabolas = new ArrayList<>();
        for(int i=0; i<esferas; i++){
            listabolas.add(new Ball(200,200, 40, velocidad, velocidad));
        }
        
    }
    private void setArcos(){
        listaArcos = new ArrayList<>();
        listaArcos.add(new Arcos(245,55,200,30,0,180));//n
        listaArcos.add(new Arcos(245,620,200,30,180,180));//s
        listaArcos.add(new Arcos(612,250,30,200,270,180));//e
        listaArcos.add(new Arcos(49,250,30,200,90,180));//o
        //red
        listaArcos.add(new Arcos(70, 90, 350,350,110,50));//NO
        listaArcos.add(new Arcos(300,90,300,300,20,50));//NE
        listaArcos.add(new Arcos(80,300,300,300,190,60));//SO
        listaArcos.add(new Arcos(300,300,300,300,300,50));//SE

    }
    public void start(){
        final int delay = 10;
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
            case ARR: a.dy= -1; break;
            case ABA: a.dy= 1; break;
            case IZQ: a.dx= -1; break;
            case DER: a.dx= 1; break;
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

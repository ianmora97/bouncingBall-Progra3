
package juego.presentation;

import juego.logic.Racket;
import juego.logic.Ball;
import juego.logic.Circulo;
import juego.logic.Arcos;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import juego.logic.score;


public class Model extends Observable {
    
    public Circulo c;
    public Ball b;
    public Racket a;
    public ArrayList<Ball> listabolas;
    public ArrayList<Arcos> listaArcos;
    public score s;
    

    public boolean congelar;
    int delay = 20;
    int cantidadBolas = 1;
    public static final int ARR = 1;
    public static final int ABA = 2;
    public static final int IZQ = 3;
    public static final int DER = 4;
    
    public Model(){
        congelar = true;
        c = new Circulo(340,340,300);
        b = new Ball(120,340,25,10.0,10.0);
        a = new Racket(300,300,100,30,0.0,0.0);
        s = new score(0);
        listaArcos = new ArrayList<>();
        listabolas = new ArrayList<>();
        listabolas.add(b);
        setArcos();
    }
    
    public void bolas(int esferas, final int velocidad){
        if(esferas < cantidadBolas){
            for(int i=0; esferas == cantidadBolas; i++){
                listabolas.remove(1);
            }
        }
        else{
            for(int i=0; i < esferas; i++){
                Random r = new Random();
                int valor = r.nextInt(300)+100;  
                listabolas.add(new Ball(valor,150,25,9.5,9.5));
            }
        }
        cantidadBolas = esferas;
        delay = velocidad;
        System.out.println(cantidadBolas);
    }
    private void setArcos(){
        //green
        listaArcos.add(new Arcos(245-22,55+12,200,30,0,180));//n
        listaArcos.add(new Arcos(245-22,620+12,200,30,180,180));//s
        listaArcos.add(new Arcos(612-22,250+12,30,200,270,180));//e
        listaArcos.add(new Arcos(49-22,250+12,30,200,90,180));//o
        //red
        listaArcos.add(new Arcos(70-22, 90+12, 350,350,110,50));//NO
        listaArcos.add(new Arcos(300-22,90+12,300,300,20,50));//NE
        listaArcos.add(new Arcos(80-22,300+12,300,300,190,60));//SO
        listaArcos.add(new Arcos(300-22,300+12,300,300,300,50));//SE

    }
    public void start(){
        
        Runnable code = new Runnable() {
            @Override
            public void run() {
                while(true){
                    while(congelar){
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
            }
        };
        Thread thread = new Thread(code);
        thread.start();
    }
    public void pause(){
        congelar = false;
    }
    public void avanzar(){
        congelar = true;
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
        for(int i=0;i<cantidadBolas;i++){
            listabolas.get(i).move(this);
        }
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

    public Racket getRacket() {
        return a;
    }
}

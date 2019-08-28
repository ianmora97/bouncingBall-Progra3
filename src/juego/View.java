
package juego;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.imageio.ImageIO;

public class View extends JFrame implements java.util.Observer{
    Model model;
    Controller controller;
    
    public static final String BALL_PATH = "media/ball.png";
    public static final String FONDO_PATH = "media/fondo.png";
    public static final String RAQUETA_PATH = "media/raqueta.png";
            
    Image ballImage;
    Image fondo;
    Image raqueta;
    
    public View(){
        
        this.setSize(630,650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new java.awt.event.KeyAdapter(){
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt){
                formKeyPressed(evt);
            }
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt){
                formKeyReleased(evt);
            }
        });
        try {
            ballImage = ImageIO.read(getClass().getResourceAsStream(BALL_PATH));
            fondo = ImageIO.read(getClass().getResourceAsStream(FONDO_PATH));
            raqueta =ImageIO.read(getClass().getResourceAsStream(RAQUETA_PATH));
        } catch (Exception ex) {}
    }
    private void formKeyPressed(java.awt.event.KeyEvent evt){
        switch(evt.getKeyCode()){
                case KeyEvent.VK_UP : controller.move(Model.ARR); break;
                case KeyEvent.VK_DOWN : controller.move(Model.ABA); break;
                case KeyEvent.VK_LEFT : controller.move(Model.IZQ); break;
                case KeyEvent.VK_RIGHT : controller.move(Model.DER); break;
        }
    }
    private void formKeyReleased(java.awt.event.KeyEvent evt){
        int key = evt.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT: controller.stopHor(); break;
            case KeyEvent.VK_UP: case KeyEvent.VK_DOWN: controller.stopVer(); break;
        }
    }
    @Override
    public void update(Observable o, Object arg){
        this.repaint();
    }
    @Override
    public void paint(Graphics g){
//        super.paint(g);
        this.renderModel(model, g);
        this.update(model,g);
    }
    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public Model getModel() {
        return model;
    }
    public Controller getController() {
        return controller;
    }
    void renderModel(Model m, Graphics media){
        renderBall(m.b,media);
        renderCircle(m.r,media);
        renderRacket(m.a,media);
        this.update(model,media);
    }
   void  renderBall(Ball b, Graphics media){
        media.drawImage(ballImage, (int)(b.x-b.r), (int)(b.y-b.r),2* b.r ,2* b.r ,this);
        this.update(model,media);
    }
    void renderRacket(Racket r, Graphics media){
        media.drawImage(raqueta, (int)(r.x), (int)(r.y), r.w ,r.h ,this);
        this.update(model,media);
    }
    void renderCircle(Circulo r, Graphics media){
//        media.drawImage(fondo, (int)(r.x), (int)(r.y),600 ,600 ,this);
        media.setColor(Color.white);
        media.fillOval(r.x, r.y , 600, 600);
        this.update(model,media);
    }
}

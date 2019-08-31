package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class View extends JFrame implements Observer{

    Model model;
    Controller controller;
    panel pane;
    
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    

    private void initComponents(){
       
        this.setSize(900,900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(null);
        this.setResizable(false);
        this.add(pane);
    }
    
    public View() {
        pane = new panel(model);
        
        this.initComponents();
        
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
}

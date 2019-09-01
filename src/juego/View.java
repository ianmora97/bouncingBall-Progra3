
package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

public class View extends JFrame implements Observer{

    Model model;
    Controller controller;
    JuegoPanel panel;
    
    BufferedImage bf;
    
    public View() {
        panel = new JuegoPanel(model);
               
        this.setContentPane(panel);
        bf = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        
        this.setSize(700,700);
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
    public void paint(Graphics graphics){
        Graphics g = bf.getGraphics();
        super.paint(g);
        panel.renderModel(model, g);
        graphics.drawImage(bf, 0, 0, null);
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

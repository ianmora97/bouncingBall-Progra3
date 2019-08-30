/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class View extends javax.swing.JFrame implements Observer{

    Model model;
    Controller controller;
    
    public static final String BALL_PATH = "media/ball.png";
    public static final String FONDO_PATH = "media/fondo.png";
    public static final String RAQUETA_PATH = "media/raqueta.png";
            
    Image ballImage;
    Image fondo;
    Image raqueta;
   
    
    public View() {
        initComponents();
        
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
        
        super.paint(g);
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
        renderCircle(m.c,media);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) throws Exception{
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Model model = new Model();
                View view = new View();
                Controller controller = new Controller(model, view);
                view.setVisible(true);
                model.start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

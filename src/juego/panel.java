
package juego;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class panel extends JPanel {
    Model model;
    public static final String BALL_PATH = "media/ball.png";
    public static final String FONDO_PATH = "media/fondo.png";
    public static final String RAQUETA_PATH = "media/raqueta.png";
            
    BufferedImage fondoCirculo, raquet, ball;
    
    public panel(Model m){
        this.model = m;
        
        try {
            fondoCirculo = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
            raquet = new BufferedImage(model.getA().getW(),model.getA().getH(), BufferedImage.TYPE_INT_RGB);
            ball = new BufferedImage(model.getB().getR(),model.getB().getR(), BufferedImage.TYPE_INT_RGB);

        } catch (Exception e) {
        }
           
        
       
        try {
            ball = ImageIO.read(getClass().getResourceAsStream(BALL_PATH));
            fondoCirculo = ImageIO.read(getClass().getResourceAsStream(FONDO_PATH));
            raquet =ImageIO.read(getClass().getResourceAsStream(RAQUETA_PATH));
        } catch (IOException ex) {
           Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    
    private void renderBall(Graphics media){
        media.drawImage(ball, model.b.x-model.b.r, (model.b.y-model.b.r),2* model.b.r ,2* model.b.r ,null);
        
    }
    private void renderRacket(Graphics media){
        media.drawImage(raquet, (model.a.x), (model.a.y), model.a.w ,model.a.h ,null);
        
    }
    private void renderCircle(Graphics media){
        media.drawImage(fondoCirculo, 30, 40, model.c.r *2 + 20 , model.c.r *2 + 20 ,null);
        
    }
    @Override
    public void paintComponent(Graphics g){
       	super.paintComponent(g);
      	renderCircle(g);
    	renderBall(g);
      	renderRacket(g);       
    }

    public Model getModel() {
        return model;
    }
    
}

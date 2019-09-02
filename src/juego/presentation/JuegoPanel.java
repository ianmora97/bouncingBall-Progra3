
package juego.presentation;

import juego.media;

import juego.logic.Racket;
import juego.logic.Ball;
import juego.logic.Circulo;
import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javax.swing.JPanel;
import juego.logic.score;


public class JuegoPanel extends JPanel{
    
    Model model;
    
    
    public static final String UH_PATH = "media/Uh.wav";
    public static final String LOSE_PATH = "media/lose.wav";
    Clip win, lose;
    public static final String BALL_PATH = "media/ball.png"; 
    public static final String BALL2_PATH = "media/ball3.gif";
    
    public static final String FONDO_PATH = "media/fondo.png";
    public static final String RAQUETA_PATH = "media/nave.png";
    public static final String STARS_PATH = "media/stars.gif";
   
            
    BufferedImage fondoCirculo, raquet, ball, stars;
    
    Toolkit tool;
    Image ballGif;

    
    
    
    
    public JuegoPanel(Model m){
        this.model = m;
        
         
        try {
            win = this.loadSound(UH_PATH);
            lose = this.loadSound(LOSE_PATH);
        
        } catch (Exception e) {
        }
        tool = Toolkit.getDefaultToolkit();
        try {
            fondoCirculo = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
            raquet = new BufferedImage(model.getRacket().getW(),model.getRacket().getH(), BufferedImage.TYPE_INT_RGB);
            ball = new BufferedImage(model.getB().getR(),model.getB().getR(), BufferedImage.TYPE_INT_RGB);
            stars = new BufferedImage(model.getB().getR(),model.getB().getR(), BufferedImage.TYPE_INT_RGB);
            
        } catch (Exception e) {
        }
       
        try {
            stars = ImageIO.read(getClass().getResourceAsStream(STARS_PATH));
            ball = ImageIO.read(getClass().getResourceAsStream(BALL2_PATH));
            fondoCirculo = ImageIO.read(getClass().getResourceAsStream(FONDO_PATH));
            raquet = ImageIO.read(getClass().getResourceAsStream(RAQUETA_PATH));
            
            ballGif = tool.getImage(this.getClass().getResource(BALL2_PATH));
        } catch (Exception ex) {}
        
        
        
    }
    Clip loadSound(String path){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    getClass().getResourceAsStream(path));
            AudioFormat soundFormat = audio.getFormat();
            int soundSize = (int)(soundFormat.getFrameSize() * audio.getFrameLength());
            byte[] soundData = new byte[soundSize];
            DataLine.Info soundInfo = new DataLine.Info(Clip.class, soundFormat,soundSize);
            audio.read(soundData,0,soundSize);
            Clip clip = (Clip)(AudioSystem.getLine(soundInfo));
            clip.open(soundFormat,soundData,0,soundSize);
            
           return clip;
        } catch(Exception e){return null;}
        
    }
    
    void renderModel(Model m, Graphics media){
        renderCircle(m.c,media);
       // renderStars(media);
        renderRacket(m.a,media);
        for(int i=0; i < m.cantidadBolas ; i++){
             renderBall(m.listabolas.get(i),media);
        }
        renderScore(m.s, media);
    }
    
    public void renderBall(Ball b, Graphics media){
        media.drawImage(ball, (int)(b.x-b.r), (int)(b.y-b.r)+46,2* b.r ,2* b.r ,this);
        
        if(b.chocharGreen){
            win.start();
            win.setFramePosition(0);
            b.chocharGreen = false;
        }
        if(b.chocharRed){
            lose.start();
            lose.setFramePosition(0);
            b.chocharRed = false;
        }
    }
    public void renderRacket(Racket r, Graphics media){
        media.drawImage(raquet, (int)(r.x), (int)(r.y)+46, r.w ,r.h ,this);
    }
    public void renderCircle(Circulo r, Graphics media){
        media.drawImage(fondoCirculo, 8, 80, r.r *2 +50, r.r *2 +50,this);
    }
    public void renderScore(score s,Graphics media){
        String h = "Score: "+s.sc;
        media.setColor(Color.cyan);
        media.setFont(new Font("Consolas", 1, 30));
        media.drawString(h, 470, 100);
    }
    public void renderStars(Graphics mediaGraphics){
        mediaGraphics.drawImage(ballGif, 10, 10, 800,800,this);
    }
    
   
}

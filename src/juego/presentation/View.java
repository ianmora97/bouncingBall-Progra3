
package juego.presentation;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class View extends JFrame implements Observer{

    Model model;
    Controller controller;
    JuegoPanel panel;
    JMenuBar menu;
    JMenu file,edit,about;
    JMenuItem item,exit, settings;
    
    
    
    BufferedImage bf;
    
    public View() {
        
        
        panel = new JuegoPanel(model);
        menu = new JMenuBar();
        
        item = new JMenuItem("Info");
        exit = new JMenuItem("Salir");
        settings = new JMenuItem("Settings");

        file = new JMenu("File");
        edit = new JMenu("Edit");
        about = new JMenu("About");
        
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(panel, 
                 "Dodge Ball 1.0 - Programacion III - Escuela de Informatica - UNA - IAN MORA RODRIGUEZ");
                
            }
        });
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        settings.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editMenu(e);
            }
        });
        edit.add(settings);
        file.add(exit);
        about.add(item);
        
        menu.add(file);
        menu.add(edit);
        menu.add(about);
        
        
        
        this.setContentPane(panel);
        this.setJMenuBar(menu);
        
        bf = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        
        this.setSize(680,750);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        panel.setBackground(Color.BLACK);

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
    private void showEdit(){
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenu(evt);
            }
        });
    }
    
    private void editMenu(ActionEvent evt){

        
        JFrame opciones = new JFrame();
        opciones.getContentPane().setLayout(null);
        String cantE = ""+model.cantidadBolas, cantVel = ""+model.delay;
        
        JLabel esfe = new JLabel("Esferas: ");
        JLabel vel = new JLabel("Velocidad: ");
        
        JTextField esferas = new JTextField(cantE);
        JTextField abc = new JTextField(cantVel);
        
         
        JButton save = new JButton("Salvar");
        
        esfe.setBounds(20, 15, 50, 10);
        esferas.setBounds(85, 10, 150, 25);
        vel.setBounds(20, 45, 70, 10);
        abc.setBounds(85, 40, 150, 25);
        
        save.setBounds(100, 70, 80, 20);
        
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.bolas(Integer.parseInt(esferas.getText()), Integer.parseInt(abc.getText()));
                opciones.setVisible(false);
            }
        });
        
        
        opciones.add(esfe);
        opciones.add(vel);
        opciones.add(esferas);
        opciones.add(abc);
        opciones.add(save);
        
        
        opciones.setSize(300,150);
        opciones.setLocationRelativeTo(null);
        opciones.setResizable(false);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
       
        opciones.setVisible(true);

    }
    
}

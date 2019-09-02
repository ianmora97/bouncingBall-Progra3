/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import juego.presentation.Controller;
import juego.presentation.Model;
import juego.presentation.View;

/**
 *
 * @author Ian Rodriguez
 */
public class juego {

    
    public static void main(String args[]) throws Exception{
        
      
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
    
}


package juego;

import java.io.IOException;

public class Juego {


    public static void main(String[] args) throws IOException /*throws InterruptedException*/{
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setVisible(true);
        model.start();
    }
    

}

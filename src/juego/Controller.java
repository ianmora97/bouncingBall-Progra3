
package juego;

public class Controller {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setController(this);
        view.setModel(model);
    }
    public void move(int flecha){
        model.move(flecha);
    }
    public void stopHor(){
        model.stopHor();
    }
    public void stopVer(){
        model.stopVer();
    }
    public void pause(){
        model.pause();
    }
    public void avanzar(){
        model.avanzar();
    }
}

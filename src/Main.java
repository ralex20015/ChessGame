import controller.ControllerTablero;
import models.Logica;
import views.Tablero;

public class Main {

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        Logica logica = new Logica();
        tablero.setVisible(true);
        ControllerTablero controllerTablero = new ControllerTablero(tablero,logica);
    }
}

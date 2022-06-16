import controller.BoardController;
import models.Logica;
import views.Tablero;

public class Main {

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.setVisible(true);
        Logica logica = new Logica();
        BoardController boardController = new BoardController(tablero,logica);
    }
}

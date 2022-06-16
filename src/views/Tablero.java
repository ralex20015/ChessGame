package views;

import javax.swing.*;
import java.awt.*;

public class Tablero extends JFrame {

    private final Square [][] casillas;
    private final int ROWS = 8;
    private final int COLUMNS = 8;
    private final JPanel tablero;

    public Tablero() throws HeadlessException {
        tablero = new JPanel();
        tablero.setLayout(new GridLayout(ROWS,COLUMNS));
        initFrame();
        casillas = new Square[ROWS][COLUMNS];
        initilizeCasillas();
    }

    private void initFrame() {
        this.setSize(600,600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(tablero,BorderLayout.CENTER);
    }

    private void initilizeCasillas(){
        Point p;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                p = new Point(i,j);
                casillas[i][j] = new Square(p);
                tablero.add(casillas[i][j]);
            }
        }
    }

    public Square[][] getCasillas() {
        return casillas;
    }

}

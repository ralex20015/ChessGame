package views;

import Pieces.Piece;
import controller.BoardController;
import models.Logica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tablero extends JFrame {

    private final Square [][] casillas;
    private final String route = "src/Images/";
    private final int ROWS = 8;
    private final int COLUMNS = 8;
    private final JPanel tablero;
    private BoardController boardController;

    public Tablero() throws HeadlessException {
        tablero = new JPanel();
        tablero.setLayout(new GridLayout(ROWS,COLUMNS));
        tablero.setLocation(65,50);
        tablero.setSize(475,475);
        initFrame();
        casillas = new Square[ROWS][COLUMNS];
        initilizeCasillas();
    }

    private void initFrame() {
        setSize(600,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Chess Game");
        add(tablero,BorderLayout.CENTER);
    }

    private void initilizeCasillas(){
        Point p;
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j <= 7; j++) {
                p = new Point(i+1,j+1);
                casillas[i][j] = new Square(p, initializePiecesImages(p));
                tablero.add(casillas[i][j]);
                if (i == 1 && j == 1){
                    casillas[i][j].setIsAPiece(true);
                }
            }
        }
    }

    private ImageIcon initializePiecesImages(Point initPosition){
        return new ImageIcon(pieceToPaint(initPosition));
    }

    private String pieceToPaint(Point p){
        //white pieces
        if (p.x == 2){
            return route+"WHITE/pawn.png";
        }

        if (p.x == 1 && p.y == 4 ){
            return route+"WHITE/queen.png";
        }
        if (p.x == 1 && p.y == 5){
            return route+"WHITE/king.png";
        }

        if (p.x == 1 && p.y == 6 || p.x == 1 && p.y ==3){
            return route+"WHITE/bishop.png";
        }
        if (p.x == 1 && p.y == 7 || p.x == 1 && p.y ==2){
            return route+"WHITE/knight.png";
        }
        if (p.x == 1 && p.y == 8 || p.x == 1 && p.y ==1){
            return route+"WHITE/tower.png";
        }

        //Black pieces
        if (p.x == 7){
            return route+"BLACK/pawn.png";
        }
        if (p.x == 8 && p.y == 1 || p.x == 8 && p.y == 8){
            return route+"BLACK/tower.png";
        }
        if (p.x == 8 && p.y == 2 || p.x == 8 && p.y ==7){
            return route+"BLACK/knight.png";
        }
        if (p.x == 8 && p.y == 3 || p.x == 8 && p.y ==6){
            return route+"BLACK/bishop.png";
        }
        if (p.x == 8 && p.y == 4 ){
            return route+"BLACK/king.png";
        }
        if (p.x == 8 && p.y == 5){
            return route+"BLACK/queen.png";
        }
        return "";
    }

    public void setIsAPiece(){

    }

    public Square[][] getCasillas() {
        return casillas;
    }
    public JPanel getTablero() {
        return tablero;
    }

    /*public String typePiece(Piece piece){

    }*/

}

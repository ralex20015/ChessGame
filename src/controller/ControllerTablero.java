package controller;

import models.Logica;
import views.Square;
import views.Tablero;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControllerTablero {

    private final Square[][] casillas;
    private final Logica logica;

    public ControllerTablero(Tablero tablero, Logica logica){
        this.logica = logica;
        casillas = tablero.getCasillas();
        addMouseListeners();
    }

    private void addMouseListeners() {
        Point p;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                p = new Point(i,j);
                addEventToCasilla(p);
            }

        }
    }

    private void addEventToCasilla(Point p) {
        Square actual = casillas[p.x][p.y];
        actual.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                logica.clickSomething(p);
                String action = null;
                if (logica.isSomethingClicked()){
                    if (logica.getCurrentPiece() == p){
                        action = "Deseleccionar";
                    }
                }

                if (logica.getPieceClicked()!= null && !(logica.isSomethingClicked())){
                     action = "Seleccionar";
                }
                if (action != null)
                    setAction(action);
            }
        });
    }

    public void setAction(String action){
        if (action.equals("Seleccionar")){
            clickAPiece();
        }
        if (action.equals("Deseleccionar")){
           notClickAPiece();
        }
    }

    public void clickAPiece(){
        Point current = logica.getCurrentPiece();
        int x = current.x;
        int y = current.y;
        casillas[x][y].setIsSelected(true);
        logica.setSomethingClicked(true);
        System.out.println("Current Point: "+current);
    }

    public void notClickAPiece(){
        Point current = logica.getCurrentPiece();
        int x = current.x;
        int y = current.y;
        casillas[x][y].setIsSelected(false);
        logica.setSomethingClicked(false);
    }

}

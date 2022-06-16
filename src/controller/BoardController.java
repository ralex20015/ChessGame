package controller;

import Pieces.Piece;
import models.Logica;
import views.Square;
import views.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardController {

    private final Tablero game;
    private final Square [][] squares;
    private Point currentClicked;
    private Point prevPoint;
    private boolean isSomethingSelected;
    private Square previousSquare;
    private Logica logica;
    private Piece currentPieceSelected;
    //restar a i-1 y j-1

    public BoardController(Tablero game, Logica logica){
        this.game = game;
        this.logica = logica;
        squares = game.getCasillas();
        addListeners();
        isSomethingSelected = logica.isSomethingClicked();
    }

    private void addListeners() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].addMouseListener(onClick(i,j));
            }
        }
    }

    private MouseAdapter onClick(int i, int j){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                currentClicked = squares[i][j].getP();
                Piece piece = logica.getSelected(currentClicked);
                //Si teniamos una pieza seleccionada y le damos en la misma pieza
                if (prevPoint == currentClicked){
                    logica.setSomethingClicked(false);
                    currentPieceSelected = null;
                }
                //vemos que selecciono una pieza y que no este nada seleccionado
                if (piece != null && !isSomethingSelected){
                    System.out.println("No habia nada");
                    logica.setSomethingClicked(true);
                    prevPoint = currentClicked;
                    previousSquare = squares[i][j];
                    currentPieceSelected = piece;
                }

                isSomethingSelected = logica.isSomethingClicked();
                if (squares[i][j].isThereAPiece()){
                    squares[i][j].setSelected(isSomethingSelected);
                }
                //movemos
                if (isSomethingSelected){
                    if (logica.isValidMove(currentPieceSelected, currentClicked)){

                        Piece [][] before = logica.getPieces();
                        System.out.println("Antes");
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                System.out.printf("i:%d -->",(k + 1));
                                System.out.printf("j:%d  =",(l+1));
                                System.out.println(""+before[k][j]);
                            }
                        }
                        System.out.println();
                        System.out.println("despues");
                        //updateUI
                        ImageIcon imageIcon = new ImageIcon("src/Images/WHITE/pawn.png");
                        ImageIcon empty = new ImageIcon();
                        squares[i][j].setImage(imageIcon);
                        squares[i][j].setIsAPiece(true);
                        previousSquare.setImage(empty);
                        previousSquare.setIsAPiece(false);

                        //updateLogic
                        logica.setSomethingClicked(false);
                        logica.updatePieces(prevPoint,currentClicked);
                        previousSquare = null;
                        currentClicked = null;
                        Piece[][] asd =   logica.getPieces();
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                System.out.printf("i:%d -->",(k + 1));
                                System.out.printf("j:%d  =",(l+1));
                                System.out.println(""+asd[k][j]);
                            }
                        }
                    }
                }
            }
        };
    }
}

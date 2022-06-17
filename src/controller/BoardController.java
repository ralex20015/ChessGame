package controller;

import Pieces.Piece;
import models.Logica;
import models.PieceColor;
import models.Turn;
import models.ValidateMovements;
import views.Square;
import views.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardController {

    private final Square [][] squares;
    private Point currentClicked;
    private Point prevPoint;
    private boolean isSomethingSelected;
    private Square previousSquare;
    private final Logica logica;
    private Piece currentPieceSelected;
    //restar a i-1 y j-1

    public BoardController(Tablero game, Logica logica){
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
                    System.out.println("Misma pieza seleccionada");
                }

                //vemos que selecciono una pieza y que no este nada seleccionado
                if (piece != null && !isSomethingSelected && isMyTurn(piece)){
                    logica.setSomethingClicked(true);
                    prevPoint = currentClicked;
                    previousSquare = squares[i][j];
                    currentPieceSelected = piece;
                    System.out.println(squares[i][j].isThereAPiece());
                    System.out.println("Se selecciono una pieza");
                }



                //movemos
                if (isSomethingSelected){

                    ValidateMovements validateMovements = new ValidateMovements();
                    validateMovements.setCurrentSelected(currentPieceSelected);
                    validateMovements.setNextPosition(currentClicked);
                    validateMovements.setPieceOnNextPosition(piece);

                    if (logica.isValidMove(validateMovements)){
                        move(currentClicked);
                    }
                }

                isSomethingSelected = logica.isSomethingClicked();
                if (squares[i][j].isThereAPiece()){
                    squares[i][j].setSelected(isSomethingSelected);
                }
            }
        };
    }

    private void move(Point nextPoint){
        updateUI(nextPoint);
        updateLogic();
    }

    private void updateUI(Point point) {
        int i = point.x-1;
        int j = point.y -1;
        squares[i][j].setImage(getPieceImage(currentPieceSelected));
        squares[i][j].setIsAPiece(true);
        previousSquare.setImage(getPieceImage(null));
        previousSquare.setIsAPiece(false);
        previousSquare.setSelected(false);
    }

    private void updateLogic() {
        logica.setSomethingClicked(false);
        logica.updatePieces(prevPoint,currentClicked);
        previousSquare = null;
        currentClicked = null;
        currentPieceSelected = null;
    }

    private ImageIcon getPieceImage(Piece piece){
        if (piece != null){
            return new ImageIcon("src/Images/"+ piece +".png");
        }
        return new ImageIcon();
    }
    private boolean isMyTurn(Piece piece){
        PieceColor pieceColor = PieceColor.NULL;
        if (piece != null){
            pieceColor= piece.getPieceColor();
        }

        Turn temp;
        if (pieceColor == PieceColor.BLACK){
            temp = Turn.BLACKS;
        }else{
            temp = Turn.WHITES;
        }
        return temp == logica.getTurn();
    }
}

package models;

import Pieces.Pawn;
import Pieces.Piece;

import java.awt.*;

public class Logica {

    private final int COLUMNS = 8;
    private final int ROWS = 8;

    private Piece[][] pieces;
    private Point currentPiece;
    private Point pointClicked;
    private boolean somethingClicked;
    private Turn turn;


    public Logica(){
        turn = Turn.WHITES;
        pieces = new Piece[ROWS][COLUMNS];
        initializePieces();
    }

    private void initializePieces() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Point p = new Point(i+1,j+1);
                if (i == 6){
                  pieces[i][j] = new Pawn(p,PieceColor.BLACK);
                }
                if (i == 1){
                    pieces[i][j] = new Pawn(p,PieceColor.WHITE);
                }
            }
        }
    }

    public Piece getSelected(Point p){
        return pieces[p.x-1][p.y-1];
    }

    public boolean isSomethingClicked() {
        return somethingClicked;
    }

    public void setSomethingClicked(boolean somethingClicked) {
        this.somethingClicked = somethingClicked;
    }

    public boolean isValidMove(ValidateMovements validateMovements){
        PieceColor pieceColor = PieceColor.NULL;
        //Vemos si hay una pieza en la otra posicion
        Piece currentPieceSelected = validateMovements.getCurrentSelected();
        Point nextPosition = validateMovements.getNextPosition();
        Piece otherPiece = validateMovements.getPieceOnNextPosition();

        if (currentPieceSelected != null){
           pieceColor= currentPieceSelected.getPieceColor();
        }

        Turn temp;
        if (pieceColor == PieceColor.BLACK){
            temp = Turn.BLACKS;
        }else{
            temp = Turn.WHITES;
        }

        if (currentPieceSelected instanceof Pawn && getTurn() == temp){
            return ((Pawn) currentPieceSelected).isValidMove(nextPosition,otherPiece);
        }
        return false;
    }

    public void updatePieces(Point prevPoint,Point nextPoint){
        int beforeMoveX = prevPoint.x-1;
        int beforeMoveY = prevPoint.y-1;
        int afterMoveX = nextPoint.x-1;
        int afterMoveY = nextPoint.y-1;

        pieces[afterMoveX][afterMoveY] = pieces[beforeMoveX][beforeMoveY];
        pieces[afterMoveX][afterMoveY].setPosition(nextPoint);

        if (turn == Turn.WHITES){
            setTurn(Turn.BLACKS);
        }else{
            setTurn(Turn.WHITES);
        }

        pieces[beforeMoveX][beforeMoveY] = null;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setTurn(Turn turn){
        this.turn = turn;
    }

    public Turn getTurn() {
        return turn;
    }
}

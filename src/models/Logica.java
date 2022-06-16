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
    private String pieceClicked;


    public Logica(){
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

    public boolean isValidMove(Piece piece, Point nextPosition){
        if (piece instanceof Pawn){
            Point currentPosition = piece.getPosition();
            System.out.println(nextPosition);
            return nextPosition.x == currentPosition.x + 1;
        }
        return false;
    }

    public void updatePieces(Point prevPoint,Point nextPoint){
        int beforeMoveX = prevPoint.x-1;
        int beforeMoveY = prevPoint.y-1;
        int afterMoveX = nextPoint.x-1;
        int afterMoveY = nextPoint.y-1;
        pieces[afterMoveX][afterMoveY] = pieces[beforeMoveX][beforeMoveY];
        pieces[beforeMoveX][beforeMoveY] = null;
    }

    public Piece[][] getPieces() {
        return pieces;
    }
}

package models;

import Pieces.Piece;

import java.awt.*;

public class ValidateMovements {

    private Piece currentSelected;
    private Point nextPosition;
    private Piece pieceOnNextPosition;

    public ValidateMovements(){
        this.currentSelected = null;
        this.nextPosition = null;
        this.pieceOnNextPosition = null;
    }

    public Piece getCurrentSelected() {
        return currentSelected;
    }

    public void setCurrentSelected(Piece currentSelected) {
        this.currentSelected = currentSelected;
    }

    public Point getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Point nextPosition) {
        this.nextPosition = nextPosition;
    }

    public Piece getPieceOnNextPosition() {
        return pieceOnNextPosition;
    }

    public void setPieceOnNextPosition(Piece pieceOnNextPosition) {
        this.pieceOnNextPosition = pieceOnNextPosition;
    }
}

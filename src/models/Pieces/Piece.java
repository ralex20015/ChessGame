package models.Pieces;

import models.PieceColor;

import java.awt.*;

abstract public class Piece implements Actions{

    private Point position;
    private PieceColor pieceColor;

    public Piece(Point position, PieceColor pieceColor){
        this.position = position;
        this.pieceColor = pieceColor;
    }

    public Piece(){
        this.position = null;
    }
    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }
}

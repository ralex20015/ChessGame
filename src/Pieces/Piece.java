package Pieces;

import models.PieceColor;

import java.awt.*;

abstract public class Piece {

    private Point position;
    private PieceColor pieceColor;

    public abstract Point getDestinationPosition();

    public Piece(Point position, PieceColor pieceColor){
        this.position = position;
        this.pieceColor = pieceColor;
    }

    public Piece(){
        this.position = null;
    }
    private void move(){

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

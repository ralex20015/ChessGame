package Pieces;

import models.PieceColor;

import java.awt.*;

public class Pawn extends Piece{

    @Override
    public Point getDestinationPosition() {
        return null;
    }

    public Pawn(Point p,PieceColor pieceColor){
        super(p,pieceColor);
    }
    public String toString(){
        return this.getPieceColor()+"/"+"pawn";
    }
}

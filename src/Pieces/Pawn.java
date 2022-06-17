package Pieces;

import models.PieceColor;

import java.awt.*;

public class Pawn extends Piece{

    public Pawn(Point p,PieceColor pieceColor){
        super(p,pieceColor);
    }
    public String toString(){
        return this.getPieceColor()+"/"+"pawn";
    }

    public boolean isValidMove(Point nextPosition, Piece otherPiece){
        if (isStartPosition()){
            return isAdvancingOneSquare(nextPosition,otherPiece)
                    || isAdvancingTwoSquares(nextPosition);
        }else{
            return isAdvancingOneSquare(nextPosition,otherPiece) || canIEat(nextPosition,otherPiece);
        }
    }



    private boolean isStartPosition(){
        Piece piece = this;
        Point currentPosition = piece.getPosition();
        return  currentPosition.x == 2 || currentPosition.x == 7;
    }

    private boolean isAdvancingOneSquare(Point nextPosition, Piece otherPiece){
        if (getPieceColor() == PieceColor.WHITE){
           return (nextPosition.x == getPosition().x + 1 && nextPosition.y == getPosition().y)
                   && otherPiece == null;
        }
        if (getPieceColor() == PieceColor.BLACK){
            return (nextPosition.x == getPosition().x -1  && nextPosition.y == getPosition().y)
                    && otherPiece == null;
        }
      return false;
    }

    private boolean isAdvancingTwoSquares(Point nextPosition){
        if (getPieceColor() == PieceColor.WHITE){
            return nextPosition.x == getPosition().x + 2 && nextPosition.y == getPosition().y;
        }
        if (getPieceColor() == PieceColor.BLACK){
            return nextPosition.x == getPosition().x -2  && nextPosition.y == getPosition().y;
        }
        return false;
    }

    private boolean canIEat(Point nextPosition, Piece otherPiece) {
        if (getPieceColor() == PieceColor.WHITE){
            return nextPosition.x == getPosition().x +1 &&
                    (nextPosition.y == getPosition().y+1 || nextPosition.y == getPosition().y-1)
                    && otherPiece != null;
        }

        if (getPieceColor() == PieceColor.BLACK){
            return nextPosition.x == getPosition().x -1 &&
                    (nextPosition.y == getPosition().y+1 || nextPosition.y == getPosition().y-1)
                    && otherPiece != null;
        }
       return false;
    }
}

package models.Pieces;

import models.PieceColor;
import models.PieceState;

import java.awt.*;

public class Queen extends Piece{

    public Queen(Point position, PieceColor pieceColor) {
        super(position, pieceColor);
    }

    @Override
    public PieceState move(Point pointToMove, Piece piece) {
        return null;
    }
}

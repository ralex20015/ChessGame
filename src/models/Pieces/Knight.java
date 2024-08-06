package models.Pieces;

import models.PieceColor;
import models.PieceState;

import java.awt.*;

public class Knight extends Piece{

    public Knight(Point position, PieceColor pieceColor) {
        super(position, pieceColor);
    }

    @Override
    public PieceState move(Point pointToMove, Piece piece) {
        return null;
    }
}

package models.Pieces;

import models.PieceColor;
import models.PieceState;

import java.awt.*;

public class Bishop extends Piece {

    public Bishop(Point position, PieceColor pieceColor) {
        super(position, pieceColor);
    }

    @Override
    public PieceState move(Point pointToMove, Piece piece) {
        return null;
    }
}

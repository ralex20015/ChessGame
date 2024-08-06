package models.Pieces;

import models.PieceState;

import java.awt.*;

public interface Actions {
    PieceState move(Point pointToMove, Piece piece);
}

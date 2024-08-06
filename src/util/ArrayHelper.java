package util;

import models.Pieces.Piece;

import java.awt.*;

public class ArrayHelper {
    public static Point normalCoordinatesToArrayCoordinates(Piece piece) {
        return new Point(piece.getPosition().x - 1, piece.getPosition().y - 1);
    }
    public static Point normalCoordinatesToArrayCoordinates(Point point) {
        return new Point(point.x - 1, point.y - 1);
    }


}

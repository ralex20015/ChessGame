package models;

import models.Pieces.*;
import views.Player;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {
    private Player player;
    private Turn turn;
    //isSomethingSelected.
    //Piezas
    //Turno
    //Player

    private Piece[][] pieces;

    public GameLogic() {
        //TODO: Initialize pieces
        player = Player.WHITES;
        turn = Turn.WHITES;
        pieces = new Piece[2][16];
        resetGame();
    }

    public void resetGame() {
        setBlackPieces();
        setWhitePieces();
    }

    private void setBlackPieces() {
        for (int i = 8; i <= 15; i++) {
            pieces[1][i] = new Pawn(new Point(7, (i % 8) + 1), PieceColor.BLACK);
        }
        //set the towers
        pieces[1][0] = new Tower(new Point(8, 1), PieceColor.BLACK);
        pieces[1][7] = new Tower(new Point(8, 8), PieceColor.BLACK);

        //set Knight
        pieces[1][1] = new Knight(new Point(8, 2), PieceColor.BLACK);
        pieces[1][6] = new Knight(new Point(8, 7), PieceColor.BLACK);

        //set Bishop
        pieces[1][2] = new Bishop(new Point(8, 3), PieceColor.BLACK);
        pieces[1][3] = new Bishop(new Point(8, 6), PieceColor.BLACK);

        pieces[1][4] = new Queen(new Point(8, 4), PieceColor.BLACK);
        pieces[1][5] = new King(new Point(8, 5), PieceColor.BLACK);


    }

    private void setWhitePieces() {
        for (int i = 8; i <= 15; i++) {
            pieces[0][i] = new Pawn(new Point(2, (i % 8) + 1), PieceColor.WHITE);
        }
        pieces[0][0] = new Tower(new Point(1, 1), PieceColor.WHITE);
        pieces[0][7] = new Tower(new Point(1, 8), PieceColor.WHITE);

        pieces[0][1] = new Knight(new Point(1, 2), PieceColor.WHITE);
        pieces[0][6] = new Knight(new Point(1, 7), PieceColor.WHITE);

        pieces[0][2] = new Bishop(new Point(1, 3), PieceColor.WHITE);
        pieces[0][3] = new Bishop(new Point(1, 6), PieceColor.WHITE);

        pieces[0][4] = new Queen(new Point(1, 4), PieceColor.WHITE);
        pieces[0][5] = new King(new Point(1, 5), PieceColor.WHITE);
    }

    public static String getImageToPaintInBaseOfPiece(Piece piece) {
        String routeOfImage = "src/Images/";
        if (piece.getPieceColor() == PieceColor.BLACK) {
            routeOfImage += "BLACK/";

        } else {
            routeOfImage += "WHITE/";
        }

        if (piece instanceof Pawn) {
            routeOfImage = routeOfImage + "pawn.png";
            return routeOfImage;
        }
        if (piece instanceof Tower) {
            routeOfImage = routeOfImage + "tower.png";
            return routeOfImage;
        }

        if (piece instanceof Knight) {
            routeOfImage = routeOfImage + "knight.png";
            return routeOfImage;
        }
        if (piece instanceof Bishop) {
            routeOfImage = routeOfImage + "bishop.png";
            return routeOfImage;
        }

        if (piece instanceof Queen) {
            routeOfImage = routeOfImage + "queen.png";
            return routeOfImage;
        }

        if (piece instanceof King) {
            routeOfImage = routeOfImage + "king.png";
            return routeOfImage;
        }
        return routeOfImage;
    }


    public List<Piece> getPieces() {

        return Arrays.stream(pieces)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayerColor(Player playerColor) {
        this.player = playerColor;
        resetGame();
    }

    public Piece getPieceSelected(Point point) {
        var listOfPieces = getPieces();
        for (Piece piece : listOfPieces) {
            if (piece != null) {
                if (point.equals(piece.getPosition())) {
                    var pieceColor = piece.getPieceColor() + "S"; //Fix this
                    if (pieceColor.equals(turn.toString())) {
                        return piece;
                    }
                }
            }
        }
        return null;
    }
}

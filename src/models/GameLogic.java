package models;

import models.Pieces.Pawn;
import models.Pieces.Piece;
import views.Player;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameLogic {
    private Player player;
    private Turn turn;
    private Point squareSelected;
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
    }

    private void setWhitePieces() {
        for (int i = 8; i <= 15; i++) {
            pieces[0][i] = new Pawn(new Point(2, (i % 8) + 1), PieceColor.WHITE);
        }
    }

    public static String getImageToPaintInBaseOfPiece(Piece piece) {
        String routeOfImage = "src/Images/";
        if (piece.getPieceColor() == PieceColor.BLACK){
            routeOfImage += "BLACK/";

       } else {
            routeOfImage += "WHITE/";
        }

        if (piece instanceof Pawn){
            routeOfImage = routeOfImage + "pawn.png";
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
                    var pieceColor = piece.getPieceColor()+"S"; //Fix this
                    if(pieceColor.equals(turn.toString())){
                        return piece;
                    }
                }
            }
        }
        return  null;
    }
}

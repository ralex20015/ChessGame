package views;

import models.GameLogic;
import models.Pieces.Piece;
import util.ArrayHelper;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Board extends JPanel {

    private Square[][] squares;
    private Player playerColor;
    private List<Piece> pieces;
    private Piece pieceSelected;
    private Point previousPoint;


    public Board(List<Piece> pieces) {
        this.pieces = pieces;
        resetGame();
    }

    //Al tablero solo le interesa que pieza pintar
    private void initBoard() {
        Point p;
        if (playerColor == Player.WHITES) {
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j <= 7; j++) {
                    p = new Point(i + 1, j + 1);
                    squares[i][j] = new Square(p);
                    add(squares[i][j]);
                }
            }
        } else {
            for (int i = 0; i <= 7; i++) {
                for (int j = 7; j >= 0; j--) {
                    p = new Point(i + 1, j + 1);
                    squares[i][j] = new Square(p);
                    add(squares[i][j]);
                }
            }
        }
    }
    private void addPieces() {
        for (Piece piece : pieces) {
            if (piece != null) {
                Point p = piece.getPosition();
                squares[p.x - 1][p.y - 1].setImageIcon(
                        paintIcon(GameLogic.getImageToPaintInBaseOfPiece(piece))
                );
            }
        }
    }

    public void setGameController(GameController gameController) {
        for (Square[] row : squares) {
            for (Square square : row) {
                square.setGameController(gameController);
            }
        }
    }
    private ImageIcon paintIcon(String route) {
        return new ImageIcon(route);
    }


    public void resetGame() {
        removeAll();
        int COLUMNS = 8;
        int ROWS = 8;
        setLayout(new GridLayout(ROWS, COLUMNS));
        squares = new Square[ROWS][COLUMNS];
        initBoard();
        addPieces();
        validate();
    }

    public void resetPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void setPlayerColor(Player playerColor) {
        this.playerColor = playerColor;
    }

    public void setPieceSelected(Piece pieceSelected) {
        System.out.println("Select Square on: "+pieceSelected.getPosition());
        Point p = ArrayHelper.normalCoordinatesToArrayCoordinates(pieceSelected);
        this.pieceSelected = pieceSelected;
        previousPoint = p;
        squares[p.x][p.y].changeColor(true);
    }

    public void resetSquareSelectedToDefaultColor() {
        if (previousPoint != null)
            squares[previousPoint.x][previousPoint.y].changeColor(false);
        this.pieceSelected = null;
        previousPoint = null;
        System.out.println("Reset pieces");
    }

    public void movePiece(Point pointToMovePiece) {
        Point temp = ArrayHelper.normalCoordinatesToArrayCoordinates(pointToMovePiece);
        if (previousPoint != null) {
            squares[previousPoint.x][previousPoint.y].setImageIcon(null);
            squares[temp.x][temp.y].setImageIcon(
                    new ImageIcon(GameLogic.getImageToPaintInBaseOfPiece(pieceSelected))
            );
        }

        squares[temp.x][temp.y].repaint();
        System.out.println("Movemos pieza a :"+pointToMovePiece);
        resetSquareSelectedToDefaultColor();
    }
}

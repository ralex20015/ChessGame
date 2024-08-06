package views;

import models.GameLogic;
import models.PieceState;
import models.Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameViewModel implements GameController, Subject {

    private final GameLogic gameLogic;
    private Piece piece;
    private List<Piece> pieces;
    private Point pointToMovePiece;
    private final List<Observer> observers;
    private PieceState pieceState;

    public GameViewModel() {
        gameLogic = new GameLogic();
        observers = new ArrayList<>();
        resetGame();
    }

    public void resetGame() {
        gameLogic.resetGame();
        pieces = gameLogic.getPieces();
        notifyObservers();
    }

    public void setPlayerColor(Player playerColor) {
        gameLogic.setPlayerColor(playerColor);
        notifyObservers();
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Player getPlayerColor() {
        return gameLogic.getPlayer();
    }

    @Override
    public void onSquareClicked(Square square) {
        //Tengo que cambiar esto ya que introduje side effects
        Piece piece = gameLogic.getPieceSelected(square.getP());
        pointToMovePiece = square.getP();
        if (!isThereAPieceSelected()) {
            this.piece = piece;
            if (piece != null) {
                pieceState = PieceState.PIECE_SELECTED;
                notifyObservers();
            }
        } else {
            if (isPieceSelectedAgain()) {
                this.piece = null;
                pieceState = PieceState.DESELECT_PIECE;
            } else {
                pieceState = this.piece.move(pointToMovePiece, piece);
            }
            notifyObservers();

            if (pieceState != PieceState.DO_NOTHING)
                resetStatesToDefault();

        }
    }

    public boolean isThereAPieceSelected() {
        return this.piece != null;
    }

    private boolean isPieceSelectedAgain() {
        return pointToMovePiece.equals(piece.getPosition());
    }

    private void resetStatesToDefault() {
        this.piece = null;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Piece getPiece() {
        return piece;
    }

    public PieceState getPieceState() {
        return pieceState;
    }

    public Point getPointToMovePiece() {
        return pointToMovePiece;
    }
}

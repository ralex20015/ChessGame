package views;

import models.GameLogic;
import models.Pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameViewModel implements GameController, Subject {

    private boolean isPieceSelected;
    private GameLogic gameLogic;
    private Piece piece;
    private List<Piece> pieces;
    private Point pointToMovePiece;
    private Square previousSquare;
    private List<Observer> observers;

    public GameViewModel() {
        gameLogic = new GameLogic();
        observers = new ArrayList<>();
        resetGame();
    }

    public void resetGame() {
        //Las piezas se van a pintar en base a el modelo
        // repository.resetGame();
        gameLogic.resetGame();
        pieces = gameLogic.getPieces();
    }

    public void setPlayerColor(Player playerColor) {
        gameLogic.setPlayerColor(playerColor);
    }

    public void onPieceSelected() {

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
                //Aqui entraria mi observable
//                previousSquare = square;
//                square.changeColor(true);
                notifyObservers();
            }
        } else {
            if (isPieceSelectedAgain()) {
                this.piece = null;
            } else {
                this.piece.move(pointToMovePiece, piece);
                //Update ImageIcon on the squares

                //Estoy introduciendo side effects en esto
                //previousSquare.setImageIcon(null);
//                square.setImageIcon(
//                        new ImageIcon(GameLogic.getImageToPaintInBaseOfPiece(this.piece))
//                );
                //Notificar que cambio la posicion de una pieza
            }
            notifyObservers();
            //Update UI
//            resetColorOfSquare(square);
//
//            //Update logic
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
        previousSquare = null;
    }

    private void resetColorOfSquare(Square square) {
        square.changeColor(false);
        previousSquare.changeColor(false);
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
}

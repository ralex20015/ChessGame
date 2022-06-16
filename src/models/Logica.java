package models;

import java.awt.*;

public class Logica {

    private final int COLUMNS = 8;
    private final int ROWS = 8;

    private String [] whitePawns;
    private String []blackPawns;

    private String [][] piece;
    private Point currentPiece;
    private Point pointClicked;
    private boolean somethingClicked;
    private String pieceClicked;


    public Logica(){
        whitePawns = new String[COLUMNS];
        blackPawns = new String[COLUMNS];
        piece = new String[ROWS][COLUMNS];
        initializePieces();
    }

    private void initializePieces() {
        for (int i = 0; i < ROWS; i++) {
            piece[1][i] = "PawnW";
            piece[6][i]= "PawnB";
        }
    }

    public void clickSomething(Point p){
        pointClicked = p;
        if(isThereAPiece()  && !isSomethingClicked()){
            currentPiece = p;
            pieceClicked = piece[currentPiece.x][currentPiece.y];
        }
    }

    private boolean isThereAPiece(){
        return piece[pointClicked.x][pointClicked.y] != null;
    }

    public Point getCurrentPiece() {
        return currentPiece;
    }

    public String getPieceClicked() {
        return pieceClicked;
    }

    public boolean isSomethingClicked() {
        return somethingClicked;
    }

    public void setSomethingClicked(boolean somethingClicked) {
        this.somethingClicked = somethingClicked;
    }
}

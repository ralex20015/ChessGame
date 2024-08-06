package views;


import models.PieceState;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class GameScreen extends JFrame implements Observer {


    private final Board tablero;
    private final GameViewModel gameViewModel;


    public GameScreen() throws HeadlessException {
        gameViewModel = new GameViewModel();
        setCustomBar();
        tablero = new Board(gameViewModel.getPieces());
        tablero.setLocation(65, 50);
        tablero.setSize(475, 475);
        resetGame();
        gameViewModel.addObserver(this);
        initFrame();
    }

    private void setCustomBar() {
        JMenuBar menu = new JMenuBar();
        JMenu barPlayAs = new JMenu("Jugar como");

        JMenuItem itemBarPlayAsWhite = new JMenuItem("Blancas");
        itemBarPlayAsWhite.addActionListener(actionEvent -> {
            gameViewModel.resetGame();
            gameViewModel.setPlayerColor(Player.WHITES);
            resetGame();
        });

        JMenuItem itemBarPlayAsBlack = new JMenuItem("Negras");
        itemBarPlayAsBlack.addActionListener(actionEvent -> {
            gameViewModel.resetGame();
            gameViewModel.setPlayerColor(Player.BLACKS);
            resetGame();
        });
        JMenu menuResetGame = getMenu();
        barPlayAs.add(itemBarPlayAsBlack);
        barPlayAs.add(itemBarPlayAsWhite);
        menu.add(barPlayAs);
        menu.add(menuResetGame);
        setJMenuBar(menu);
    }

    private JMenu getMenu() {
        JMenu menuResetGame = new JMenu("Reiniciar juego");
        menuResetGame.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent menuEvent) {
                gameViewModel.resetGame();
                resetGame();
            }

            @Override
            public void menuDeselected(MenuEvent menuEvent) {

            }

            @Override
            public void menuCanceled(MenuEvent menuEvent) {

            }
        });
        return menuResetGame;
    }

    private void initFrame() {
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Chess Game");
        add(tablero, BorderLayout.CENTER);
    }

    @Override
    public void update() {
        //Maybe hacer otro callback
        if (gameViewModel.getPieceState() == PieceState.PIECE_SELECTED)
            tablero.setPieceSelected(gameViewModel.getPiece());
        else if (gameViewModel.getPieceState() == PieceState.DESELECT_PIECE){
            tablero.resetSquareSelectedToDefaultColor();
        }else if(gameViewModel.getPieceState() == PieceState.MOVE){
            tablero.movePiece(gameViewModel.getPointToMovePiece());
        }
    }

    private void resetGame() {
        tablero.resetPieces(gameViewModel.getPieces());
        tablero.setPlayerColor(gameViewModel.getPlayerColor());
        tablero.resetGame();
        tablero.setGameController(gameViewModel);
    }
}

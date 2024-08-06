package views;


import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class GameScreen extends JFrame implements Observer {


    private final Tablero tablero;
    private final GameViewModel gameViewModel;


    public GameScreen() throws HeadlessException {
        gameViewModel = new GameViewModel();
        setCustomBar();
        tablero = new Tablero(gameViewModel.getPieces());
        tablero.setLocation(65, 50);
        tablero.setSize(475, 475);
        tablero.setPlayerColor(gameViewModel.getPlayerColor());
        tablero.setGameController(gameViewModel);
        gameViewModel.addObserver(this);
        initFrame();
    }

    private void setCustomBar() {
        JMenuBar menu = new JMenuBar();
        JMenu barPlayAs = new JMenu("Jugar como");

        JMenuItem itemBarPlayAsWhite = new JMenuItem("Blancas");
        itemBarPlayAsWhite.addActionListener(actionEvent -> {
            gameViewModel.setPlayerColor(Player.WHITES);
            tablero.setPlayerColor(gameViewModel.getPlayerColor());
        });

        JMenuItem itemBarPlayAsBlack = new JMenuItem("Negras");
        itemBarPlayAsBlack.addActionListener(actionEvent -> {
            gameViewModel.setPlayerColor(Player.BLACKS);
            tablero.setPlayerColor(gameViewModel.getPlayerColor());
        });
        JMenu menuResetGame = new JMenu("Reiniciar juego");
        menuResetGame.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent menuEvent) {
                gameViewModel.resetGame();
                tablero.resetGame();
                tablero.resetPieces(gameViewModel.getPieces());
            }

            @Override
            public void menuDeselected(MenuEvent menuEvent) {

            }

            @Override
            public void menuCanceled(MenuEvent menuEvent) {

            }
        });
        barPlayAs.add(itemBarPlayAsBlack);
        barPlayAs.add(itemBarPlayAsWhite);
        menu.add(barPlayAs);
        menu.add(menuResetGame);
        setJMenuBar(menu);
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
        if (gameViewModel.isThereAPieceSelected())
            tablero.setPieceSelected(gameViewModel.getPiece());
        else {
            tablero.resetSquareToDefaultColor();
        }
    }
}

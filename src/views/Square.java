package views;

import util.NumberToLetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Square extends JPanel implements MouseListener {

    private Point p;
    //Depende de este estado
    private ImageIcon imageIcon;
    private Color squareColor;
    private Color defaultColor;
    private GameController gameController;

    public Square(Point p){
        this.p = p;
        setSquareColor(p);
        setOpaque(true);
        addMouseListener(this);
    }

    private void setSquareColor(Point point){
        int result = point.x + point.y;
        if (result % 2 == 1){
            squareColor = Color.WHITE;
        }else{
            squareColor = new Color(95,159,223);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Las letras del square dependen del estado Player
        setBackground(squareColor);
        if (p.y == 1)
            g.drawString(String.valueOf(p.x), 5, 10);
        if (p.x == 1) {
            g.drawString(NumberToLetter.convertNumberToLetter(p.y), 50, 50);
        }
        if (imageIcon != null) {
            imageIcon.paintIcon(this,g,7,0);
        }
    }

    public Point getP() {
        return p;
    }

    public void setPoint(Point p) {
        this.p = p;
    }

    public void setImageIcon(ImageIcon imageIcon){
        this.imageIcon = imageIcon;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        gameController.onSquareClicked(this);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void changeColor(boolean isSomethingSelected) {
        if (isSomethingSelected) {
            squareColor = Color.GREEN;
        }else {
            setSquareColor(p);
        }
        repaint();
    }
}

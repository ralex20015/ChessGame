package views;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {

    private Point p;

    private boolean isSelected;
    private String defaultColor;
    private ImageIcon imageIcon;
    private boolean isThereApiece;

    public Square(Point p){
        this.p = p;
        setSquareColor(p);
        setOpaque(true);
    }

    public Square(Point p,ImageIcon imageIcon){
        this.p = p;
        this.imageIcon = imageIcon;
        setSquareColor(p);
        setOpaque(true);
    }

    private void setSquareColor(Point point){
        int result = point.x + point.y;
        if (result % 2 == 1){
            defaultColor = "White";
        }else{
            defaultColor = "Black";
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setDefaultColor();
        imageIcon.paintIcon(this,g,7,0);
    }


    public void setDefaultColor(){
        if (!isSelected){
            if (defaultColor.equals("White")){
                setBackground(Color.WHITE);
            }
            if (defaultColor.equals("Black")){
                setBackground(new Color(95,159,223));
            }
        }else{
            setBackground(new Color(140,202,50));
        }
    }

    public void setImage(ImageIcon piece){
        this.imageIcon = piece;
        repaint();
    }

    public Point getP() {
        return p;
    }

    public boolean isThereAPiece(){
        return isThereApiece;
    }

    public void setIsAPiece(boolean isAPiece){
        this.isThereApiece = isAPiece;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if (isSelected){
            repaint();
        }else{
            repaint();
        }
    }

    public String getDefaultColor() {
        return defaultColor;
    }
}

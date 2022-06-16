package views;

import javax.swing.*;
import java.awt.*;

public class Square extends JPanel {

    private final Point p;
    private boolean isSelected;
    private String defaultColor;

    public Square(Point p){
        this.p = p;
        setSquareColor(p);
    }

    private void setSquareColor(Point point){
        int result = point.x + point.y;
        if (result % 2 == 0){
            defaultColor = "White";
        }else{
            defaultColor = "Black";
        }
    }

    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setDefaultColor();
    }


    public void setDefaultColor(){
        if (!isSelected){
            if (defaultColor.equals("White")){
                this.setBackground(Color.WHITE);
            }
            if (defaultColor.equals("Black")){
                this.setBackground(Color.BLACK);
            }
        }else{
            this.setBackground(new Color(140,202,50));
        }
    }

}

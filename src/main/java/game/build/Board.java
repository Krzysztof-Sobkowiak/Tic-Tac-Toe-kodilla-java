package game.build;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board extends Button{
    private Image circleOrange = new Image("circleOrange.png");
    private Image crossRed = new Image("crossRed.png");
    private int value;

    public void setState(int state){
        if (state == 0){
            setGraphic(null);
            setText("");
            value = 0;
        } else if (state == 1){
            setGraphic(new ImageView(crossRed));
            value = 1;
            setOpacity(1);
        } else if (state == -1){
            setGraphic(new ImageView(circleOrange));
            value = - 1;
            setOpacity(1);
        }
    }

    public int getValue() {
        return value;
    }
}

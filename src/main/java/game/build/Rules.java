package game.build;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Rules{
    public void rulesGame(){
        String rules = "Rules for game Tic Tac Toe\n\n" +
                "1. The game is played on a grid that's 3 squares by 3 squares.\n\n" +
                "2. You are X, your friend (or the computer in this case) is O.\n" +
                "    Players take turns putting their marks in empty squares.\n\n" +
                "3. The first player to get 3 of her marks in a row\n" +
                "    (up, down, across, or diagonally) is the winner.\n\n" +
                "4. When all 9 squares are full, the game is over.\n " +
                "    If no player has 3 marks in a row, the game ends in a tie.\n\n";

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);

        Text dialog =  new Text(rules);
        dialog.setFont(new Font("Regular",20));


        Button close = new Button("Close");
        close.setPrefSize(400,35);
        close.setOnAction(event -> {

            stage.hide();
        });

        VBox vbox = new VBox(dialog,close);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        stage.setScene(new Scene(vbox));
        stage.showAndWait();

    }
}

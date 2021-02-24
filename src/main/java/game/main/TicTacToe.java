package game.main;

import game.build.Board;
import game.build.Rules;
import game.logic.GameLogic;
import game.panel.ButtonPanel;
import game.panel.ScoreBoard;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.Serializable;

import static game.logic.GameLogic.maxButtons;

public class TicTacToe extends Application implements Serializable{
    private Image imageback = new Image("/Board.png");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GameLogic gameLogicController = new GameLogic();
        ScoreBoard scoreBoard = new ScoreBoard();
        Rules rules = new Rules();
        ButtonPanel buttonPanel = new ButtonPanel();

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 1, 1, 80));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        Board[] buttons = new Board[maxButtons];
        for (int i = 0; i < maxButtons; i++){
            buttons[i] = new Board();
            buttons[i].setPrefSize(120, 120);
        }

        int i = 0;
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                gridPane.add(buttons[i],y,x, 1, 1);
                i++;
            }
        }

        Scene scene = new Scene(borderPane, 820, 570, Color.BLACK);
        borderPane.setBackground(background);
        borderPane.setLeft(gridPane);
        borderPane.setTop(scoreBoard.gameplayTable(gameLogicController));
        borderPane.setRight(buttonPanel.controlPanel(gameLogicController,buttons,rules));

        gameLogicController.game(buttons);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
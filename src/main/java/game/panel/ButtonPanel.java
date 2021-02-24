package game.panel;

import game.build.Board;
import game.build.Rules;
import game.logic.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonPanel{
    public GridPane controlPanel(GameLogic gameLogic,Board[] buttons,Rules rules)   {
        GridPane controlPanelButtons = new GridPane();
        controlPanelButtons.setPadding(new Insets(20, 80, 1, 65));
        controlPanelButtons.setHgap(20);
        controlPanelButtons.setVgap(12);

        Label tictactoe = new Label(" Tic Tac Toe ");
        tictactoe.setPrefSize(230, 40);
        tictactoe.setFont(new Font("Normal", 30));
        tictactoe.setAlignment(Pos.CENTER);
        tictactoe.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        Label game = new Label("GAME");
        game.setPrefSize(230, 35);
        game.setFont(new Font("Regular", 19));
        game.setAlignment(Pos.CENTER);
        game.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        game.textFillProperty().set(Color.WHITESMOKE);

        Button gameMode = new Button("Gameplay Selection");
        gameMode.setPrefSize(230, 45);
        gameMode.setOnAction(event -> {
            gameLogic.select(buttons);
        });

        Button save = new Button("Save");
        save.setPrefSize(230, 45);
        save.setOnAction(event -> {
            gameLogic.saveGame();
        });

        Button load = new Button("Load");
        load.setPrefSize(230, 45);
        load.setOnAction(event -> {
            gameLogic.loadGame();
            gameLogic.resetRound(buttons);
        });

        Button rul = new Button("Rules");
        rul.setPrefSize(230, 45);
        rul.setOnAction(event -> {
            rules.rulesGame();

        });

        Button resetGame = new Button("Reset");
        resetGame.setPrefSize(230, 45);
        resetGame.setOnAction(event -> {
            gameLogic.resetRound(buttons);
            gameLogic.resetGame();
        });

        controlPanelButtons.add(tictactoe,0,0);
        controlPanelButtons.add(game, 0, 1);
        controlPanelButtons.add(gameMode, 0, 2);
        controlPanelButtons.add(save, 0, 3);
        controlPanelButtons.add(load, 0, 4);
        controlPanelButtons.add(resetGame, 0, 6);
        controlPanelButtons.add(rul, 0, 5);

        return controlPanelButtons;
    }
}
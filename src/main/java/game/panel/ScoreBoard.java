package game.panel;

import game.logic.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreBoard{
    public GridPane gameplayTable(GameLogic gameLogic){
        GridPane scoreGamePlay = new GridPane();
        scoreGamePlay.setPadding(new Insets(27, 375, 1, 80));

        Label player = new Label("PLAYER");
        player.setPrefSize(100, 26);
        player.setFont(new Font(22));
        player.setAlignment(Pos.CENTER);
        player.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        player.textFillProperty().set(Color.WHITESMOKE);

        Label playerPoints = new Label();
        playerPoints.setPrefSize(50, 65);
        playerPoints.setFont(new Font(35));
        playerPoints.setAlignment(Pos.CENTER_RIGHT);
        playerPoints.textProperty().bind(gameLogic.getPlayerPoint().asString());
        playerPoints.textFillProperty().set(Color.WHITESMOKE);

        Label vs = new Label("  :   ");
        vs.setPrefSize(100, 26);
        vs.setFont(new Font(25));
        vs.setAlignment(Pos.CENTER);
        vs.textFillProperty().set(Color.WHITESMOKE);

        Label cpu = new Label("COMPUTER");
        cpu.setPrefSize(110, 26);
        cpu.setFont(new Font(20));
        cpu.setAlignment(Pos.CENTER);
        cpu.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        cpu.textFillProperty().set(Color.WHITESMOKE);

        Label cpuPoints = new Label();
        cpuPoints.setPrefSize(50, 65);
        cpuPoints.setFont(new Font(38));
        cpuPoints.setAlignment(Pos.CENTER_LEFT);
        cpuPoints.textProperty().bind(gameLogic.getComputerPoint().asString());
        cpuPoints.textFillProperty().set(Color.WHITESMOKE);

        scoreGamePlay.add(player,0,0);
        scoreGamePlay.add(playerPoints, 1, 0);
        scoreGamePlay.add(vs,2,0);
        scoreGamePlay.add(cpuPoints, 3, 0);
        scoreGamePlay.add(cpu, 4,0);

        return scoreGamePlay;
    }
}


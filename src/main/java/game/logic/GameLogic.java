package game.logic;

import game.build.Board;
import game.build.SaveGame;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;

public class GameLogic implements Serializable{
    private int move = 0;
    private int player = 1;
    private int cpu = -1;
    private boolean play = true;
    private boolean computer = true;
    private boolean crossWin = false;
    private boolean circleWin = false;
    private static final int maxMoves = 9;
    public static final int maxButtons = 9;

    private Random random = new Random();
    private Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    private File save = new File("SaveGame.save");

    private int win = 0;
    private int computerWin = 0;
    private int playerWin = 0;
    private IntegerProperty winner = new SimpleIntegerProperty();
    private IntegerProperty playerPoint = new SimpleIntegerProperty();
    private IntegerProperty computerPoint = new SimpleIntegerProperty();

    public IntegerProperty getWinner(){
        return winner;
    }
    public IntegerProperty getComputerPoint(){
        return computerPoint;
    }
    public IntegerProperty getPlayerPoint(){
        return playerPoint;
    }

    public void select(Board[] buttons){
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);

        Text dialog = new Text("Select the type of game");
        dialog.setFont(new Font("Regular",25));

        Button playerVsComputer = new Button("Player vs Computer");
        playerVsComputer.setPrefSize(200,25);
        playerVsComputer.setOnAction(event -> {
            computer = true;
            resetRound(buttons);
            resetGame();
            stage.hide();
        });

        Button playerVsPlayer = new Button("Player vs Player");
        playerVsPlayer.setPrefSize(200,25);
        playerVsPlayer.setOnAction(event -> {
            computer = false;
            resetRound(buttons);
            resetGame();
            stage.hide();
        });

        Button close = new Button("Close");
        close.setPrefSize(200,25);
        close.setOnAction(event -> {
            stage.hide();
        });

        VBox vbox = new VBox(dialog,playerVsComputer,playerVsPlayer,close);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        stage.setScene(new Scene(vbox));
        stage.showAndWait();
    }

    public void game(Board[] buttons){
        for(Board button: buttons){
            button.setOnAction(event -> {
                if(move == 9){
                    resetRound(buttons);
                } if(crossWin || circleWin){
                    crossWin = false;
                    circleWin = false;
                    resetRound(buttons);
                } if(play){
                    firstPlayer(button,buttons);
                } if(!play && !crossWin){
                    if(!computer){
                        secondPlayer(button,buttons);
                    } else {
                        computer(buttons);
                    }
                }
            });
        }
    }



    public void firstPlayer(Board button,Board[] buttons){
        if(move < maxMoves){
            if(button.getGraphic() == null){
                if(play){
                    button.setState(player);
                    play = false;
                    move++;
                    checkWinner(buttons);
                    alertRound();
                } else {
                    play = true;
                }
            }
        }
    }

    public void secondPlayer(Board button,Board[] buttons){
        if(move < maxMoves){
            if(button.getGraphic() == null){
                if(!play){
                    button.setState(cpu);
                    play = false;
                    move++;
                    checkWinner(buttons);
                    alertRound();
                } else {
                    play = true;
                }
            }
        }
    }

    public void computer(Board[] buttons){
        if(move < maxMoves){
            if(!play){
                int computerRandom;
                do {
                    computerRandom = random.nextInt(maxButtons);
                } while(buttons[computerRandom].getGraphic() != null);
                buttons[computerRandom].setState(cpu);
                play = true;
                move++;
                checkWinner(buttons);
                alertRound();
            }
        }
    }

    public void checkWinner(Board[] buttons){
        //Horizontal
        if(buttons[0].getValue() == player && buttons[1].getValue() == player && buttons[2].getValue() == player){
            crossWin = true;
        }
        if(buttons[0].getValue() == cpu && buttons[1].getValue() == cpu && buttons[2].getValue() == cpu){
            circleWin = true;
        }
        if(buttons[3].getValue() == player && buttons[4].getValue() == player && buttons[5].getValue() == player){
            crossWin = true;
        }
        if(buttons[3].getValue() == cpu && buttons[4].getValue() == cpu && buttons[5].getValue() == cpu){
            circleWin = true;
        }
        if(buttons[6].getValue() == player && buttons[7].getValue() == player && buttons[8].getValue() == player){
            crossWin = true;
        }
        if(buttons[6].getValue() == cpu && buttons[7].getValue() == cpu && buttons[8].getValue() == cpu){
            circleWin = true;
        }

        //Vertical
        if(buttons[0].getValue() == player && buttons[3].getValue() == player && buttons[6].getValue() == player){
            crossWin = true;
        }
        if(buttons[0].getValue() == cpu && buttons[3].getValue() == cpu && buttons[6].getValue() == cpu){
            circleWin = true;
        }
        if(buttons[1].getValue() == player && buttons[4].getValue() == player && buttons[7].getValue() == player){
            crossWin = true;
        }
        if(buttons[1].getValue() == cpu && buttons[4].getValue() == cpu && buttons[7].getValue() == cpu){
            circleWin = true;
        }
        if(buttons[2].getValue() == player && buttons[5].getValue() == player && buttons[8].getValue() == player){
            crossWin = true;
        }
        if(buttons[2].getValue() == cpu && buttons[5].getValue() == cpu && buttons[8].getValue() == cpu){
            circleWin = true;
        }

        //Diagonal
        if(buttons[0].getValue() == player && buttons[4].getValue() == player && buttons[8].getValue() == player){
            crossWin = true;
        }
        if(buttons[0].getValue() == cpu && buttons[4].getValue() == cpu && buttons[8].getValue() == cpu){
            circleWin = true;
        }

        if(buttons[2].getValue() == player && buttons[4].getValue() == player && buttons[6].getValue() == player){
            crossWin = true;
        }
        if(buttons[2].getValue() == cpu && buttons[4].getValue() == cpu && buttons[6].getValue() == cpu){
            circleWin = true;
        }
    }

    public void alertRound(){

        if(move == 9 && !crossWin && !circleWin){
            alert.setTitle("Tie round");
            alert.setHeaderText(null);
            alert.setContentText("~~~~~~ Tie ~~~~~~\n This time is no winner!");
            alert.showAndWait();
        }

        if(crossWin){
            alert.setTitle("Winner is Cross");
            alert.setHeaderText(null);
            alert.setContentText("~~~~~~~ Round ~~~~~~~\nThis time round won the Cross !");
            alert.showAndWait();
            playerWin++;
            playerPoint.setValue(playerWin);

            if(computer){
                win++;
            } else {
                win = 0;
            }
            winner.setValue(win);
        }

        if(circleWin){
            alert.setTitle("Winner is Cross");
            alert.setHeaderText(null);
            alert.setContentText("~~~~~~~ Round ~~~~~~~\nThis time round won the Circle !");
            alert.showAndWait();

            computerWin++;
            computerPoint.setValue(computerWin);

            win = 0;
            winner.setValue(win);
        }
    }

    public void resetGame(){
        playerWin = 0;
        computerWin = 0;
        win = 0;
        computerPoint.setValue(computerWin);
        playerPoint.setValue(playerWin);
        winner.setValue(player);
    }

    public void resetRound(Board[] buttons){
        for(Board button: buttons){
            button.setState(0);
        }
        move = 0;
    }

    public void saveGame(){

        SaveGame saveGame = new SaveGame();
        saveGame.playerWin = playerWin;
        saveGame.computerWin = computerWin;
        saveGame.savePlayerTurn = play;
        saveGame.gameplaySelection = computer;
        saveGame.win = win;


        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save));
            oos.writeObject(saveGame);
            oos.close();

            alert.setTitle("Save Game");
            alert.setHeaderText(null);
            alert.setContentText("~~~~~~ Success ~~~~~~\nThe current game has been saved");
            alert.showAndWait();
        } catch(Exception e){

            alert.setTitle("Error Save Game");
            alert.setHeaderText(null);
            alert.setContentText("Error: This game cannon`t be saved\nError: " + e.getMessage());
            alert.showAndWait();
        }
    }

    public void loadGame(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(save));
            SaveGame saveGame = (SaveGame) ois.readObject();
            playerWin = saveGame.playerWin;
            computerWin = saveGame.computerWin;
            play = saveGame.savePlayerTurn;
            computer = saveGame.gameplaySelection;
            win = saveGame.win;

            ois.close();

            playerPoint.setValue(playerWin);
            computerPoint.setValue(computerWin);
            winner.setValue(win);

            alert.setTitle("Load Game");
            alert.setHeaderText(null);
            alert.setContentText("~~~~~~ Success ~~~~~~\nStart new round.\nLast game has been loaded.");
            alert.showAndWait();

        } catch(Exception e){
            alert.setTitle("Error Loaded");
            alert.setHeaderText(null);
            alert.setContentText("Error: This game cannon`t be loaded\nError:" + e.getMessage());
            alert.showAndWait();
        }
    }
}


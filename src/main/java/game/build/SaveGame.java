package game.build;

import java.io.Serializable;

public class SaveGame implements Serializable{
    private static final long serialVersionUID = 0L;

    public int playerWin;
    public int computerWin;
    public boolean savePlayerTurn;
    public boolean gameplaySelection;
    public int win;

}
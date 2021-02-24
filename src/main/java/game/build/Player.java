package game.build;

import java.io.Serializable;

public class Player implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private int winner;

    public Player(String name, int winner) {
        this.name = name;
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }

    public String getName() {
        return name;
    }
}

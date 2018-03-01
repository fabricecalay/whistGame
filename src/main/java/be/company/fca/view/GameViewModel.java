package be.company.fca.view;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

public class GameViewModel  {

    private String name="Whist Game";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Command(value = "newGame")
    public void doNewGame() {
        // Start new game

        System.err.println("New game....");

    }
}

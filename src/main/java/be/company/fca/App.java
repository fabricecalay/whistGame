package be.company.fca;

import be.company.fca.model.Card;
import be.company.fca.model.Game;
import be.company.fca.model.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * First class of Whist Game
 * Created by fca on 18-09-17.
 */
public class App extends Application {

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    @Override
    public void start(Stage primaryStage) {

        Game game = new Game();
        game.initGame();
        game.chooseContract();

        // Affichage des decks

        primaryStage.setTitle("App");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 300, 250, Color.GRAY);

        int i = 0;
        for (Player player : game.getPlayers()){
            int j = 0;
            root.add(new Label(player.getNickname()),i,j);
            j++;
            for (Card card : player.getPlayerDeck()){
                Button btn = new Button();
                btn.setText(card.getDisplayLabel());
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println(card.toString());
                    }
                });
                root.add(btn,i,j);
                j++;
            }
            i++;
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void maisn(String[] args) throws Exception{

//        game.showPlayersDeck();

//        game.launchCardGame();
    }
}

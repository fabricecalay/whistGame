package be.company.fca;

import be.company.fca.model.Card;
import be.company.fca.model.Fold;
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

    private Game game;

    @Override
    public void start(Stage primaryStage) {

        game = new Game();
        game.initGame();
        game.sortPlayersDeck();
        game.chooseContract();

        // Affichage des decks

        primaryStage.setTitle("App");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, Color.GRAY);

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

                        // TODO : verifier qu'il s'agit bien du bon joueur qui joue une carte

                        Player nextPlayer = game.getNextPlayerToPlay();
                        if (player.equals(nextPlayer)){
                            boolean validCard = player.isCardValid(game.getCurrentFold(),card);
                            if (validCard){
                                System.err.println("Carte jouee par " + player.getNickname() + " : " + card.getDisplayLabel());
                                game.getCurrentFold().addCardToFold(game.getContract(),player, card);
                                root.getChildren().remove(btn);
                            }else{
                                //System.err.println("Carte non-autorisée");
                            }
                        }else{
                            //System.err.println("Joueur non-autorisé");
                        }

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

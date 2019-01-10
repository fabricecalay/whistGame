package be.company.fca;

import be.company.fca.model.Card;
import be.company.fca.model.Fold;
import be.company.fca.model.Game;
import be.company.fca.model.Player;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


/**
 * First class of Whist Game
 * Created by fca on 18-09-17.
 */
public class App extends Application {

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    private Game game;
    private GridPane centralZone;

    @Override
    public void start(Stage primaryStage) {

        game = new Game();
        game.initGame();
        game.sortPlayersDeck();
        game.chooseContract();

        primaryStage.setTitle("App");

        BorderPane globalPane = new BorderPane();
        HBox bottomHbox = new HBox();
        Label bottomLabel = new Label(game.getPlayers().get(0).getNickname());
        bottomHbox.getChildren().add(bottomLabel);
        bottomHbox.setAlignment(Pos.CENTER);
        globalPane.setBottom(bottomHbox);

        HBox leftHbox = new HBox();
        Label leftLabel = new Label(game.getPlayers().get(1).getNickname());
        leftLabel.setRotate(270.0);
        leftHbox.getChildren().add(leftLabel);
        leftHbox.setAlignment(Pos.CENTER);
        globalPane.setLeft(leftHbox);

        HBox topHbox = new HBox();
        Label topLabel = new Label(game.getPlayers().get(2).getNickname());
        topLabel.setRotate(180.0);
        topHbox.getChildren().add(topLabel);
        topHbox.setAlignment(Pos.CENTER);
        globalPane.setTop(topHbox);

        HBox rightHbox = new HBox();
        Label rightLabel = new Label(game.getPlayers().get(3).getNickname());
        rightLabel.setRotate(90.0);
        rightHbox.getChildren().add(rightLabel);
        rightHbox.setAlignment(Pos.CENTER);
        globalPane.setRight(rightHbox);

        // Affichage des decks

        BorderPane border = new BorderPane();
        globalPane.setCenter(border);

        Map<Player, Pane> paneMap = new HashMap<>();
        HBox bottom = new HBox();
        bottom.setPadding(new Insets(5, 15, 5, 15));
        bottom.setAlignment(Pos.CENTER);
        border.setBottom(bottom);
        paneMap.put(game.getPlayers().get(0),bottom);

        VBox left = new VBox();
        left.setPadding(new Insets(15, 5, 15, 5));
        left.setAlignment(Pos.CENTER);
        border.setLeft(left);
        paneMap.put(game.getPlayers().get(1),left);

        HBox top = new HBox();
        top.setPadding(new Insets(5, 15, 5, 15));
        top.setAlignment(Pos.CENTER);
        border.setTop(top);
        paneMap.put(game.getPlayers().get(2),top);

        VBox right = new VBox();
        right.setPadding(new Insets(15, 5, 15, 5));
        right.setAlignment(Pos.CENTER);
        border.setRight(right);
        paneMap.put(game.getPlayers().get(3),right);

        // Zone de jeu centrale

        centralZone = new GridPane();
        centralZone.setPadding(new Insets(15, 15, 15, 15));
        centralZone.setAlignment(Pos.CENTER);

        border.setCenter(centralZone);


        int i = 0;
        for (Player player : game.getPlayers()){
            int j = 0;
//            root.add(new Label(player.getNickname()),i,j);
            j++;
            for (Card card : player.getPlayerDeck()){
                Button btn = new Button();
                btn.setText(card.getDisplayLabel());
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        // Verifier qu'il s'agit bien du bon joueur qui joue une carte

                        Player nextPlayer = game.getNextPlayerToPlay();
                        if (player.equals(nextPlayer)){
                            boolean validCard = player.isCardValid(game.getCurrentFold(),card);
                            if (validCard){
                                System.err.println("Carte jouee par " + player.getNickname() + " : " + card.getDisplayLabel());
                                game.getCurrentFold().addCardToFold(game.getContract(),player, card);
                                paneMap.get(player).getChildren().remove(btn);
                                playCard(player,card,game.getCurrentFold().getPlayedCards().size()==1);
                            }else{
                                //System.err.println("Carte non-autorisée");
                            }
                        }else{
                            //System.err.println("Joueur non-autorisé");
                        }

                    }
                });
                paneMap.get(player).getChildren().add(btn);
                j++;
            }
            i++;
        }


        Scene scene = new Scene(globalPane, Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void playCard(Player player, Card card, boolean newFold){
        if (newFold){
            centralZone.getChildren().clear();
        }
        int columnIndex = 0;
        int rowIndex = 0;

        switch (game.getPlayers().indexOf(player)){
            case 0 :
                columnIndex = 1;
                rowIndex = 2;
                break;
            case 1 :
                columnIndex = 0;
                rowIndex = 1;
                break;
            case 2 :
                columnIndex = 1;
                rowIndex = 0;
                break;
            case 3 :
                columnIndex = 2;
                rowIndex = 1;
                break;
            default : break;
        }
        centralZone.add(new Label(card.getDisplayLabel()),columnIndex,rowIndex);
    }

}

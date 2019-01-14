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
    private Map<Player,Integer> positionMap=new HashMap<>();
    private Map<Integer, Pane> paneMap = new HashMap<>();
    private GridPane centralZone;
    private Label bottomPlayerLabel=new Label();
    private Label leftPlayerLabel=new Label();
    private Label topPlayerLabel=new Label();
    private Label rightPlayerLabel=new Label();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("App");
        primaryStage.setMinWidth(500.0);
        primaryStage.setMinHeight(500.0);

        BorderPane globalPane = new BorderPane();
        HBox bottomHbox = new HBox();
        bottomHbox.getChildren().add(bottomPlayerLabel);
        bottomHbox.setAlignment(Pos.CENTER);
        globalPane.setBottom(bottomHbox);

        HBox leftHbox = new HBox();
        leftPlayerLabel.setRotate(270.0);
        leftHbox.getChildren().add(leftPlayerLabel);
        leftHbox.setAlignment(Pos.CENTER);
        globalPane.setLeft(leftHbox);

        HBox topHbox = new HBox();
        topPlayerLabel.setRotate(180.0);
        topHbox.getChildren().add(topPlayerLabel);
        topHbox.setAlignment(Pos.CENTER);
        globalPane.setTop(topHbox);

        HBox rightHbox = new HBox();
        rightPlayerLabel.setRotate(90.0);
        rightHbox.getChildren().add(rightPlayerLabel);
        rightHbox.setAlignment(Pos.CENTER);
        globalPane.setRight(rightHbox);

        // Affichage des decks

        BorderPane border = new BorderPane();
        globalPane.setCenter(border);

        HBox bottom = new HBox();
        bottom.setPadding(new Insets(5, 15, 5, 15));
        bottom.setAlignment(Pos.CENTER);
        border.setBottom(bottom);
        paneMap.put(0,bottom);

        VBox left = new VBox();
        left.setPadding(new Insets(15, 5, 15, 5));
        left.setAlignment(Pos.CENTER);
        border.setLeft(left);
        paneMap.put(1,left);

        HBox top = new HBox();
        top.setPadding(new Insets(5, 15, 5, 15));
        top.setAlignment(Pos.CENTER);
        border.setTop(top);
        paneMap.put(2,top);

        VBox right = new VBox();
        right.setPadding(new Insets(15, 5, 15, 5));
        right.setAlignment(Pos.CENTER);
        border.setRight(right);
        paneMap.put(3,right);

        // Zone de jeu centrale

        centralZone = new GridPane();
        centralZone.setPadding(new Insets(15, 15, 15, 15));
        centralZone.setAlignment(Pos.CENTER);

        border.setCenter(centralZone);

        newGame();

        Scene scene = new Scene(globalPane, Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void newGame(){

        for (Pane pane : paneMap.values()){
            pane.getChildren().clear();
            pane.setDisable(false);
        }
        centralZone.getChildren().clear();

        game = new Game();
        game.initGame();

        positionMap.clear();
        initPlayersNameAndPosition();

        game.sortPlayersDeck();
        game.chooseContract();

        drawGameBoard();
    }

    /**
     * Permet d'afficher le nom des joueurs
     */
    public void initPlayersNameAndPosition(){
        bottomPlayerLabel.setText(game.getPlayers().get(0).getNickname());
        positionMap.put(game.getPlayers().get(0),0);
        leftPlayerLabel.setText(game.getPlayers().get(1).getNickname());
        positionMap.put(game.getPlayers().get(1),1);
        topPlayerLabel.setText(game.getPlayers().get(2).getNickname());
        positionMap.put(game.getPlayers().get(2),2);
        rightPlayerLabel.setText(game.getPlayers().get(3).getNickname());
        positionMap.put(game.getPlayers().get(3),3);


    }

    public void drawGameBoard(){

        int i = 0;
        for (Player player : game.getPlayers()){
            int j = 0;
            j++;
            for (Card card : player.getPlayerDeck()){
                Button btn = new Button();
                btn.setText(card.getDisplayLabel());
                btn.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {

                        // Verifier qu'il s'agit bien du bon joueur qui joue une carte

                        Player nextPlayer = game.getNextPlayerToPlay();
                        if (player.equals(nextPlayer)){

                            Fold foldToPlay = game.getFoldToPlay();

                            boolean validCard = player.isCardValid(foldToPlay,card);
                            if (validCard){
                                boolean carteJouee = foldToPlay.addCardToFold(game.getContract(),player, card);
                                if (carteJouee){
                                    paneMap.get(positionMap.get(player)).getChildren().remove(btn);
                                    playCard(player,card,foldToPlay.getPlayedCards().size()==1);
                                    if (game.isFinished()){
                                        Button newGameButton = new Button("New Game");
                                        centralZone.add(newGameButton,3,1);
                                        for (Pane pane : paneMap.values()){
                                            pane.setDisable(true);
                                        }
                                        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                newGame();
                                            }
                                        });
                                    }
                                }
                            }else{
                                //System.err.println("Carte non-autorisée");
                            }
                        }else{
                            //System.err.println("Joueur non-autorisé");
                        }

                    }
                });
                paneMap.get(positionMap.get(player)).getChildren().add(btn);
                j++;
            }
            i++;
        }
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

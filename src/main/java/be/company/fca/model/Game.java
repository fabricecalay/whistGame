package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private Deck gameDeck = new Deck();

    private Player[] players = new Player[4];

    private GameType gameType;

    public void showDeck(){
        // Show the gameDeck

        for (Card card : gameDeck){
            System.out.println(card);
        }

    }

    private void mixDeck(){
        Collections.shuffle(gameDeck);
    }

    private void initPlayers(){
        for (int i=0;i<4;i++){
            players[i]=new Player();
        }
    }

    public void initGame(){
        initPlayers();
        mixDeck();
        distribution();
    }

    public void distribution(){
        if (gameDeck.isEmpty()){
            throw new RuntimeException("You must create a gameDeck before distribution");
        }

        for (int i = 0; i<players.length;i++){
            for (int j = 0; j< gameDeck.size()/players.length; j++){
                players[i].getPlayerDeck().add(gameDeck.get(j + (i*gameDeck.size()/players.length)));
            }
        }
    }

    public void showPlayersDeck(){
        for (int i = 0; i<players.length;i++){
            players[i].sortDeck();
            System.out.println("Player " + (i+1)  + " : ");
            for (Card card : players[i].getPlayerDeck()){
                System.out.println(card);
            }
        }
    }

}

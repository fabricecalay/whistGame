package be.company.fca.model;

import java.util.Collections;

public class Game {

    private Deck gameDeck = new Deck();

    private Player[] players = new Player[4];

    private Contract contract;

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

    public void launchGame(){

        // Chaque joueur joue a son tour et vide progressivement son deck

        for (int i=0;i<players.length;i++){
            players[i].playCard(this);
        }

        // REGLE STRICTE Connaitre la premiere carte car on est oblige de suivre
        // Connaitre les cartes jouees
        // Connaitre le contrat en cours --> l'atout egalement pour savoir qui gagne le pli
        // REGLE STRICTE Savoir si la carte jouee est valable

    }

}

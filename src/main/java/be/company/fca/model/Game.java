package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private Deck gameDeck = new Deck();
    private List<Fold> folds = new ArrayList<>();

    private Player[] players = new Player[Constants.NUMBER_OF_PLAYERS];

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
        for (int i=0;i<Constants.NUMBER_OF_PLAYERS;i++){
            players[i]=new Player("Joueur " + (i+1));
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
            Player player = players[i];
            player.sortDeck();
            System.out.println(player);
            for (Card card : player.getPlayerDeck()){
                System.out.println(card);
            }
        }
    }

    /**
     * Phase de selection du contrat qui va etre joue
     */
    public void chooseContract(){
        contract = new Contract();
        //TODO : selection d'un atout --> analyse des plis gagnants
    }

    /**
     * Phase du jeu de la carte
     */
    public void launchCardGame(){

        // Chaque joueur joue a son tour et vide progressivement son deck

        int numberOfFolds = players[0].getPlayerDeck().size();

        for (int i=0;i<numberOfFolds;i++){
            Fold fold = new Fold();
            folds.add(fold);
            for (int j=0;j<players.length;j++){
                Player player = players[j];
                Card playedCard = player.playCard(this);
                fold.addCardToFold(player,playedCard);
            }
            // On analyse qui gagne le pli
            Player winningPlayer = fold.getWinningPlayer(contract);
            // TODO : selection de l'index de depart + utilisation du modulo pour faire jouer les quatre joueurs

            System.out.println(fold);
            System.out.println("Winning player : " + winningPlayer);

        }

        System.out.println("End of game");

        // REGLE STRICTE Connaitre la premiere carte car on est oblige de suivre
        // Connaitre les cartes jouees
        // Connaitre le contrat en cours --> l'atout egalement pour savoir qui gagne le pli
        // REGLE STRICTE Savoir si la carte jouee est valable
        // Apres le pli, savoir qui va jouer en premier lors du tour suivant

    }

    public List<Fold> getFolds() {
        return folds;
    }

    public Contract getContract() {
        return contract;
    }
}

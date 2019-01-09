package be.company.fca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private Deck gameDeck = new Deck();
    private List<Fold> folds = new ArrayList<>();

    private List<Player> players = new ArrayList<Player>();

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
            players.add(new Player("Joueur " + (i+1)));
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

        for (int i = 0; i<players.size();i++){
            for (int j = 0; j< gameDeck.size()/players.size(); j++){
                players.get(i).getPlayerDeck().add(gameDeck.get(j + (i*gameDeck.size()/players.size())));
            }
        }
    }

    public void showPlayersDeck(){
        for (int i = 0; i<players.size();i++){
            Player player = players.get(i);
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
        //TODO : selection d'un contract (avec atout ou sans atout, trou, misere, ...)
    }

    /**
     * Phase du jeu de la carte
     */
    public void launchCardGame(){

        // Chaque joueur joue a son tour et vide progressivement son deck

        int numberOfFolds = players.get(0).getPlayerDeck().size();
        int winningIndex = 0;

        for (int i=0;i<numberOfFolds;i++){
            Fold fold = new Fold();
            folds.add(fold);
            for (int j=0;j<players.size();j++){
                Player player = players.get((winningIndex + j) % 4);
                Card playedCard = player.playCard(this);
                fold.addCardToFold(player,playedCard);
            }
            // On analyse qui gagne le pli
            Player winningPlayer = fold.getWinningPlayer(contract);
            // Selection de l'index du joueur gagnant le pli pour determiner l'ordre de jeu du tour suivant
            winningIndex = players.indexOf(winningPlayer);
//            System.out.println(fold);
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

    /**
     * Permet de recuperer le dernier pli complet
     * @return
     */
    public Fold getLastCompletedFold(){
        if (folds.size()>0){
            for (int i=0;i<folds.size();i++){
                Fold fold = folds.get(folds.size()-i-1);
                if (fold.getPlayedCards().size()==4){
                    return fold;
                }
            }
        }
        return null;
    }

    public Fold getCurrentFold(){
        if (folds.size()>0){
            Fold fold = folds.get(folds.size() - 1);
            if (fold.getPlayedCards().size()<4){
                return fold;
            }
        }
        Fold newFold =  new Fold();
        this.folds.add(newFold);
        return newFold;
    }

    public Contract getContract() {
        return contract;
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Permet de recuperer le prochain joueur qui doit jouer
     * @return
     */
    public Player getNextPlayerToPlay(){
        Fold currentFold = getCurrentFold();
        if (currentFold.isEmpty()){
            Fold lastCompletedFold = getLastCompletedFold();
            if (lastCompletedFold!=null){
                return lastCompletedFold.getWinningPlayer(this.contract);
            }else{
                // TODO : Premier pli de la partie --> en fonction du donneur
                return players.get(0);
            }
        }else{
            PlayedCard lastPlayedCard = currentFold.getPlayedCards().get(currentFold.getPlayedCards().size()-1);
            int lastPlayerIndex = players.indexOf(lastPlayedCard.getPlayer());
            return players.get((lastPlayerIndex+1)%4);
        }
    }
}

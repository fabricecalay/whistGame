package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private String nickname;
    private List<Card> playerDeck = new ArrayList<>();

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public List<Card> getPlayerDeck() {
        return playerDeck;
    }

    /**
     * Permet de trier les cartes de la plus faible à la plus forte
     */
    public void sortDeck(){
        Collections.sort(playerDeck);
    }

    /**
     * Permet de jouer une carte
     */
    public Card playCard(Game game){

        // TODO : Etablir la strategie du joueur
        // TODO : tout en garantissant le coup valable --> avoir une methode pour verifier la validite de la carte jouee

        int indexOfCard = 0;

        // On regarde la premiere carte jouee dans le pli en cours
        List<Fold> folds = game.getFolds();
        Fold currentFold = folds.get(folds.size()-1);

        if (currentFold.isEmpty()){
            // Le joueur peut jouer ce qu'il veut
        }else{
            // Le joueur doit s'il en possede jouer le meme type que le premier joueur (obligation de suivre)
            PlayedCard firstCardOfFold = currentFold.getPlayedCards().get(0);
            Card.CardType cardType = firstCardOfFold.getCard().getCardType();
            for (int i=0;i<playerDeck.size();i++){
                // S'il n'a plus de carte de meme type, il jouera la premiere carte de son jeu
                if (cardType.equals(playerDeck.get(i).getCardType())){
                    indexOfCard = i;
                }
            }
        }
        return playerDeck.remove(indexOfCard);
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}

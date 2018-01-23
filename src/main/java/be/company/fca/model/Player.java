package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    //TODO : pour le joueur humain, proposer une aide au joueur ("meilleure carte")

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
        int indexOfCard = getCardIndex(game);
        return playerDeck.remove(indexOfCard);
    }

    /**
     * Permet de recuperer le type de carte valable pour ce pli (premiere carte jouee)
     * @param game Jeu en cours
     * @return Type de carte jouee en premier, null si aucune carte jouee
     */
    private Card.CardType getValidCardType(Game game) {

        // On regarde la premiere carte jouee dans le pli en cours
        List<Fold> folds = game.getFolds();
        Fold currentFold = folds.get(folds.size() - 1);

        // Si une carte a deja ete jouee, la premiere definit le type de carte valable pour ce tour de jeu
        // Si le joueur en a une, il est obligée de la jouer
        Card.CardType validCardType = null;
        if (!currentFold.isEmpty()) {
            PlayedCard firstCardOfFold = currentFold.getPlayedCards().get(0);
            validCardType = firstCardOfFold.getCard().getCardType();
        }

        return validCardType;
    }

        /**
         * Determination de la carte a jouer
         * @return
         */
    private int getCardIndex(Game game){

        // TODO : Etablir la strategie du joueur
        // TODO : Tout en garantissant le coup valable --> avoir une methode pour verifier la validite de la carte jouee

        Card.CardType validCardType = getValidCardType(game);

        // TODO : Strategies possibles : toujours la plus forte, toujours la plus faible juste au dessus pour gagner, fonction du partenaire et de la position du joueur a la table

        int indexOfCard = 0;
        if (validCardType!=null){
            for (int i=0;i<playerDeck.size();i++){
                // S'il n'a plus de carte de meme type, il jouera la premiere carte de son jeu
                if (validCardType.equals(playerDeck.get(i).getCardType())){
                    indexOfCard = i;
                }
            }
        }
        return indexOfCard;
    }


    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}

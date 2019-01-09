package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    //TODO : pour le joueur humain, proposer une aide au joueur ("meilleure carte")

    private Integer id;
    private String name;
    private String nickname;
    private List<Card> playerDeck = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPlayerDeck(List<Card> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public Player() {
    }

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
        Card cardToPlay = getCardToPlay(game);
        playerDeck.remove(cardToPlay);
        System.err.println("Carte jouée par joueur "  + this.nickname + " : " + cardToPlay);
        return cardToPlay;
    }

    /**
     * Permet de recuperer le type de carte valable pour ce pli (premiere carte jouee)
     * @param game Jeu en cours
     * @return Type de carte jouee en premier, null si aucune carte jouee
     */
    private Card.CardType getValidCardType(Game game) {

        // On regarde la premiere carte jouee dans le pli en cours
        Fold currentFold = game.getCurrentFold();

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
     * Permet de verifier la validite d'une carte qu'un joueur souhaite jouer
     * @param game
     * @param card
     * @return true si la carte est valable
     */
    public boolean isCardValid(Game game, Card card){
        Card.CardType validCardType = getValidCardType(game);
        if (validCardType!=null) {
            if (!validCardType.equals(card.getCardType())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determination de la carte a jouer
     * @return
     */
    private Card getCardToPlay(Game game){

        // TODO : Etablir la strategie du joueur
        // TODO : si joueur humain, prevoir un timer pour jouer la carte pour pallier aux departs + possibilite d'etendre ce delai si le joueur reflechi

        if (playerDeck.isEmpty()){
            throw new RuntimeException("Player's deck is empty");
        }

        // On recupere le type de carte valable (premiere carte jouee)

        Card.CardType validCardType = getValidCardType(game);

        // S'il y a un type de carte defini,

        if (validCardType!=null){

            // On va regarder si on a des cartes de ce type --> on sera oblige d'en jouer
            List<Card> validCards = new ArrayList<>();

            if (validCardType!=null){
                for (Card card : playerDeck){
                    if (validCardType.equals(card.getCardType())){
                        validCards.add(card);
                    }
                }
            }

            if (validCards.isEmpty()){
                // TODO : s'il y a un atout, determiner si je vais couper (ou pas)

                // TODO : si le partenaire gagne le pli, on peut rechercher apres un renom par exemple

                // TODO : S'il n'y a pas ou que je n'ai plus d'atout, determiner la carte a ecarter

                return playerDeck.get(0);
            }else{

                // Obligation de jouer une carte du meme type

                boolean bestCard = true; // Carte la plus forte
                boolean betterSmallCard = true; // Carte superieure la plus faible
                boolean smallestCard = true; // Carte la plus faible
                boolean lessGoodBestCard = true; // Carte inferieure la plus forte - Utile pour les miseres par exemple

                //TODO : quand deux cartes se succedent (ou se valent selon ce qu'il reste en jeu), on peut jouer l'une ou l'autre --> influence le jeu du partenaire et/ou de l'adversaire

                Collections.sort(validCards);
                if (bestCard){
                    return validCards.get(validCards.size()-1);
                }
                if (smallestCard){
                    return validCards.get(0);
                }
                if (betterSmallCard){
                    // Analyser les cartes deja jouees dans le pli et determiner laquelle l'emporte
                    // Analyser les cartes deja jouees dans le jeu et mettre la carte qui est sûr de l'emporter.
                    // Si on suppose que l'adversaire qui nous précède a une meilleure carte, on peut faire l'impasse

                    Fold currentFold = game.getCurrentFold();
                    

                }

                //TODO : il y a encore la possibilite de jouer plus finement en ne forcant pas le joueur a mettre la plus forte


            }

        }else{

            // TODO : Je dois determiner la carte a jouer comme premier joueur
            return playerDeck.get(0);
        }
        //

        // TODO : Strategies possibles : toujours la plus forte, toujours la plus faible juste au dessus pour gagner
        // TODO : Fonction du partenaire et de la position du joueur a la table

        int indexOfCard = 0;
//        return indexOfCard;
        return null;
    }


    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}

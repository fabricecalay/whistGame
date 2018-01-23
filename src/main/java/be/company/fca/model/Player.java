package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private List<Card> playerDeck = new ArrayList<>();

    public List<Card> getPlayerDeck() {
        return playerDeck;
    }

    /**
     * Permet de trier les cartes de la plus faible à la plus forte
     */
    public void sortDeck(){
        Collections.sort(playerDeck,Card.getCardComparator());
    }

}

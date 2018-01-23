package be.company.fca.model;

import java.util.ArrayList;

public class Deck extends ArrayList<Card> {

    public Deck() {
        // Deck creation
        for (Card.CardType cardType : Card.CardType.values()){
            for (Card.CardValue cardValue : Card.CardValue.values()){
                add(new Card(cardType,cardValue));
            }
        }
    }

}

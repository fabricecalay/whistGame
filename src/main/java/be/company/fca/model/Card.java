package be.company.fca.model;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private CardType cardType;
    private CardValue cardValue;

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    enum CardValue {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }

    enum CardType {
        SPADE,
        CLUB,
        DIAMOND,
        HEART
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardType=" + cardType +
                ", cardValue=" + cardValue +
                '}';
    }

    public static void main(String[] args){

        // Deck creation

        List<Card> deck = new ArrayList<Card>();
        for (CardType cardType : CardType.values()){
            for (CardValue cardValue : CardValue.values()){
                deck.add(new Card(cardType,cardValue));
            }
        }

        // Mix the deck

        // Show the deck

        for (Card card : deck){
            System.out.println(card);
        }

    }


}

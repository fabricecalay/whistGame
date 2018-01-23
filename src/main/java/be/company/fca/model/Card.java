package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Card {

    private CardType cardType;
    private CardValue cardValue;

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    enum CardValue {
        TWO(1),
        THREE(2),
        FOUR(3),
        FIVE(4),
        SIX(5),
        SEVEN(6),
        EIGHT(7),
        NINE(8),
        TEN(9),
        JACK(10),
        QUEEN(11),
        KING(12),
        ACE(13);

        private Integer value;
        CardValue(int value) {
            this.value = value;
        }
    }

    enum CardType {
        SPADE(1),
        CLUB(2),
        DIAMOND(3),
        HEART(4);

        private Integer value;
        CardType(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            switch (this){
                case SPADE:return "SPADE " + Character.toString((char)'\u2660');
                case CLUB:return "CLUB " + Character.toString((char)'\u2663');
                case DIAMOND:return "DIAMOND" + Character.toString((char)'\u2666');
                case HEART:return "HEART " + Character.toString((char)'\u2764');
                default:return "";
            }
        }
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardValue=" + cardValue +
                ", cardType=" + cardType +
                '}';
    }

    public static Comparator<Card> getCardComparator(){
        return new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                if (card1.cardType.value<card2.cardType.value){
                    return -1;
                }else if (card1.cardType.value>card2.cardType.value){
                    return 1;
                }else{
                    return card1.cardValue.compareTo(card2.cardValue);
                }
            }
        };
    }

}

package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Card implements Comparable<Card> {

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
                case DIAMOND:return "DIAMOND " + Character.toString((char)'\u2666');
                case HEART:return "HEART " + Character.toString((char)'\u2764');
                default:return "";
            }
        }
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public String getDisplayLabel(){
        return cardValue.value + " " + cardType.toString();
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardValue=" + cardValue +
                ", cardType=" + cardType +
                '}';
    }

    @Override
    public int compareTo(Card card) {
        if (this.cardType.value<card.cardType.value){
            return -1;
        }else if (this.cardType.value>card.cardType.value){
            return 1;
        }else{
            return this.cardValue.compareTo(card.cardValue);
        }
    }
}

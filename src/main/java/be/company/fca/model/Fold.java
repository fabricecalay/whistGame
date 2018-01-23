package be.company.fca.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fold {
    List<PlayedCard> playedCards = new ArrayList<PlayedCard>();

    public List<PlayedCard> getPlayedCards() {
        return playedCards;
    }

    public void addCardToFold(Player player, Card card){
        playedCards.add(new PlayedCard(player,card));
    }

    public boolean isEmpty(){
        return getPlayedCards().isEmpty();
    }

    public Player getWinningPlayer(Contract contract){

        if (playedCards.isEmpty()){
            throw new RuntimeException("Fold is empty");
        }

        // Le type de carte
        Card.CardType firstCardType = playedCards.get(0).getCard().getCardType();

        // S'il y a un atout dans le contrat et s'il y en a au moins un dans le pli, on prend l'atout le plus fort

        List<Card> trumpCards = new ArrayList<>();
        List<Card> cardsOfFirstType = new ArrayList<>();

        for (PlayedCard playedCard : playedCards){
            if (playedCard.getCard().getCardType().equals(firstCardType)){
                cardsOfFirstType.add(playedCard.getCard());
            }
            if (playedCard.getCard().getCardType().equals(contract.getTrump())){
                trumpCards.add(playedCard.getCard());
            }

        }

        Card winningCard = null;

        // S'il y a des atouts joues, on prend le plus fort
        if (!trumpCards.isEmpty()){
            Collections.sort(trumpCards);
            winningCard = trumpCards.get(trumpCards.size()-1);
        }else{
            // sinon, on prend la carte la plus forte parmi celles correspond au type de carte joue par le joueur ayant initie le pli
            Collections.sort(cardsOfFirstType);
            winningCard = cardsOfFirstType.get(cardsOfFirstType.size()-1);
        }

        for (PlayedCard playedCard : playedCards){
            if (playedCard.getCard().equals(winningCard)){
                return playedCard.getPlayer();
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Fold{" +
                "playedCards=" + playedCards +
                '}';
    }
}

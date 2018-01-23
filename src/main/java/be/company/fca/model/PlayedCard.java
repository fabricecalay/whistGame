package be.company.fca.model;

/**
 * Classe representant un pli
 */
public class PlayedCard {

    private Player player;
    private Card card;

    public PlayedCard(Player player, Card card) {
        this.player = player;
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "PlayedCard{" +
                "player=" + player +
                ", card=" + card +
                '}';
    }
}

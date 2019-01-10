package be.company.fca.model;

import java.util.ArrayList;
import java.util.List;

public class Contract {

    //TODO : diagramme d'etat pour savoir vers quel contrat on peut passer en venant d'un autre

    /**
     * Permet de recuperer les contrats possibles sur base du contrat actuellement dominant
     * et du contrat precedemment annonce par le joueur
     * @param actualContract Contrat actuellement dominant
     * @param previouslyAnnoncedContract Contrat precedemment annonce par le joueur
     * @return
     */
    public List<Contract> getPossiblesContracts(Contract actualContract, Contract previouslyAnnoncedContract){
        List<Contract> possiblesContracts = new ArrayList<Contract>();



        return possiblesContracts;
    }


    enum ContractType {
        EMBALLAGE,
        SOLO,
        TROU,
        PETITE_MISERE,
        HUIT_SUR_MISERE,
        ABONDANCE,
        GRANDE_MISERE,
        GRANDE_MISERE_SUR_TABLE,
        PETIT_CHELEM,
        SOLO_CHELEM
    }

    /**
     * Couleur de l'atout (s'il y en a un)
     */
    private Card.CardType trump;

    /** Nombre de plis */
    private Integer numberOfFolds;

    public Card.CardType getTrump() {
        return trump;
    }

    public Integer getNumberOfFolds() {
        return numberOfFolds;
    }
}

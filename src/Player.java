import java.util.Arrays;

public class Player {

    Card[] cards;
    int playerID;

    public Player(int[] cvs, int id){
        // For easier construction an array of int is used instead of array of cards
        cards = new Card[cvs.length];
        for (int i = 0; i < cvs.length;){
            cards[i] = new Card(cvs[i]);
            i++;
        };
        this.playerID = id;
    }

    public Card[] getCards(){
        return cards;
    }

    public int getPlayerID(){
        return playerID;
    }

    public boolean validate(){
        Card firstCard = cards[0];
        // Start from 1st card, loop through the rest.
        // If any cards are different from the first, return false. If loop completes run without running into problems, return true
        for (int i = 0; i < cards.length; i++){
            if (firstCard.getCardValue() != cards[i].getCardValue()) {
                return false;
            }
        }
        return true;
    }

    // Picks up a card and puts into own hand
    public void pickUp (Card c){
        Card[] auxCards = new Card[cards.length+1];
        System.arraycopy(cards, 0, auxCards, 1, cards.length);
        cards = auxCards;
        this.cards[0] = c;
    }

    // Discard this.cards[index] and returns it (return is for card pushing into deck in front of the player)
    public Card discard (int index){
        Card c = cards[index];
        Card[] auxCards = new Card[cards.length-1];
        System.arraycopy(cards, 0, auxCards, 0, index);
        System.arraycopy(cards, index+1, auxCards, index, auxCards.length-index);
        cards = auxCards;
        return c;
    }

    // Turns pickup and discard into one action (Handles the player logic too)
    public Card atomicPickUpAndDiscard(Card c, Card prevC){
        pickUp(c);
        Card retCard = new Card(0);
        for (int i = 0; i < cards.length; i++){
            // If card's value isn't the playerId then discard until all card's values are playerId
            if ((cards[i].getCardValue() != (playerID+1))){
                retCard = discard(i);
                break;
            }
        }
        return retCard;
    }

    @Override
    public String toString() {
        String aux = "";
        for (Card c: cards){
            aux = aux + "|" + c;
        };
        return aux + "|";
    }
}

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
                // System.out.println("broken" + firstCard.getCardValue() + "//" + cards.getCard(i).getCardValue());
                return false;
            }
        }
        return true;
    }

    public void pickUp (Card c){
        Card[] auxCards = new Card[cards.length+1];
        System.arraycopy(cards, 0, auxCards, 1, cards.length);
        cards = auxCards;
        this.cards[0] = c;
    }

    public Card discard (int index){
        Card c = cards[index];
        Card[] auxCards = new Card[cards.length-1];
        System.arraycopy(cards, 0, auxCards, 0, index);
        System.arraycopy(cards, index+1, auxCards, index, auxCards.length-index);
        cards = auxCards;
        return c;
    }

    public Card atomicPickUpAndDiscard(Card c, int index){
        return c; //TBD
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

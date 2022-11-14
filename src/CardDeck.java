import java.util.Arrays;

public class CardDeck {
    Card[] cards;
    int deckID;

    public CardDeck(int[] cvs, int id){
        // For easier construction an array of int is used instead of array of cards
        cards = new Card[cvs.length];
        for (int i = 0; i < cvs.length;){
            cards[i] = new Card(cvs[i]);
            i++;
        };
        this.deckID = id;
    }

    public Card[] getCards(){
        return cards;
    }

    public Card getCard(int index){
        return cards[index];
    }

    public int getLength(){
        return cards.length;
    }

    public int getDeckID(){
        return deckID;
    }

    // I fucked up... Should've used Queues, but it's too late everything else is in place and I don't want stuff breaking
    // Push(), Pop() EMULATES a Queue of cards with Array features at the cost of run time
    public void push (Card c){
        Card[] auxCards = new Card[cards.length+1];
        System.arraycopy(cards, 0, auxCards, 1, cards.length);
        cards = auxCards;
        this.cards[0] = c;
    }

    public Card pop (){
        Card c = cards[cards.length-1];
        this.cards = Arrays.copyOf(cards, cards.length-1);
        return c;
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

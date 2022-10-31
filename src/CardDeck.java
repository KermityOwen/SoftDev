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

    public void input (Card c){
        // TBD
    }

    public Card output (Card c){
        // TBD
        return c;
    }
}

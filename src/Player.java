public class Player {

    CardDeck cards;
    int playerID;

    public Player(CardDeck c, int id){
        this.cards = c;
        this.playerID = id;
    }

    public Card[] getCards(){
        return cards.getCards();
    }

    public int getPlayerID(){
        return playerID;
    }

    public boolean validate(){
        Card firstCard = cards.getCard(0);
        // Start from 1st card, loop through the rest.
        // If any cards are different from the first, return false. If loop completes run without running into problems, return true
        for (int i = 0; i < cards.getLength(); i++){
            if (firstCard.getCardValue() != cards.getCard(i).getCardValue()) {
                // System.out.println("broken" + firstCard.getCardValue() + "//" + cards.getCard(i).getCardValue());
                return false;
            }
        }
        return true;
    }

    public void pickUp (Card c){
        // TBD
    }

    public Card discard (Card c){
        // TBD
        return c;
    }
}

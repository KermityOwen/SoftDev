public class Card {

    int cardValue;

    public Card(int cv){
        this.cardValue = cv;
    }

    public int getCardValue(){
        return cardValue;
    }
    
    @Override
    public String toString() {
        return Integer.toString(cardValue);
    }
}

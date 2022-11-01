import java.util.HashMap;

public class CardGame {

    HashMap<Integer, Player> playersMap;
    HashMap<Integer, CardDeck> deckMap;

    public CardGame(int nOfPlayers, int[] cards){

    }

    public static void main(String[] args) {
        int nPlayers = Utilities.inputInt("Please enter the number of players: ");
        CardGame mainGame = new CardGame(nPlayers, new int[]{1, 1, 1, 1});

    }
}

import java.util.HashMap;

public class CardGame {

    HashMap<Integer, Player> playersMap;
    HashMap<Integer, CardDeck> deckMap;

    public CardGame(int nOfPlayers, int[] cvs) throws IncorrectCardsInPackException {
        if (cvs.length != 8*nOfPlayers){
            throw new IncorrectCardsInPackException("Error");
        }

        int[][] cardSplits = Utilities.splitIntArray(cvs, nOfPlayers*2); // INCOMPLETE
        for (int i = 0; i < cardSplits.length-1;){

        }
    }

    public static void main(String[] args) throws IncorrectCardsInPackException {
        int nPlayers = Utilities.inputInt("Please enter the number of players: ");
        CardGame mainGame = new CardGame(nPlayers, new int[]{1, 1, 1, 1});

    }
}

import java.util.HashMap;
import java.util.Scanner;

public class CardGame {

    // Maps for players and decks
    HashMap<Integer, Player> playersMap;
    HashMap<Integer, CardDeck> deckMap;

    // Number of players + numbers of decks (used for circular calculations with modulo later)
    int nPlayersDecks;

    synchronized static int[] parseDeckFile(String filePath){
        // TBD
        return null;
    }

    synchronized static String inputString(String prompt){
        Scanner s = new Scanner(System.in);
        System.out.println(prompt);
        return s.nextLine();
    }

    public CardGame(int nOfPlayers, int[] cvs) throws IncorrectCardsInPackException {
        // Guards
        if (cvs.length != 8*nOfPlayers){
            throw new IncorrectCardsInPackException("Pack doesn't have 8*(N of Players) cards");
        }

        nPlayersDecks = nOfPlayers *2;
        int[][] cardSplits = Utilities.splitIntArray(cvs, nOfPlayers*2); // INCOMPLETE

        for (int i = 0; i < (nOfPlayers*2);){
            playersMap.put(i, new Player(cardSplits[i], i));
            deckMap.put(i+1, new CardDeck(cardSplits[i+1], i+1));
            i = i+2;
        }
    }

    public static void main(String[] args) {
        int nPlayers = Utilities.inputInt("Please enter the number of players: ");

        // \/ need to edit after parseDeckFile adn inputString is relocated to the Utility class
        int[] cardPack = parseDeckFile(inputString("Please enter location of the pack to load: "));

        try {
            CardGame mainGame = new CardGame(nPlayers, cardPack);
        } catch (IncorrectCardsInPackException e){
            System.out.println(e);
        }


    }
}

import java.util.HashMap;
import java.util.Scanner;

public class CardGame {

    // Maps for players and decks
    HashMap<Integer, Player> playersMap;
    HashMap<Integer, CardDeck> deckMap;

    // Number of players + numbers of decks (used for circular calculations with modulo later)
    int nPlayersDecks;

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

    public void startGame(){
        Thread[] threads = new Thread[this.nPlayersDecks/2];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    // TBD
                }
            });
            threads[i].start();
        }

    }

    public static void main(String[] args) {
        int nPlayers = Utilities.inputInt("Please enter the number of players: ");

        // \/ need to edit after parseDeckFile adn inputString is relocated to the Utility class
        int[] cardPack = Utilities.parseDeckFile(Utilities.inputString("Please enter location of the pack to load: "));

        try {
            CardGame mainGame = new CardGame(nPlayers, cardPack);
        } catch (IncorrectCardsInPackException e){
            System.out.println(e);
        }


    }
}

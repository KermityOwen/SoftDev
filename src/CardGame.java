import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CardGame {

    // Maps for players and decks
    public HashMap<Integer, Player> playersMap = new HashMap<Integer, Player>();
    public HashMap<Integer, CardDeck> deckMap = new HashMap<Integer, CardDeck>();

    // Number of players + numbers of decks (used for circular calculations with modulo later)
    int nPlayersDecks;

    public CardGame(int nOfPlayers, int[] cvs) throws IncorrectCardsInPackException {
        // Guards
        if (cvs.length != 8*nOfPlayers){
            throw new IncorrectCardsInPackException("Pack doesn't have 8*(N of Players) cards");
        }

        nPlayersDecks = nOfPlayers *2;
        int[][] playerSplit = Utilities.roundRobinSplit(Arrays.copyOfRange(cvs, 0, (cvs.length/2)), nOfPlayers);
        int[][] deckSplit = Utilities.roundRobinSplit(Arrays.copyOfRange(cvs, cvs.length/2, cvs.length), nOfPlayers);

        for (int i = 0; i < nOfPlayers; i++){
            playersMap.put(i, new Player(playerSplit[i], i));
            deckMap.put(i, new CardDeck(deckSplit[i], i));
        }
    }

    @Override
    public String toString() {
        String deckInfo = "Deck Cards: " + Arrays.toString(deckMap.values().toArray()) + "\nDeck Ids: " + Arrays.toString(deckMap.keySet().toArray());
        String playerInfo = "Player Cards: " + Arrays.toString(playersMap.values().toArray()) + "\nPlayer Ids: " + Arrays.toString(playersMap.keySet().toArray());
        return deckInfo + "\n" + playerInfo;
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
            System.out.println(mainGame);
            System.out.println("Valid Pack");
        } catch (IncorrectCardsInPackException e){
            System.out.println(e);
        }


    }
}

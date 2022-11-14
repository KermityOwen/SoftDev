import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class CardGame {

    // Maps for players and decks
    public HashMap<Integer, Player> playersMap = new HashMap<Integer, Player>();
    public HashMap<Integer, CardDeck> deckMap = new HashMap<Integer, CardDeck>();

    // Auxiliary list of string to be logged into a txt file post-game
    public ArrayList<String> playersLogs = new ArrayList<String>();

    // Number of players / decks
    int nPlayersDecks;

    // Group of threads of size nPlayerDeck (CountDownLatch for group awaits)
    public CountDownLatch threads = new CountDownLatch(this.nPlayersDecks);

    // Which player won the game, if empty the game is not complete yet
    String gameWonBy = "";

    public CardGame(int nOfPlayers, int[] cvs) throws IncorrectCardsInPackException {
        // Guards to ensure correct amount of players
        if (cvs.length != 8*nOfPlayers){
            throw new IncorrectCardsInPackException("Pack doesn't have 8*(N of Players) cards");
        }

        // Constructors
        nPlayersDecks = nOfPlayers;
        int[][] playerSplit = Utilities.roundRobinSplit(Arrays.copyOfRange(cvs, 0, (cvs.length/2)), nOfPlayers);
        int[][] deckSplit = Utilities.roundRobinSplit(Arrays.copyOfRange(cvs, cvs.length/2, cvs.length), nOfPlayers);

        // Self-explanatory
        for (int i = 0; i < nOfPlayers; i++){
            playersMap.put(i, new Player(playerSplit[i], i));
            deckMap.put(i, new CardDeck(deckSplit[i], i));
        }
    }

    @Override
    public String toString() {
        // Easier for debugging
        String deckInfo = "Deck Cards: " + Arrays.toString(deckMap.values().toArray()) + "\nDeck Ids: " + Arrays.toString(deckMap.keySet().toArray());
        String playerInfo = "Player Cards: " + Arrays.toString(playersMap.values().toArray()) + "\nPlayer Ids: " + Arrays.toString(playersMap.keySet().toArray());
        return deckInfo + "\n" + playerInfo;
    }

    // Concat logLine into the string that is already existing in playersLog(playerId)
    public void logLine(int playerId, String logLine){
        String newLog = playersLogs.get(playerId) + "\n" + logLine;
        playersLogs.set(playerId, newLog);
    }

    // Picks up a card then discards the appropriate card (logic handled in Player class)
    // Handles the deck in front and behind player to ensure no dupes or missing cards (logic handled mostly in Deck class)
    // Logs all the moves appropriately
    public Card playerMove(int i, Card prevDisc){
        logLine(i, "player " + i + " draws a " + deckMap.get(i) + " from deck " + i);
        Card cardDiscarded = playersMap.get(i).atomicPickUpAndDiscard(deckMap.get(i).pop(), prevDisc); // pop card from deck then give to player

        logLine(i, "player " + i + " discards a " + cardDiscarded + " to deck " + (i+1)%nPlayersDecks);
        deckMap.get((i+1)%nPlayersDecks).push(cardDiscarded);

        return cardDiscarded;
    }

    // Start game simulation
    public synchronized void startGame(){
        for (int i = 0; i < this.nPlayersDecks; i++) {
            // Final i so it's accessible in the thread below
            int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    while (gameWonBy.length() == 0) {
                        Card prevDisc = new Card(0);
                        if (!playersMap.get(finalI).validate()){
                            prevDisc = playerMove(finalI, prevDisc);
                            System.out.println("Player: " + finalI + ", Player Hand: " + playersMap.get(finalI));
                            System.out.println("Deck: " + finalI + ", Deck Hand: " + deckMap.get(finalI));
                        } else {
                            gameWonBy = Integer.toString(finalI);
                            System.out.println(gameWonBy+" won");
                            break;
                        }
                    }
                    Utilities.logFile("player"+finalI+"_output.txt", playersLogs.get(finalI));
                }
            }).start(); // should be rewritten so that the threads start after they have been all been made
        }
    }

    public static void main(String[] args) {
        int nPlayers = Utilities.inputInt("Please enter the number of players: ");
        int[] cardPack = Utilities.parseDeckFile(Utilities.inputString("Please enter location of the pack to load: "));

        try {
            CardGame mainGame = new CardGame(nPlayers, cardPack);
            for (int i = 0; i < nPlayers; i++){
                mainGame.playersLogs.add("");
            }
            mainGame.startGame();

            // Doesn't work yet
            mainGame.threads.countDown();
            System.out.println("working");

        } catch (IncorrectCardsInPackException e){
            System.out.println(e);
        }


    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class CardGame {

    // Maps for players and decks
    public HashMap<Integer, Player> playersMap = new HashMap<Integer, Player>();
    public HashMap<Integer, CardDeck> deckMap = new HashMap<Integer, CardDeck>();

    public ArrayList<String> playersLogs = new ArrayList<String>();

    public CountDownLatch threads = new CountDownLatch(this.nPlayersDecks);

    int nPlayersDecks;

    String gameWonBy = "";

    public CardGame(int nOfPlayers, int[] cvs) throws IncorrectCardsInPackException {
        // Guards
        if (cvs.length != 8*nOfPlayers){
            throw new IncorrectCardsInPackException("Pack doesn't have 8*(N of Players) cards");
        }

        nPlayersDecks = nOfPlayers;
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

    public void logLine(int playerId, String logLine){
        String newLog = playersLogs.get(playerId) + "\n" + logLine;
        playersLogs.set(playerId, newLog);
    }


    // returns card previously discarded
    public Card playerMove(int i, Card prevDisc){
        logLine(i, "player " + i + " draws a " + deckMap.get(i) + " from deck " + i);
        Card cardDiscarded = playersMap.get(i).atomicPickUpAndDiscard(deckMap.get(i).pop(), prevDisc); // pop card from deck then give to player

        logLine(i, "player " + i + " discards a " + cardDiscarded + " to deck " + (i+1)%nPlayersDecks);
        deckMap.get((i+1)%nPlayersDecks).push(cardDiscarded);

        return cardDiscarded;
    }

    public synchronized void startGame(){
        for (int i = 0; i < this.nPlayersDecks; i++) {
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
            }).start();
            //threads.start();
        }
    }

    public static void main(String[] args) {
        int nPlayers = Utilities.inputInt("Please enter the number of players: ");
        // \/ need to edit after parseDeckFile adn inputString is relocated to the Utility class
        int[] cardPack = Utilities.parseDeckFile(Utilities.inputString("Please enter location of the pack to load: "));

        try {
            CardGame mainGame = new CardGame(nPlayers, cardPack);
            for (int i = 0; i < nPlayers; i++){
                mainGame.playersLogs.add("");
            }
            mainGame.startGame();

            // doesnt work yet
            mainGame.threads.countDown();
            System.out.println("working");

            // System.out.println(mainGame);
            // System.out.println("Valid Pack");
        } catch (IncorrectCardsInPackException e){
            System.out.println(e);
        }


    }
}

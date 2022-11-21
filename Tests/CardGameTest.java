
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {
    CardGame cardGame;
    Card card1 = new Card(1);

    @BeforeEach
    void setUp() throws IncorrectCardsInPackException {
        cardGame = new CardGame(4, new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8});
    }


    @Test
    void testToString() {
        String expected = "Deck Cards: [|5|6|7|8|, |5|6|7|8|, |5|6|7|8|, |5|6|7|8|]";
        String c = "\nDeck Ids: [0, 1, 2, 3]";
        String x = "\nPlayer Cards: [|1|2|3|4|, |1|2|3|4|, |1|2|3|4|, |1|2|3|4|]";
        String d = "\nPlayer Ids: [0, 1, 2, 3]";
        assertEquals(expected + c + x + d, cardGame.toString());
    }

    @Test
    void logLine(){
        cardGame.playersLogs.add(0, "123");
        cardGame.logLine(0,"456");
        assertEquals("123\n456",cardGame.playersLogs.get(0));
    }

    @Test
    void playerMove() {
        Card prevDisc = new Card(0);
        cardGame.playerMove(0, prevDisc);
    }

    @Test
    void startGame() {
    }
}
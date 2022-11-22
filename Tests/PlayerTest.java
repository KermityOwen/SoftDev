import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player;
    Card card1 = new Card(1);
    Card card2 = new Card(2);
    Card card3 = new Card(3);
    Card card4 = new Card(4);
    Card[] cards = new Card[]{card1, card2, card3, card4};

    @BeforeEach
    void setUp(){
        player = new Player(new int[]{1,2,3,4},1);
    }

    @Test
    void getCardsTest() {
        String c = Arrays.deepToString(cards);
        assertEquals(c, Arrays.deepToString(player.getCards()));
    }

    @Test
    void getPlayerID() {
        assertEquals(1, player.getPlayerID());
    }

    @Test
    void validate() {
        assertFalse(player.validate());
    }

    @Test
    void pickUp() {
        player.pickUp(card1);
        Card[] cards = player.getCards();
        assertEquals(5, cards.length);
    }

    @Test
    void discard() {
        player.discard(0);
        Card[] cards = player.getCards();
        assertEquals(3, cards.length);
    }

    @Test
    void atomicPickUpAndDiscard() {
        Card c = player.atomicPickUpAndDiscard(card1);
        assertEquals(player.getCards()[0].toString(), c.toString());
    }

    @Test
    void toStringTest(){
        String s = "|1|2|3|4|";
        assertEquals(s, player.toString());
    }
}

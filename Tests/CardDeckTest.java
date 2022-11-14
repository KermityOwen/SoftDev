import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {

    CardDeck cardDeck;
    Card card1 = new Card(1);
    Card card2 = new Card(2);
    Card card3 = new Card(3);
    Card card4 = new Card(4);
    Card[] cards = new Card[]{card1, card2, card3, card4};

    @BeforeEach
    void setUp(){
        cardDeck = new CardDeck(new int[]{1,2,3,4}, 1);
    }

    @Test
    void getCardsTest(){
        String c = Arrays.deepToString(cards);
        assertEquals(c, Arrays.deepToString(cardDeck.getCards()));
    }

    @Test
    void getCardTest(){
        assertEquals(cardDeck.getCards()[0], cardDeck.getCard(0));
    }

    @Test
    void getLengthTest(){
        assertEquals(4, cardDeck.getLength());
    }

    @Test
    void getDeckIdTest(){
        assertEquals(1,cardDeck.getDeckID());
    }

    @Test
    void pushTest(){
        cardDeck.push(card1);
        assertEquals(5, cardDeck.getLength());
    }

    @Test
    void popTest(){
        cardDeck.pop();
        assertEquals(3, cardDeck.getLength());
    }

    @Test
    void toStringTest(){
        String s = "|1|2|3|4|";
        assertEquals(s, cardDeck.toString());
    }
}
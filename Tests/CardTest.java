import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card card;

    @BeforeEach
    void setUp() {
        card = new Card(10);
    }

    @Test
    void getCardValue(){
        assertEquals(10, card.getCardValue());
    }

    @Test
    void ToString(){
        assertEquals("10", card.toString());
    }
}
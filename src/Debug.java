import java.util.Arrays;

// Used for debug while coding and for easy copy-pasting for testing later
public class Debug {
    public static void main(String[] args) {
        CardDeck auxDeck = new CardDeck(new int[]{1,2,3,4}, 2);
        Player player = new Player(new int[]{1,1,5,4}, 0);

        System.out.println(Arrays.deepToString(Utilities.roundRobinSplit(new int[]{1,2,3,4,5,6,7,8,1,2,3,4}, 3)));
/*
        System.out.println(player);
        player.pickUp(new Card(2));
        System.out.println(player);
        player.discard(4);
        System.out.println(player);

        System.out.println(Arrays.toString(Utilities.parseDeckFile("four.txt")));
        System.out.println(Arrays.deepToString(Utilities.splitIntArray(new int[]{1,2,3,4,5,6,3,4,5}, 3)));

        System.out.println(auxDeck);
        System.out.println(auxDeck.pop());
        auxDeck.push(new Card(6));
        System.out.println(auxDeck);
        System.out.println(auxDeck.pop());
        System.out.println(auxDeck.pop());
        System.out.println(auxDeck);

        System.out.println(Utilities.inputInt("Testing:"));
        System.out.println(auxDeck.getCard(1).getCardValue());
        System.out.println(auxDeck.getLength());
        System.out.println(player.validate());
        */

    }
}
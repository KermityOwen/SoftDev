public class Main {
    public static void main(String[] args) {
        CardDeck auxDeck = new CardDeck(new int[]{1,1,1,1}, 2);
        Player player = new Player(auxDeck, 0);

        System.out.println(Utilities.inputInt("Testing:"));

        /*
        System.out.println(auxDeck.getCard(1).getCardValue());
        System.out.println(auxDeck.getLength());
        System.out.println(player.validate());
        */

    }
}
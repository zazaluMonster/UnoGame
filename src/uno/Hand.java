package uno;


/**
 * The purpose of this class is to get the player’s card and store the players’ cards separately,
 * each player has a array store their cards. (This is a DynamicArray) There are 3 card object
 * arrays to store each player’s cards and a size filed to adjustment the array size, if the size
 * causes some errors, I will use expection. There is addCard method can allow me to add card object
 * and delCard method can allow me to delete any card object (the card player draw). Also, getLength
 * method can allow us to get the array length.
 */
public class Hand {
    private CardArray cards = new CardArray();

    public int getLength() {
        return cards.getLength();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card delCard(int index) {
        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public CardArray getCards() {
        return cards;
    }

    //show all players cards
    public void showAllCards(int nextTurnPlayer){
        System.out.println("Now player " + nextTurnPlayer + "'s cards:");
        for (int i = 0; i < this.getLength(); i++) {
            System.out.print(this.getCard(i).toString() + ", ");
        }
        System.out.println("");
    }

    //There's only one card left. Shout UNO
    public void checkUno(int nextTurnPlayer){
        if(this.getLength() == 1){
            System.out.println("player " + nextTurnPlayer + " UNO!");
        }
    }
}

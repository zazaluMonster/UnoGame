package uno;

import java.util.ArrayList;

/**
 * The purpose of this class is to get the player’s card and store the players’ cards separately,
 * each player has a array store their cards. (This is a DynamicArray) There are 3 card object
 * arrays to store each player’s cards and a size filed to adjustment the array size, if the size
 * causes some errors, I will use expection. There is addCard method can allow me to add card object
 * and delCard method can allow me to delete any card object (the card player draw). Also, getLength
 * method can allow us to get the array length.
 */
public class Hand {
    private ArrayList<Card> arrayList = new ArrayList<>();
    int next;//the player's next card num

    public Hand() {
    }

    @Override
    public String toString() {
        return "Hand{" +
                "arrayList=" + arrayList +
                ", next=" + next +
                '}';
    }

    public int getLength() {
        return arrayList.size();
    }

    public void addCard(Card card) {
        arrayList.add(card);
    }

    public Card delCard(int index) {
        Card card = arrayList.get(index);
        arrayList.remove(index);
        return card;
    }

    public Card getCard(int index) {
        return arrayList.get(index);
    }

    public ArrayList<Card> getCards() {
        return arrayList;
    }
}

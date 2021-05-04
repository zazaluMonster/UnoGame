package uno;

import java.util.ArrayList;
import java.util.Random;

/**
 *  Storing cards
 *
 *  There are 84 cards in all.
 *  normal card(0-9): 76
 *  function card(skip) : 8
 *  total: 88 cards.
 */
public class CardBox {
    ArrayList<Card> arrayList = new ArrayList<>();
    Random random = new Random();

    public void init(){
        productionCard(false, 0);
        productionCard(false, 1);
        productionCard(false, 1);
        productionCard(false, 2);
        productionCard(false, 2);
        productionCard(false, 3);
        productionCard(false, 3);
        productionCard(false, 4);
        productionCard(false, 4);
        productionCard(false, 5);
        productionCard(false, 5);
        productionCard(false, 6);
        productionCard(false, 6);
        productionCard(false, 7);
        productionCard(false, 7);
        productionCard(false, 8);
        productionCard(false, 8);
        productionCard(false, 9);
        productionCard(false, 9);
        productionCard(true, -1);
        productionCard(true, -1);
    }

    public void productionCard(boolean isFunction, int num){
        Card red = new Card(num, "red", isFunction);
        Card yellow = new Card(num, "yellow", isFunction);
        Card blue = new Card(num, "blue", isFunction);
        Card green = new Card(num, "green", isFunction);
        arrayList.add(red);
        arrayList.add(yellow);
        arrayList.add(blue);
        arrayList.add(green);
    }

    public Card getRandomCard(){
        if(arrayList.size() <= 0){//If the card is used up, add new cards
            Log.info("牌用完了，重新塞");
            init();
        }

        int index = random.nextInt(arrayList.size());
        Card card = arrayList.get(index);
        arrayList.remove(index);
        return card;
    }
}
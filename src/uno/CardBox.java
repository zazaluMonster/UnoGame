package uno;

import java.util.Random;

/**
 *  Storing cards
 *
 *  There are 84 cards in all.
 *  normal card(0-9): 76
 *  function card(skip) : 8
 *  total: 84 cards.
 */
public class CardBox {
    CardArray cards = new CardArray();
    Random random = new Random();
    static int SKIP = -1;//function skip card value

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
        productionCard(true, CardBox.SKIP);
        productionCard(true, CardBox.SKIP);
    }

    public void productionCard(boolean isFunction, int num){
        Card red = new Card(num, "red", isFunction);
        Card yellow = new Card(num, "yellow", isFunction);
        Card blue = new Card(num, "blue", isFunction);
        Card green = new Card(num, "green", isFunction);
        cards.add(red);
        cards.add(yellow);
        cards.add(blue);
        cards.add(green);
    }

    public Card drawCard(){
        if(cards.getLength() <= 0){//If the card is used up, add new cards
            init();
        }

        int index = random.nextInt(cards.getLength());
        Card card = cards.get(index);
        cards.remove(index);
        return card;
    }

    //player and deal cards to them, 7 cards for each
    public Hand[] dealCardsToPlayers(int playerCount, int beginningCardsCount){
        Hand[] players = new Hand[playerCount];
        for (int j = 0; j < playerCount; j++) {
            players[j] = new Hand();
            System.out.println("player "+(j+1) + "'s cards:");
            for (int i = 0; i < beginningCardsCount; i++) {
                players[j].addCard(this.drawCard());
                System.out.print(players[j].getCard(i).toString() + ", ");
            }
            System.out.println("");
        }
        return players;
    }
}

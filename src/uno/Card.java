package uno;

/**
 * The purpose of this class is to represent a single card. One card includes one value
 * and one number (not include the skip function card) and a random field to make the
 * random card. There is the draw method to get the random card (include card color and
 * number, the maximum number should be 9) and add value and color in one string. Also,
 * the skip function to achieve the skip function card
 */
public class Card {
    private int value;//normal card: [0-9], function card: [-1]
    private String color;//red, yellow, blue, green
    private boolean isFunction;//true-function card, false-normal card

    public Card(int value, String color, boolean isFunction) {
        this.value = value;
        this.color = color;
        this.isFunction = isFunction;
    }

    public int getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public Card draw(CardBox cardBox){
        return cardBox.drawCard();
    }

    public void skipFunction(int nextTurnPlayer){
        System.out.println("Player "+nextTurnPlayer+" use skip cards, skip this turn");
    }

    @Override
    public String toString() {
        if(CardBox.SKIP == value){
            return color + " skip";
        }else{
            return color + " " + value;
        }
    }



    public boolean isFunction() {
        return isFunction;
    }
}

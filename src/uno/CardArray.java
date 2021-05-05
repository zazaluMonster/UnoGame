package uno;

/**
 * a DynamiccardArray for Card
 */
public class CardArray {
    private Card[] cardArray;
    private int next;

    public CardArray() {
        this.cardArray = new Card[100];
        this.next = 0;
    }
    public int getLength() {
        return this.next;
    }
    public Card get(int index){
        if(index >= this.next)
            throw new IllegalArgumentException("Invalid index value!");
        return this.cardArray[index];
    }

    public void add(Card card)  {
        if(this.next >= this.cardArray.length){
            resize();  //this.resize(); is the same
        }
        this.cardArray[this.next] = card;
        this.next++;
    }

    private void resize() {
        Card[] newArray = new Card[this.cardArray.length*2];
        for(int i = 0; i < this.cardArray.length; i++) {
            newArray[i] = this.cardArray[i];
        }
        this.cardArray = newArray;
    }

    public void remove(int i) {
        for (int j = i; j < this.next; j++) {
            this.cardArray[j] = this.cardArray[j + 1];
        }

        this.cardArray[this.next - 1] = null;
        this.next--;
    }


}

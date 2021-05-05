package uno;

/**
 * The purpose of this class is to make each turn of the game.There is a Hand
 * object which contain each player’s cards in hand. There are userInsert method
 * allow player to insert the card they want to play out. And There is toCheck
 * method to make sure the card player draw is valid.
 */
public class PlayTurn {
    int lastTurnPlayer;
    int nextTurnPlayer;
    int totalPlayers;
    Card lastCard;
    Hand[] players;

    public void init(int startPlayerNum, int totalPlayers, Hand[] players){
        this.totalPlayers = totalPlayers;
        this.lastTurnPlayer = 0;
        this.nextTurnPlayer = startPlayerNum;
        this.players = players;
        this.lastCard = null;
    }

    /**
     * userInsert method allow player to insert the card they want to play out. refresh status
     */
    public void userInsert(Card card){
        lastTurnPlayer = nextTurnPlayer;
        if(card!= null){//有牌打出去才记录
            lastCard = card;
            if(card.isFunction()){
                card.skipFunction(nextTurnPlayer);
            }
        }
        nextTurnPlayer = (nextTurnPlayer+1) > totalPlayers ? 1 : (nextTurnPlayer+1);
    }

    /**
     * toCheck method to make sure the card fix condition
     */
    public boolean toCheck(Card nextCard){
        if(lastCard != null){
            if(nextCard.isFunction()){//function card check color only
                if(lastCard.getColor().equals(nextCard.getColor())){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(lastCard.getColor().equals(nextCard.getColor()) || lastCard.getValue() == nextCard.getValue()){
                    return true;
                }else{
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * toCheck method to make sure the card fix condition
     */
    public boolean toCheckAll(CardArray allCards){
        if(allCards == null){
            return false;
        }else{
            for (int i = 0; i < allCards.getLength(); i++) {
                if(allCards.get(i).isFunction()){
                    return true;
                }
                if(toCheck(allCards.get(i))){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Last player: player " + lastTurnPlayer +
                ", Next player: player " + nextTurnPlayer + ", Last Card: " + lastCard.toString();
    }

    public int getNextTurnPlayer() {
        return nextTurnPlayer;
    }
}

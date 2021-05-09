package uno;

import java.util.Scanner;

/**
 * This is the driver class of the application with the main method.
 * This class contain PlayTurn object and all the interaction with the user is part of this class.
 * Also, the sentence display before and after the game, make the game more complete.
 */
public class UnoApplication {
    public static void main(String[] args){
        int firstPlayer = 1;
        boolean isGameOver = false;
        int beginningCardsCount = 7;
        int playerCount = 3;
        Scanner input=new Scanner(System.in);

        System.out.println("Welcome to Uno!");
        System.out.println("We have " + playerCount + " players in this game!");
        System.out.println("Now Let's Start!");

        //Initial
        //Card-box(Storing cards)
        CardBox cardBox = new CardBox();
        cardBox.init();

        //Players
        Hand[] players = cardBox.dealCardsToPlayers(playerCount,beginningCardsCount);

        //PlayTurn
        PlayTurn playTurn = new PlayTurn();
        playTurn.init(firstPlayer, playerCount, players);

        //start game(A while cycle represents a player turn)
        while(!isGameOver){
            int nextTurnPlayer = playTurn.getNextTurnPlayer();
            if(nextTurnPlayer <= 0 || nextTurnPlayer > players.length ){
                System.out.println("Abnormal number of players, pleas try again!");
                continue;
            }

            System.out.println("player "+nextTurnPlayer+"'s turn");

            Hand curPlayer = players[nextTurnPlayer - 1];

            curPlayer.showAllCards(nextTurnPlayer);

            //Check whether players have cards to play, if yes, draw a card. if no,choose card
            if(playTurn.toCheckAll(curPlayer.getCards())){
                System.out.print("please choose the card to play: ");
                int n = input.nextInt();
                if(n <= 0 || n > curPlayer.getLength()){
                    System.out.println("Please enter the correct card number.");
                    continue;
                }
                Card curCard = players[nextTurnPlayer - 1].getCard(n-1);
                System.out.println(curCard);

                //Check whether the card fix the condition, if yes, play this card and Check whether it meets the requirements of the end of the game. if no, request play again
                if(playTurn.toCheck(curCard) || curCard.isFunction()){
                    playTurn.userInsert(players[nextTurnPlayer - 1].delCard(n-1));
                    if(curPlayer.getLength() <= 0){
                        System.out.println("player " + nextTurnPlayer + " won :)!");
                        boolean inputError = true;
                        while(inputError){
                            System.out.print("Do you guys want to continue?(yes or no): ");
                            String isContinue = input.next();
                            if("yes".equals(isContinue)){//restart
                                players = cardBox.dealCardsToPlayers(playerCount,beginningCardsCount);
                                playTurn.init(firstPlayer, playerCount, players);
                                inputError = false;
                            }else if("no".equals(isContinue)){//gameover
                                isGameOver = true;
                                inputError = false;
                            }else{
                                System.out.println("Please enter yes or no!");
                            }
                        }
                    }else{
                        curPlayer.showAllCards(nextTurnPlayer);
                        curPlayer.checkUno(nextTurnPlayer);
                    }
                }else{
                    System.out.println("the card is not fix to condition, please try again.");
                }
            }else{
                Card card = cardBox.drawCard();
                System.out.println("player " + nextTurnPlayer + " has no cards to play, draw a card: " + card);
                //Check whether the card fix the condition, if yes, Ask the player whether to play it out.
                if(playTurn.toCheck(card)  || card.isFunction()){
                    boolean inputError = true;
                    while(inputError){
                        System.out.print("Do you want to play this card?(yes or no): ");
                        String isPlay = input.next();
                        if("yes".equals(isPlay)){//直接打出
                            playTurn.userInsert(card);
                            inputError = false;
                        }else if("no".equals(isPlay)){//保留
                            curPlayer.addCard(card);
                            playTurn.userInsert(null);
                            inputError = false;
                        }else{
                            System.out.println("Please enter yes or no!");
                        }
                    }
                }else{
                    curPlayer.addCard(card);
                    playTurn.userInsert(null);
                }
                curPlayer.showAllCards(nextTurnPlayer);
            }
        }

        System.out.println("Game Over.");
    }
}

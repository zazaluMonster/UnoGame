package uno;

import java.util.Scanner;

/**
 * This is the driver class of the application with the main method.
 * This class contain PlayTurn object and all the interaction with the user is part of this class.
 * Also, the sentence display before and after the game, make the game more complete.
 */
public class UnoApplication {

    public static void main(String[] args){
        int playerCount = 3;
        int beginningCardsCount = 7;
        int firstPlayer = 1;
        boolean isGameOver = false;
        Scanner input=new Scanner(System.in);

        System.out.println("Welcome to Uno!");
        System.out.println("We have " + playerCount + " players in this game!");
        System.out.println("Now Let's Start!");

        //Initial card-box(Storing cards)
        CardBox cardBox = new CardBox();
        cardBox.init();

        //Initial player and deal cards to them, 7 cards for each
        Hand[] players = new Hand[playerCount];
        for (int j = 0; j < playerCount; j++) {
            players[j] = new Hand();
            System.out.println("player "+(j+1) + "'s cards:");
            for (int i = 0; i < beginningCardsCount; i++) {
                players[j].addCard(cardBox.getRandomCard());
                System.out.print(players[j].getCard(i).toString() + ", ");
            }
            System.out.println("");
        }

        //Initial PlayTurn
        PlayTurn playTurn = new PlayTurn();
        playTurn.init(firstPlayer, playerCount, players);

        //start game
        while(!isGameOver){
            //player choose card
            int nextTurnPlayer = playTurn.getNextTurnPlayer();
            if(nextTurnPlayer <= 0 || nextTurnPlayer > players.length ){
                System.out.println("Abnormal number of players, pleas try again!");
                continue;
            }

            System.out.println("player "+nextTurnPlayer+"'s turn");
            Hand curPlayer = players[nextTurnPlayer - 1];

            //检查玩家是否有牌可出, 有牌可出让玩家选择出哪张, 无牌可出就抽牌
            if(playTurn.toCheckAll(curPlayer.getCards())){
                System.out.print("please choose the card to play: ");
                int n = input.nextInt();
                if(n <= 0 || n > curPlayer.getLength()){
                    System.out.println("Please enter the correct card number.");
                    continue;
                }
                Card curCard = players[nextTurnPlayer - 1].getCard(n-1);
                System.out.println(curCard);

                //检查出的牌是否符合规则
                if(playTurn.toCheck(curCard)){
                    playTurn.userInsert(players[nextTurnPlayer - 1].delCard(n-1));
                    if(curPlayer.getLength() <= 0){//牌已出完
                        System.out.println("player " + nextTurnPlayer + " won :)!");
                        boolean inputError = true;
                        while(inputError){
                            System.out.print("Do you guys want to continue?(yes or no): ");
                            String isContinue = input.next();
                            if("yes".equals(isContinue)){//继续玩
                                players = new Hand[playerCount];
                                for (int j = 0; j < playerCount; j++) {
                                    players[j] = new Hand();
                                    System.out.println("player "+(j+1) + "'s cards:");
                                    for (int i = 0; i < beginningCardsCount; i++) {
                                        players[j].addCard(cardBox.getRandomCard());
                                        System.out.print(players[j].getCard(i).toString() + ", ");
                                    }
                                    System.out.println("");
                                }
                                playTurn.init(firstPlayer, playerCount, players);
                                inputError = false;
                            }else if("no".equals(isContinue)){//结束游戏
                                isGameOver = true;
                                inputError = false;
                            }else{
                                System.out.println("Please enter yes or no!");
                            }
                        }
                    }else{
                        System.out.println("Now player " + nextTurnPlayer + "'s cards:");
                        for (int i = 0; i < curPlayer.getLength(); i++) {
                            System.out.print(curPlayer.getCard(i).toString() + ", ");
                        }
                        System.out.println("");

                        //只剩最后一张牌, 喊出uno
                        if(curPlayer.getLength() == 1){
                            System.out.println("player " + nextTurnPlayer + " UNO!");
                        }
                    }
                }else{
                    System.out.println("the card is not fix to condition, please try again.");
                }
            }else{
                //无牌可出 抽牌
                Card card = cardBox.getRandomCard();
                System.out.println("player " + nextTurnPlayer + " has no cards to play, draw a card: " + card);
                if(playTurn.toCheck(card)){//刚抽到的牌符合要求, 询问玩家是否保留还是打出
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
                //展示手牌
                System.out.println("Now player " + nextTurnPlayer + "'s cards:");
                for (int i = 0; i < curPlayer.getLength(); i++) {
                    System.out.print(curPlayer.getCard(i).toString() + ", ");
                }
                System.out.println("");
            }
        }

        System.out.println("Game Over.");

    }
}

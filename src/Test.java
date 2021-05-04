import uno.Card;
import uno.CardBox;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int lastTurnPlayer = 1;
        int nextTurnPlayer = 3;
        int totalPlayers = 3;
        lastTurnPlayer = nextTurnPlayer;
        nextTurnPlayer = (nextTurnPlayer+2) > totalPlayers ? ((nextTurnPlayer+2) % totalPlayers) : (nextTurnPlayer+2);
        System.out.println(lastTurnPlayer);
        System.out.println(nextTurnPlayer);
    }
}


/**
 yellow 7, green 4, yellow 5, yellow 4, green 7, blue 7, blue 4,
 yellow 6, blue 5, green 4, red 2, green 3, red 1, red 8,
 blue 7, blue 2, red -1, yellow 9, green 9, red 7, red 8,
 */

//测试案例
//blue -1, blue 4, blue 6, blue 1,
//blue 7, red 2, red 3, blue 3,
//red 4, yellow 7, blue 5,
//第一次输入：1-7，以及功能卡

//第二次输入：1-7，以及功能卡，输入正确的卡 不正确的卡



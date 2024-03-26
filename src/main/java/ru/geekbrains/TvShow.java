package ru.geekbrains;

import java.util.HashMap;
import java.util.Random;

public class TvShow {
    private final Player player = new Player();
    private final Showman showman = new Showman();
    private final Random random = new Random();
    private static final int DOOR_COUNT = 3;
    private int prizeDoor;
    private int playerDoor;
    private int showmanDoor;

    public void startGame(int numberOfIterations){
        HashMap<Integer, Boolean> results = new HashMap<>();

        for (int i = 0; i < numberOfIterations; i++) {
            putPrise();
            System.out.println(STR."Prize is in box #\{prizeDoor}");
            playerTurn();
            System.out.println(player);
            showManTurn();
            System.out.println(showman);
            playerSecondTurn();
            System.out.println(STR."Player has selected new box #\{playerDoor}");
            results.put(i, playerDoor == prizeDoor);
        }

        printResultSummary(results, numberOfIterations);
    }

    private void putPrise(){
        prizeDoor = random.nextInt(DOOR_COUNT);
    }

    private void playerTurn(){
        player.makeSelection();
        playerDoor = player.getSelection();
    }

    private void showManTurn(){
        do {
            showman.makeSelection();
            showmanDoor = showman.getSelection();
        } while (showmanDoor == playerDoor || showmanDoor == prizeDoor);
    }

    private void playerSecondTurn(){
        for (int i = 0;; i++)
            if (i != playerDoor && i != showmanDoor) {
                playerDoor = i;
                return;
            }
    }

    private void printResultSummary(HashMap<Integer, Boolean> results, int numberOfIterations){
        if (results == null) return;

        long winsCount = results.values().stream().filter(result -> result).count();
        float winsPercent = (float) (winsCount * 100) / numberOfIterations;
        System.out.printf(String.format("Player wins statistic in percents: %f\n", winsPercent));
    }
}

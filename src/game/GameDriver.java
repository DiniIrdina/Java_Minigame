package game;

import java.util.Scanner;

/**
 * The main class for the Jurassic World game.
 *
 */
public class GameDriver {
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Welcome to Jurassic Park Simulator! %n" +
                              "Please select your desired game mode: %n" +
                              "1. Challenge %n" +
                              "2. Sandbox %n" +
                              "3. Quit %n");
            choice = scanner.nextInt();
            if (choice == 1){
                challengeSettings();

            }
            else {

            }
            WorldBuilder worldBuilder = new WorldBuilder();
            worldBuilder.generateMaps();
        } while (choice!=3);
    }

    public static void challengeSettings(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf(
                        "You have selected challenge! In this game mode you can set the winning conditions for the game.%n" +
                        "If the determined conditions are not met, you will lose the game!%n" +
                        "Please tweak the settings for EcoPoints and Number of Moves.%n" +
                        "Starting from EcoPoints,%n" +
                        "Please Enter the amount of EcoPoints required to win:  %n");
        int entered_ecopoints = scanner.nextInt();
        System.out.printf(
                        "Great! Your input for EcoPoints win condition is %d%n" +
                        "Now, %n" + "Please Enter the maximum number of moves required before the game ends:  %n",entered_ecopoints);
        int entered_num_moves = scanner.nextInt();
        System.out.println("Your input for maximum number of moves is " + entered_num_moves);

    }
}

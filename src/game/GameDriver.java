package game;

import java.util.Scanner;

/**
 * The main class for the Jurassic World game.
 *
 */
public class GameDriver {
    private static int challengeTurn;
    private static int challengeEcoPoints;
    private static boolean challengeMode = false;

    public static void main(String[] args) {
        int choice;
        boolean user_input_format = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Welcome to Jurassic Park Simulator! %n" +
                    "Please select your desired game mode: %n" +
                    "1. Challenge %n" +
                    "2. Sandbox %n" +
                    "3. Quit %n");
            choice = scanner.nextInt();
            if (choice <=0 || choice > 3){
                user_input_format = false;
            }
            else if (choice == 1) {
                challengeMode = true;
                challengeSettings();
            }
            else if (choice == 2) {
                System.out.println("You have selected sandbox! Please enjoy the game!");
            }
            else if (choice == 3) {
                break;
            }

            WorldBuilder worldBuilder = new WorldBuilder();
            worldBuilder.generateMaps();
            System.out.println();
        } while (user_input_format);
    }

    public static void challengeSettings(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf(
                        "You have selected challenge! In this game mode you can set the winning conditions for the game.%n" +
                        "If the determined conditions are not met, you will lose the game!%n" +
                        "Please tweak the settings for EcoPoints and Number of Moves.%n" +
                        "Starting from EcoPoints,%n" +
                        "Please Enter the amount of EcoPoints required to win:  %n");
        challengeEcoPoints = scanner.nextInt();
        System.out.printf(
                        "Great! Your input for EcoPoints win condition is %d%n" +
                        "Now, %n" + "Please Enter the maximum number of moves required before the game ends:  %n",challengeEcoPoints);
        challengeTurn = scanner.nextInt();
        System.out.println("Your input for maximum number of moves is " + challengeTurn);

    }

    public static boolean isChallengeMode(){
        return challengeMode;
    }

    public static int getChallengeTurn(){
        return challengeTurn;
    }

    public static void setChallengeTurn(int turn){
        challengeTurn = turn;
    }

    public static int getChallengeEcoPoints(){
        return challengeEcoPoints;
    }

    public static void setChallengeEcoPoints(int points){
        challengeEcoPoints = points;
    }

}

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepPlaying = true;

        while (keepPlaying) {
            Random rand = new Random();
            int numberToGuess = rand.nextInt(100) + 1;
            int tries = 0;
            int maxTries = 5;

            System.out.println("Guess a number between 1 and 100.");

            while (tries < maxTries) {
                System.out.print("Your guess: ");
                String input = scanner.nextLine();

                try {
                    int guess = Integer.parseInt(input);
                    tries++;

                    if (guess == numberToGuess) {
                        System.out.println("You got it in " + tries + " tries!");
                        break;
                    } else if (guess < numberToGuess) {
                        System.out.println("Too low.");
                    } else {
                        System.out.println("Too high.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            if (tries == maxTries) {
                System.out.println("Out of tries! The number was " + numberToGuess + ".");
            }

            System.out.print("Play again? (yes/no): ");
            String again = scanner.nextLine().trim().toLowerCase();
            keepPlaying = again.equals("yes");
        }

        scanner.close();
    }
}
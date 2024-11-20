package unit12_yahtzee;

import java.util.*;

public class Unit12_Yahtzee {

    static Scanner console = new Scanner(System.in);

    // ========================================================
    // 1's, 2's ... 6's - calculate the score for an Upper Section category  
    // score is the sum of dice of the given category
    // So, if the dice are 2 4 4 4 5 
    // the category_score(dice, 3) = 3 x 4 = 12
    // the category_score(dice, 2) = 1 x 2 = 2
    // the category_score(dice, 6) = 0 x 6 = 0
    // ========================================================
    public static int category_score(ArrayList<Integer> dice, int category) {
        int score = 0;
        // ------ add you code here 

        return score;
    }
    
    // ========================================================
    // THREE OF A KIND - Check if there are three dice of the same value.  
    // score is the sum of all of the dice or zero if there is not 3-of-a-kind
    // ========================================================
    public static int threeOfAKind(ArrayList<Integer> dice) {
        Integer score = 0;
        // ------ add you code here 
        
        return score; 
    }
    
    // ========================================================
    // FOUR OF A KIND - Check if there are three dice of the same value.  
    // score is the sum of all of the dice or zero if not 4-of-a-kind
    // ========================================================
    public static int fourOfAKind(ArrayList<Integer> dice) {
        Integer score = 0;
        // ------ add you code here 
        
        return score; 
    }
    
        
    // ========================================================
    // YAHTZEE - Check if all 5 dice match.  
    // score 50 or zero 
    // ========================================================
    public static int yahtzee(ArrayList<Integer> dice) {
        Integer score = 0;
        // ------ add you code here 
        
        return score; 
    }
    
    // ========================================================
    // FULL HOUSE - Check if there are 3 dice of one value and 2 dice of another value  
    // score 25 or zero 
    // ========================================================
    public static int fullHouse(ArrayList<Integer> dice) {
        Integer score = 0;
        // ------ add you code here 
        
        return score; 
    }
    
    // ========================================================
    // SMALL STRAIGHT - Check if there are dice in sequential order.  
    // score 30 or zero 
    // ========================================================
    public static int smallStraight(ArrayList<Integer> dice) {
        Integer score = 0;
        // ------ add you code here 
        
        return score; 
    }
    
    // ========================================================
    // LARGE STRAIGHT - Check if all 5 dice are in sequential order.  
    // score 40 or zero 
    // ========================================================
    public static int largeStraight(ArrayList<Integer> dice) {
        Integer score = 0;
        // ------ add you code here 
        
        return score; 
    }

    // ========================================================
    // CHANCE - used for any set of dice  
    // score is the sum of the dice
    // ========================================================
    public static int chance(ArrayList<Integer> dice) {
        Integer score = 0;
        // ------ add you code here 
        
        return score; 
    }
        
    // --------------------------------------------------------
    // countValue - utility method to count how many die of a given value are in dice
    // --------------------------------------------------------
    public static int countValue(ArrayList<Integer> dice, int value) {
        int count = 0;
        for (int die : dice) {
            if (die == value) {
                count = count + 1;
            }
        }
        return count;
    }
    
    // --------------------------------------------------------
    // sumDice - utility method to total the values in dice
    // --------------------------------------------------------
    public static int sumDice (ArrayList<Integer> dice) {
        int sum = 0;
        for (int die : dice) {
            sum = sum + die;
        }
        return sum;
    }
    
    // --------------------------------------------------------
    // To sort the dice, use the following
    //     Collections.sort (dice);
    // --------------------------------------------------------

    public static void main(String[] args) {
        ArrayList<Integer> dice = new ArrayList<>();
        ArrayList<Integer> upperScorePad = new ArrayList<>(Collections.nCopies(6, 0)); // Upper section scores
        ArrayList<Boolean> upperUsed = new ArrayList<>(Collections.nCopies(6, false)); // Upper section used status

        ArrayList<Integer> lowerScorePad = new ArrayList<>(Collections.nCopies(7, 0)); // Lower section scores
        ArrayList<Boolean> lowerUsed = new ArrayList<>(Collections.nCopies(7, false)); // Lower section used status

        System.out.println("Welcome to Yahtzee");

        // Game loop for 13 rounds (6 upper + 7 lower categories)
        for (int round = 0; round < 13; round++) {
            // Start with new dice for each round
            init(dice);

            // Roll and re-roll dice
            System.out.println("Your initial dice are:");
            display_dice(dice);
            re_roll(dice);
            System.out.println("Your final dice are:");
            display_dice(dice);

            // Display the full score pad and ask where to score
            display_fullScorePad(upperScorePad, upperUsed, lowerScorePad, lowerUsed);

            System.out.println("Where would you like to score this roll?");
            System.out.println("Enter a number between 1-6 for Upper Section, or 7-13 for Lower Section:");
            int category = console.nextInt();

            // Validate input and ensure the category has not been used
            while (!isValidCategory(category, upperUsed, lowerUsed)) {
                System.out.println("Invalid input or category already used. Please enter a valid number (1-13):");
                category = console.nextInt();
            }

            // Score the roll in the chosen category
            if (category >= 1 && category <= 6) { // Upper Section
                upperScorePad.set(category - 1, category_score(dice, category));
                upperUsed.set(category - 1, true);
            } else if (category >= 7 && category <= 13) { // Lower Section
                scoreLowerSection(dice, category - 7, lowerScorePad);
                lowerUsed.set(category - 7, true);
            }

            // Display updated score pad
            display_fullScorePad(upperScorePad, upperUsed, lowerScorePad, lowerUsed);
        }

        // Calculate and display the total score
        int totalUpper = calculate_totalScore(upperScorePad);
        int totalLower = calculate_totalScore(lowerScorePad);
        System.out.println("Game over! Your total score is: " + (totalUpper + totalLower));
    }

    // Method to initialize the dice with random values
    public static void init(ArrayList<Integer> dice) {
        dice.clear();
        for (int i = 0; i < 5; i++) {
            dice.add((int) (Math.random() * 6) + 1); // Random values between 1 and 6
        }
    }

    // Method to re-roll dice based on user input
    public static void re_roll(ArrayList<Integer> dice) {
        for (int i = 0; i < dice.size(); i++) {
            System.out.println("Re-roll die " + (i + 1) + " which is a " + dice.get(i) + " (y/n)?");
            String answer = console.next();
            if (answer.equalsIgnoreCase("y")) {
                dice.set(i, (int) (Math.random() * 6) + 1);
            }
        }
    }

    // Method to display the dice
    public static void display_dice(ArrayList<Integer> dice) {
        for (int value : dice) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

// Method to score a category in the Lower Section
    public static void scoreLowerSection(ArrayList<Integer> dice, int categoryIndex, ArrayList<Integer> lowerScorePad) {
        switch (categoryIndex) {
            case 0: // Three of a Kind
                lowerScorePad.set(categoryIndex, threeOfAKind(dice));
                break;
            case 1: // Four of a Kind
                lowerScorePad.set(categoryIndex, fourOfAKind(dice));
                break;
            case 2: // Full House
                lowerScorePad.set(categoryIndex, fullHouse(dice));
                break;
            case 3: // Small Straight
                lowerScorePad.set(categoryIndex, smallStraight(dice));
                break;
            case 4: // Large Straight
                lowerScorePad.set(categoryIndex, largeStraight(dice));
                break;
            case 5: // Yahtzee
                lowerScorePad.set(categoryIndex, yahtzee(dice));
                break;
            case 6: // Chance
                lowerScorePad.set(categoryIndex, chance(dice));
                break;
        }
    }

    // Method to display the full score pad
    public static void display_fullScorePad(ArrayList<Integer> upperScorePad, ArrayList<Boolean> upperUsed,
            ArrayList<Integer> lowerScorePad, ArrayList<Boolean> lowerUsed) {
        System.out.println("\nScore Pad:");

        // Upper Section
        System.out.println("Upper Section:");
        for (int i = 0; i < upperScorePad.size(); i++) {
            String status = upperUsed.get(i) ? " (USED)" : "";
            System.out.println("Line " + (i + 1) + "s: " + upperScorePad.get(i) + status);
        }

        // Lower Section
        System.out.println("Lower Section:");
        String[] lowerCategories = {"Three of a Kind", "Four of a Kind", "Full House", "Small Straight",
            "Large Straight", "Yahtzee", "Chance"};
        for (int i = 0; i < lowerScorePad.size(); i++) {
            String status = lowerUsed.get(i) ? " (USED)" : "";
            System.out.println("Line " + (i + 7) + " --- " + lowerCategories[i] + ": " + lowerScorePad.get(i) + status);
        }
    }

    // Method to validate if a category is valid and not used
    public static boolean isValidCategory(int category, ArrayList<Boolean> upperUsed, ArrayList<Boolean> lowerUsed) {
        if (category >= 1 && category <= 6) {
            return !upperUsed.get(category - 1);
        } else if (category >= 7 && category <= 13) {
            return !lowerUsed.get(category - 7);
        }
        return false;
    }

    // Method to calculate the total score
    public static int calculate_totalScore(ArrayList<Integer> scorePad) {
        return scorePad.stream().mapToInt(Integer::intValue).sum();
    }
}

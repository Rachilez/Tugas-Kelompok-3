import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;

public class CoepoeWordPuzzle {
    public static int maxLevel = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> usedWords = new HashSet<>();

        // Peraturan Game
        System.out.println("Coepoe Word Puzzle");
        System.out.println("===================");
        System.out.println("\nRules:");
        System.out.println("1. Create a word using given characters, min 3 characters & max 6 characters.");
        System.out.println("2. Each level, You have 10 chances to answer correctly.");
        System.out.println("3. To get through to the next level, You have to score 70 points each level.\n");

        char ulang;

        int level = 1;
        int[] levelScores = new int[maxLevel];

        while (level <= maxLevel) {
            int levelScore = playLevel(scanner, usedWords, level);
            levelScores[level - 1] = levelScore;

            // Output Jika Score diatas 70
            if (levelScore >= 70) {
                int correctAnswers = levelScore / 10;
                System.out.println("\nYou had answered 10 times with " + correctAnswers + " right answers..\n");
                System.out.println("Correct Answers:");
                printCorrectAnswers(level);
                level++;

            } else {
                // output jika skor dibawah 70
                System.out.print("\nYou Lose!! Try Again..\nDo you want to retry [y/t]: ");
                ulang = scanner.next().charAt(0);
                if (ulang == 't' || ulang == 'T') {
                    break;
                }
            }
        }
        // output jika menyelesaikan 3 level
        if (level > maxLevel) {
            int overallScore = getTotalScore(levelScores); // Menjumlahkan Total Score
            System.out.println("\nOverall Score: " + overallScore);
            System.out.println("You Win!!");
            System.out.print("Press ENTER to exit.."); // Menunggu user menekan tombol "Enter"
            scanner.nextLine();
        }

        scanner.close();
    }

    public static int playLevel(Scanner scanner, HashSet<String> usedWords, int level) {
        int correctAnswers = 0;

        // Array Huruf Dalam game
        String[][] lettersPerLevel = {
                { "d", "e", "t", "t", "l", "i" },
                { "s", "e", "c", "a", "e", "n" },
                { "h", "k", "r", "n", "e", "o" },
        };

        System.out.println("\nLevel " + level);
        System.out.println("--------");
        printLetters(lettersPerLevel[level - 1]);

        for (int attempt = 1; attempt <= 10; attempt++) {
            System.out.print(attempt + " > Your answer: ");
            String word = scanner.nextLine();

            if (word.length() >= 3 && word.length() <= 6) {
                // Output Jika kata yang dimasukan sudah ada
                if (usedWords.contains(word)) {
                    System.out.println("You had already typed this word before . .");

                } else if (isWordPossible(word, lettersPerLevel[level - 1])) {
                    correctAnswers++;
                    usedWords.add(word);
                    System.out.println("#Right. Score: " + correctAnswers * 10);

                    // Output Jika kata yang dimasukan salah
                } else {
                    System.out.println("Invalid word. The word can only use the given letters.");
                }
                // Output Jika kata kurang dari 3 huruf atau lebih dari 6 huruf
            } else {
                System.out.println("Word length should be between 3 and 6 characters.");
            }
        }

        return correctAnswers * 10;
    }

    // Fungsi untuk cek apakah kata menggunakan huruf yang diberikan
    public static boolean isWordPossible(String word, String[] letters) {
        HashSet<String> letterSet = new HashSet<>(Arrays.asList(letters));

        for (char c : word.toCharArray()) {
            if (!letterSet.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    // Array Kata Yang Benar
    public static void printCorrectAnswers(int level) {
        if (level == 1) {
            String[] correctAnswers = { "die", "led", "lei", "let", "lid", "lie", "lit", "tie", "deli", "diet", "edit",
                    "\nidle", "lied", "tide", "tied", "title", "tilt", "tilde", "tiled", "titled" };
            printWords(correctAnswers);

        } else if (level == 2) {
            String[] correctAnswers = { "seance", "cease", "scene", "acne", "sane", "scan", "cane", "case", "seen",
                    "\nease", "cans", "anes", "aces", "sac", "sea", "see", "can", "ace", "sec", "ane", "nae", "ens",
                    "sen" };
            printWords(correctAnswers);

        } else if (level == 3) {
            String[] correctAnswers = { "honker", "hooker", "hero", "heron", "honk", "hoke", "kern", "horn", "hone",
                    "\nnor", "hen", "her", "hoe", "hoe", "hon", "ore", "one", "ook", "eon", "ken", "kern" };
            printWords(correctAnswers);
        }
    }

    // method untuk print huruf pada game
    public static void printLetters(String[] letters) {
        for (int i = 0; i < letters.length; i++) {
            if (i == 0) {
                System.out.print(letters[i]);
            } else {
                System.out.print("\t" + letters[i]);
            }
        }
        System.out.println();
    }

    // method untuk print kata yang benar
    public static void printWords(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                System.out.print(words[i]);
            } else {
                System.out.print(" " + words[i]);
            }
        }
        System.out.println();
    }

    // Method untuk Menjumlahkan Total Score semua level
    public static int getTotalScore(int[] levelScores) {
        int totalScore = 0;
        for (int score : levelScores) {
            totalScore += score;
        }
        return totalScore;
    }
}

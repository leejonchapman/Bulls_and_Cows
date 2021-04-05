package bullscows;

import java.util.*;

public class Main {
    static String secretCode = null;
    static String usercode = null;
    static int bulls = 0;
    static int cows = 0;
    static int turn = 1;
    static boolean isSecretCodeAllTheSame = false;

    public static void main(String[] args) {

        secretCode = setSecretCode();

        while (bulls < 4) {
            usercode = setUserGuess();
            getBullsAndCows();
            getResult();
        }
    }

    public static String setSecretCode() {
        Scanner scSecretCodeLength = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int secretCodeLength = 0;
        try {
            secretCodeLength = scSecretCodeLength.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error");
            System.exit(0);
        }

        Scanner scSecretCodeSymbolLength = new Scanner(System.in);
        System.out.println("Input the number of possible symbols in the code:");
        int secretCodeSymbolLength = scSecretCodeSymbolLength.nextInt();
        ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<Character> charList = new ArrayList<Character>();

        if (secretCodeSymbolLength > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }

        if (secretCodeSymbolLength - secretCodeLength < 1){
            System.out.println("Error");
            System.exit(0);
        }
        if (secretCodeLength == 0){
            System.out.println("Secret code length cannot be zero");
            System.exit(0);
        }

        if (scSecretCodeSymbolLength.equals(0)) {
            for (int i = 0; i < 9; i++) {
                list.add(i);
            }
        } else {

            for (int i = 0; i < 9; i++) {
                list.add((i));
            }

            char c;
            for (c = 'a'; c <= 'z'; ++c) {
                list.add(c);
            }
        }


        String res = String.valueOf(list.subList(0, secretCodeLength));
        res = res.replace("[", "").replace("]", "").replace(", ", "");

        char[] starz = new char[secretCodeLength];
        for (int i = 0; i < secretCodeLength; i++) {
            starz[i] = '*';
        }

        if (secretCodeSymbolLength == 0) {
            System.out.println("The secret is prepared: " + Arrays.toString(starz).replace("[", "").replace("]", "").replace(", ", "") + " (0-9).");

        } else {
            if (secretCodeSymbolLength == 36){
            System.out.println("The secret is prepared: " + Arrays.toString(starz).replace("[", "").replace("]", "").replace(", ", "") + " (0-9, a-z).");
            } else {
            System.out.println("The secret is prepared: " + Arrays.toString(starz).replace("[", "").replace("]", "").replace(", ", "") + " (0-9, a-f).");
                }
            }


        return res;
    }

    public static String setUserGuess() {
        if (turn == 1) {
            System.out.println("Okay, let's start a game!");
        }
        System.out.println("Turn " + turn);

        Scanner scUserGuess = new Scanner(System.in);
        String tmpUserGuess = scUserGuess.next();

        turn++;
        return tmpUserGuess.toLowerCase(Locale.ROOT);
    }

    public static void getBullsAndCows() {
        for (int i = 0; i < secretCode.length(); i++) {
            for (int j = 0; j < secretCode.length(); j++) {
                boolean b = secretCode.charAt(i) == usercode.charAt(j);
                if (b && i == j) {
                    bulls++;
                } else if (b) {
                    cows++;
                }

            }
        }
    }

    public static void getResult() {


            if (bulls == secretCode.length()) {
                System.out.println("Congratulations! You guessed the secret code.");
                System.out.println("Grade: " + bulls + " bulls");
                System.exit(0);
            } else {
                System.out.println("Grade: " + bulls + " bulls and " + cows + " cow");
            }
            bulls = 0;
            cows = 0;
            System.out.println(secretCode);
        }
    }


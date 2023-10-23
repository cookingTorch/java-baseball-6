package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
    private static final int NUMBER_LENGTH = 3;
    private static final String CONTINUE_GAME = "1";
    private static final String EXIT_GAME = "2";

    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        do {
            playSingleRound();
        } while (getContinueGame());
    }

    private static void playSingleRound() {
        int[] answer, inputNumber;

        answer = generateRandomAnswer();
        do {
            inputNumber = getInputNumber();
        } while (!getResult(answer, inputNumber));
        System.out.print(NUMBER_LENGTH);
        System.out.println("개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    private static int[] generateRandomAnswer() {
        int[] answer;

        answer = new int[NUMBER_LENGTH];
        do {
            for (int i = 0; i < NUMBER_LENGTH; i++) {
                answer[i] = Randoms.pickNumberInRange(1, 9);
            }
        } while (duplicateNumber(answer));
        return answer;
    }

    private static boolean duplicateNumber(int[] number) {
        for (int num = 1; num <= 9; num++) {
            if (countNum(num, number) > 1) {
                return true;
            }
        }
        return false;
    }

    private static int countNum(int num, int[] number) {
        int cnt;

        cnt = 0;
        for (int n : number) {
            if (n == num) {
                cnt++;
            }
        }
        return cnt;
    }

    private static int[] getInputNumber() {
        String input;

        System.out.print("숫자를 입력해주세요 : ");
        input = Console.readLine();
        if (!isValidInput(input)) {
            throw (new IllegalArgumentException());
        }
        return (stringToIntArray(input));
    }

    private static boolean isValidInput(String input) {
        return (isNumberLength(input)
                && isInRange(input)
                && !duplicateInput(input));
    }

    private static boolean isNumberLength(String input) {
        return (input.length() == NUMBER_LENGTH);
    }

    private static boolean isInRange(String input) {
        for (char ch : input.toCharArray()) {
            if (!('1' <= ch && ch <= '9')) {
                return false;
            }
        }
        return true;
    }

    private static boolean duplicateInput(String input) {
        for (char num = '1'; num <= '9'; num++) {
            if (countChar(num, input) > 1) {
                return true;
            }
        }
        return false;
    }

    private static int countChar(char num, String input) {
        int cnt;

        cnt = 0;
        for (char ch : input.toCharArray()) {
            if (ch == num) {
                cnt++;
            }
        }
        return cnt;
    }

    private static int[] stringToIntArray(String input) {
        int[] inputNumber;

        inputNumber = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            inputNumber[i] = input.charAt(i) - '0';
        }
        return inputNumber;
    }

    private static boolean getResult(int[] answer, int[] inputNumber) {
        int ball;
        int strike;

        strike = getStrike(answer, inputNumber);
        ball = getCount(answer, inputNumber) - strike;
        printResult(ball, strike);
        return (strike == NUMBER_LENGTH);
    }

    private static int getStrike(int[] answer, int[] inputNumber) {
        int strike;

        strike = 0;
        for (int i = 0; i < inputNumber.length; i++) {
            if (inputNumber[i] == answer[i]) {
                strike++;
            }
        }
        return strike;
    }

    private static int getCount(int[] answer, int[] inputNumber) {
        int count;

        count = 0;
        for (int number : inputNumber) {
            if (isInArray(number, answer)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isInArray(int number, int[] arr) {
        for (int arrNum : arr) {
            if (number == arrNum) {
                return true;
            }
        }
        return false;
    }

    private static void printResult(int ball, int strike) {
        if ((ball == 0) && (strike == 0)) {
            System.out.println("낫싱");
            return ;
        }
        printBall(ball);
        if ((ball > 0) && (strike > 0)) {
            System.out.print(" ");
        }
        printStrike(strike);
        System.out.println();
    }

    private static void printBall(int ball) {
        if (ball > 0) {
            System.out.print(ball);
            System.out.print("볼");
        }
    }

    private static void printStrike(int strike) {
        if (strike > 0) {
            System.out.print(strike);
            System.out.print("스트라이크");
        }
    }

    private static boolean getContinueGame() {
        String choice;

        System.out.print("게임을 새로 시작하려면 " + CONTINUE_GAME);
        System.out.println(", 종료하려면 " + EXIT_GAME + "를 입력하세요.");
        choice = Console.readLine();
        if (!isValidChoice(choice)) {
            throw (new IllegalArgumentException());
        }
        return (choice.equals(CONTINUE_GAME));
    }

    private static boolean isValidChoice(String choice) {
        return (choice.equals(CONTINUE_GAME)
                || choice.equals(EXIT_GAME));
    }
}

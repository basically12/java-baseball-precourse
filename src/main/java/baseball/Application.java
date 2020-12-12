package baseball;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import utils.RandomUtils;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        startGame(scanner);

    }

    private static void startGame(Scanner scan) {

        List<Integer> goal = RandomUtils.makeGoal();

        while (true) {
            String userAnswer = getAnswerFromUser(scan);

            // 정답인경우
            if (isAnswerRight(userAnswer, goal)) {
                int continueFlag = askContinuation(scan);
                if (continueFlag == 1) {
                    goal = RandomUtils.makeGoal();
                    continue;
                } else if (continueFlag == 2) {
                    break;
                }
            }

        }

    }

    private static String getAnswerFromUser(Scanner scan) {

        System.out.print("숫자를 입력해주세요 : ");
        String userAnswer = scan.next();

        checkAnswerIsSuitable(userAnswer);

        return userAnswer;
    }


    // 적합한 답인지
    private static void checkAnswerIsSuitable(String userAnswer) {
        int lengthOfAnswer = 3;

        if (userAnswer.matches("[1-9]+") && userAnswer.length() == lengthOfAnswer) {
            hasDuplication(userAnswer);
            return;
        }
        throw new IllegalArgumentException();
    }


    private static boolean isAnswerRight(String userAnswer, List<Integer> goal) {
        StringBuilder hint = new StringBuilder();

        int strike = countForStrike(userAnswer, goal);
        int ball = countForBall(userAnswer, goal);

        if (strike == 0 && ball == 0) {
            System.out.println("낫싱");
            return false;
        }

        else if (strike == 0) {
            System.out.println(ball + "볼");
            return false;
        }

        else if (ball == 0) {
            System.out.println(strike + "스트라이크");
            if (strike != 3) {
                return false;
            } else {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
                return true;
            }
        } else {
            hint.append(strike + "스트라이크");
            hint.append(" ");
            hint.append(ball + "볼");
            System.out.println(hint.toString());

            return false;
        }


    }


    private static int countForStrike(String userAnswer, List<Integer> goal) {
        int count = 0;

        for (int i = 0, lengthOfUserAnswer = userAnswer.length(); i < lengthOfUserAnswer; i++) {
            if (Character.getNumericValue(userAnswer.charAt(i)) == goal.get(i)) {
                count++;
            }
        }

        return count;
    }

    private static int countForBall(String userAnswer, List<Integer> goal) {
        int count = 0;

        for (int i = 0, lengthOfUserAnswer = userAnswer.length(); i < lengthOfUserAnswer; i++) {
            for (int b = 0; b < lengthOfUserAnswer; b++) {
                int currentNum = Character.getNumericValue(userAnswer.charAt(i));
                if (currentNum == goal.get(b) && i != b) {
                    count++;
                }
            }
        }
        return count;

    }


    private static int askContinuation(Scanner scan) {

        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        int choice = 0;

        try {
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                case 2:
                    return choice;
                default:
                    throw new IllegalArgumentException();
            }

        } catch (IllegalArgumentException e) {
        }

        return choice;

    }

    public static void hasDuplication(String answer)
    {
        char[] answerArr = answer.toCharArray();
        Arrays.sort(answerArr);
        
        //중복확인
        for(int i = 0; i < answerArr.length-1; i++){
            if(answerArr[i] == answerArr[i+1]){
                throw new IllegalArgumentException();
            }                       
        }
       
    }


}

package EquationSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class EquationSolver {
    static String PLUS_OPERATION = "+";

    public static void main(String[] args) {
        String equation = "5*10*200";
        String equation1 = "5+10+200";
        String equation2 = "1*2*3+1+10+100+8*10"; // 6 + 111 + 80 = 197
        int result2 = 197;

        System.out.println(solve1sTry(equation));
        System.out.println(solveUsingSplitAndJoin(equation));

        System.out.println(solve1sTry(equation1));
        System.out.println(solveUsingSplitAndJoin(equation1));

        System.out.println(solve1sTry(equation2));
        System.out.println(solveUsingSplitAndJoin(equation2));

        if (solve1sTry(equation2) == result2) {
            System.out.println("Good!");
        } else {
            System.out.println("Oops!");
        }
    }

    // this is the functional programming style. very clean and uses String.split function
    private static int solveUsingSplitAndJoin(String equation) {
        String plusSign = "\\+";
        String multiplySign = "\\*";
        Function<String, Integer> multiplyResult = s -> Arrays.stream(s.split(multiplySign))
                .map(numStr -> Integer.valueOf(numStr))
                .reduce(1, (num1, num2) -> num1 * num2);
        BinaryOperator<Integer> addResult = (n1, n2) -> n1 + n2;

        String[] multiplyExpressions = equation.split(plusSign);

        return Arrays.stream(multiplyExpressions)
                .map(multiplyResult)
                .reduce(0, addResult);

    }

    private static int solve1sTry(String equation) {

        List<Integer> sumList = new ArrayList<>();
        Parser parser = new Parser(equation);
        while (parser.inProgress()) {
            Integer currentNumber = parser.nextNumber();
            String operationOnCurrentNumber = parser.nextOperation();
            if (operationOnCurrentNumber == null || operationOnCurrentNumber.equals(PLUS_OPERATION)) {
                sumList.add(currentNumber);
            } else {
                while (parser.inProgress()) {
                    Integer nextNumber = parser.nextNumber();
                    currentNumber = currentNumber * nextNumber;
                    String nextOperation = parser.nextOperation();
                    if (nextOperation == null || nextOperation.equals(PLUS_OPERATION)) {
                        break;
                    }
                }
                sumList.add(currentNumber);
            }
        }

        return sumList.stream().reduce(0, (num1, num2) -> num1 + num2);
    }



    private static class Parser {
        private final String equation;
        int currentIndex = 0;
        Parser(String equation) {
            this.equation = equation;
        }

        // returns null if the are no more numbers
        Integer nextNumber() {
            if (!inProgress()) {
                return null;
            }
            int beginIndex = currentIndex;
            while (inProgress() && equation.charAt(currentIndex) != '+' && equation.charAt(currentIndex) != '*') {
                currentIndex++;
            }
            int endIndex = currentIndex;
            String numStr = equation.substring(beginIndex, endIndex);
            return Integer.valueOf(numStr);
        }
        // returns null if there is no other operation
        String nextOperation() {
            if (!inProgress()) {
                return null;
            }
            char operation = equation.charAt(currentIndex);
            if (operation != '+' && operation != '*') {
                throw new IllegalStateException("cannot find next operation");
            }
            currentIndex++;
            return String.valueOf(operation);
        }

        boolean inProgress() {
            return currentIndex < equation.length();
        }
    }
}

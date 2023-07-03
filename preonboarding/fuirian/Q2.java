package Cow;

import java.util.Stack;

public class Q2 {
    public static String decryptCryptogram(String cryptogram) {
        Stack<Character> stack = new Stack<>();

        for (char ch : cryptogram.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String cryptogram = "sssssnonnnnwwwww";
        String result = decryptCryptogram(cryptogram);
        System.out.println(result);
    }
}


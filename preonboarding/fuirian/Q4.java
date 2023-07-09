package Cow;

public class Q4 {

	    public static String reverseWords(String word) {
	        StringBuilder result = new StringBuilder();

	        for (char ch : word.toCharArray()) {
	            if (Character.isUpperCase(ch)) {
	                result.append((char) ('Z' - (ch - 'A')));
	            } else if (Character.isLowerCase(ch)) {
	                result.append((char) ('z' - (ch - 'a')));
	            } else {
	                result.append(ch);
	            }
	        }

	        return result.toString();
	    }

	    public static void main(String[] args) {
	        String word = "hello";
	        String result = reverseWords(word);
	        System.out.println(result);
	    }
	


}

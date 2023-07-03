package Cow;

public class Q5 {
    public static int[] calculateChange(int money) {
        int[] denominations = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
        int[] result = new int[9];

        for (int i = 0; i < denominations.length; i++) {
            result[i] = money / denominations[i];
            money %= denominations[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int money1 = 60231;
        int[] result1 = calculateChange(money1);
        System.out.println(java.util.Arrays.toString(result1));

        int money2 = 15000;
        int[] result2 = calculateChange(money2);
        System.out.println(java.util.Arrays.toString(result2));
    }
}


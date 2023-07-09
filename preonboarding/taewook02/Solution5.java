import java.util.Arrays;

class Solution5 {
    private static final int FIFTY_THOUSAND = 50000;
    private static final int TEN_THOUSAND = 10000;
    private static final int FIVE_THOUSAND = 5000;
    private static final int ONE_THOUSAND = 1000;
    private static final int FIVE_HUNDRED = 500;
    private static final int ONE_HUNDRED = 100;
    private static final int FIFTY = 50;
    private static final int TEN = 10;
    private static final int ONE = 1;

    public static void main(String[] args) {
        int inp = Integer.parseInt(args[0]);
        int[] mList = {FIFTY_THOUSAND, TEN_THOUSAND, FIVE_THOUSAND, ONE_THOUSAND, FIVE_HUNDRED, ONE_HUNDRED, FIFTY, TEN, ONE};
        int[] res = new int[9];

        for (int i = 0; i < mList.length; i++) {
            int m = mList[i];
            res[i] = inp/m;
            inp %= m;
        }

        System.out.println(Arrays.toString(res));
    }
}

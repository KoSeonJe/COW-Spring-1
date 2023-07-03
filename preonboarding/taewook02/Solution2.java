class Solution2 {
    public static void main(String[] args) {
        String inp = args[0];
        boolean isDecoded = false;

        while (!isDecoded) {
            for (int i = 0; i < inp.length()-1; i++) {
                char tmp1 = inp.charAt(i);
                char tmp2 = inp.charAt(i+1);
                if (tmp1 == tmp2) {
                    inp = inp.substring(0, i) + inp.substring(i+2);
                    break;
                }
                if (i==inp.length()-2) {
                    isDecoded = true;
                    break;
                }
            }
        }
        System.out.println(inp);
    }
}

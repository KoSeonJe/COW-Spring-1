class Solution4 {
    private static final int ASCII_A_UPPERCASE = 65;
    private static final int ASCII_Z_UPPERCASE = 90;
    private static final int ASCII_A_LOWERCASE = 97;
    private static final int ASCII_Z_LOWERCASE = 122;

    public static void main(String[] args) {
        String inp = args[0];
        String res = "";

        for (int i = 0; i < inp.length(); i++) {
            int buff = inp.charAt(i);
            if (buff >= ASCII_A_UPPERCASE && buff <= ASCII_Z_UPPERCASE) {
                res += (char)(ASCII_Z_UPPERCASE - (buff - ASCII_A_UPPERCASE));
            } else if (buff >= ASCII_A_LOWERCASE && buff <= ASCII_Z_LOWERCASE) {
                res += (char)(ASCII_Z_LOWERCASE - (buff - ASCII_A_LOWERCASE));
            } else {
                res += (char)buff;
            }
        }

        System.out.println(res);
    }
}


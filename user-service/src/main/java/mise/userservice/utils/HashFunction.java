package mise.userservice.utils;

public class HashFunction {
    private static int PRIME = 31; // 选择一个较大的质数作为乘法因子

    public static String hash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * PRIME + str.charAt(i)) % Integer.MAX_VALUE;
        }
        return String.valueOf(hash);
    }
}

package util.service;

import java.util.Arrays;


public class MobilePrefix {
    public static int[] prefixes = new int[]{25, 29, 33, 44};
    
    public static boolean contains(int prefix) {
        return Arrays.asList(prefixes).contains(prefix);
    }
}

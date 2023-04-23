package backup;

import java.io.*;
import java.util.*;
public class CodeTestMain {

    public static void main(String[] args) throws Exception {
        int numbers[] = new int[10];
        String answer = "";
        String[] strNums = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            strNums[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strNums, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });

    }
}

package me.dev.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RomanNumbersProcessor {
    protected static final int MAX = 3000;

    public String toRoman(int number) {
        if (number <= 0 || number > MAX) {
            throw new IllegalArgumentException("Invalid range. Number must be [1,3000]");
        }

        final int[] val = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        final String[] roman = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder ans = new StringBuilder();
        for (int i = 0; number > 0; i++)
            while (number >= val[i]) {
                ans.append(roman[i]);
                number -= val[i];
            }
        return ans.toString();
    }

    public int toInteger(String roman) {
        if (Objects.isNull(roman) || roman.isBlank()) {
            throw new IllegalArgumentException("Roman string cannot be null nor blank");
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        roman = roman.toUpperCase();
        int result = 0, prevValue = 0;

        for (int i = 0; i < roman.length(); i++) {
            int currentValue = map.get(roman.charAt(i));

            if (i > 0 && currentValue > prevValue) {
                result -= 2 * prevValue;
            }

            result += currentValue;
            prevValue = currentValue;
        }

        return result;
    }

    public static void main(String[] args) {
        RomanNumbersProcessor test = new RomanNumbersProcessor();
        System.out.println(test.toRoman(14));
        System.out.println(test.toInteger("XIV"));
    }
}

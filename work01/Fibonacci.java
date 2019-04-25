package main.java.com.bigcabbage.work01;

import java.math.BigInteger;
import java.util.ArrayList;

public class Fibonacci {
    private static ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
    static {
        arr.add(new BigInteger("1"));
        arr.add(new BigInteger("1"));
        for (int i = 2; i < 200; i++) {
            arr.add(arr.get(i-1).add(arr.get(i-2)));
        }
    }
    public static BigInteger of(int index)
    {
        if(arr.size() < index)
        {
            for (int i = arr.size(); i < index; i++) {
                arr.add(arr.get(i-1).add(arr.get(i-2)));
            }
        }
        return arr.get(index-1);
    }
}

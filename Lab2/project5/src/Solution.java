import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public int[] sumEvenOdd(int[] array) {
        int sumeven = 0;
        int sumodd = 0;
        int[] arr = new int[2];
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 == 0){
            sumeven = sumeven + array[i];
            }
            else if (array[i] % 2 != 0){
            sumodd = sumodd + array[i];
            }
        }
        arr[0] = sumeven;
        arr[1] = sumodd;
        return (arr);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty()){
            arr = new int[]{};
        }
        else {
            for(int i = 0; i < s.length; i++)
               arr[i] = Integer.parseInt(s[i]);
        }
        int[] res = new Solution().sumEvenOdd(arr);
        System.out.print("[");
        for(int i = 0; i < res.length; i ++) {
            System.out.print(res[i]);
            if(i != 1)
                System.out.print(", ");
        }
        System.out.print("]");
    }
}

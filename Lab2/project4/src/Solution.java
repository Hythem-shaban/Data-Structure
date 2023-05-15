import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public double average(int[] array) {
        double avg = 0.0;
        double sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = array[i] + sum;
        }
        if (array.length == 0) return 0;
        else return avg = sum / (array.length);
        
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
    	String[] s = sin.split(", ");;
		int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else {
            for(int i = 0; i < s.length; i++)
        	   arr[i] = Integer.parseInt(s[i]);
        }
      	double res = new Solution().average(arr);
     	System.out.print( res);
    }
}

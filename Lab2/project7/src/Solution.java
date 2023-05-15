import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[][] transpose(int[][] array) {
        int[][] trans = new int[array.length][array.length];
    	for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++){
                trans[j][i] = array[i][j];
            }
        }
        return trans;
    }
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        String sin = sc1.nextLine().replaceAll("\\[|\\]\\[|\\]", "");
        String[] s = sin.split(", ");
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
        }
        int n = (int)Math.sqrt(s.length);
        int[][] arr = new int[n][n];
        if (s.length == 1 && s[0].isEmpty()){
            arr = new int[][]{};
        }
        else {
            for(int i = 0; i < (n); ++i){
                for(int j = 0; j < (n); ++j){
                    arr[i][j] = Integer.parseInt(s[i*(n) + j]);
                }
            }
        }
        int[][] res = new Solution().transpose(arr);
        System.out.print("[");
        if(res.length == 0)
            System.out.print("[");
        for(int i = 0; i < res.length; i++) {
            System.out.print("[");
            for(int j = 0; j < res.length; j++){
                System.out.print(res[i][j]);
                if(j != res.length - 1)
                    System.out.print(", ");
            }
            System.out.print("]");
            if(i != res.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");
        if(res.length == 0)
            System.out.print("]");
    }
}
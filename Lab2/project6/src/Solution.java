import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	public int[] moveValue(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value){
                for(int j = i; j < array.length - 1 ; ++j){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }  
            }
            else
            continue;
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value){
                for(int j = i; j < array.length - 1 ; ++j){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }  
            }
            else
            continue;
        }
        return array;
    }
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        String sin = sc1.nextLine().replaceAll("\\[|\\]", "");
        int value = sc1.nextInt();
        String[] s = sin.split(", ");;
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty()){
            arr = new int[]{};
        }
        else {
            for(int i = 0; i < s.length; i++)
               arr[i] = Integer.parseInt(s[i]);
        }
        int[] res = new Solution().moveValue(arr, value);
        System.out.print("[");
        for(int i = 0; i < res.length; i ++) {
            System.out.print(res[i]);
            if(i != s.length - 1)
                System.out.print(", ");
        }
        System.out.print("]");
        
    }
}
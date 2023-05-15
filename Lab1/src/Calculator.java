import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ICalculator {
	void add(int x, int y);
	void divide(int x, int y) throws RuntimeException;
}
public class Calculator implements ICalculator
{
    @Override
    public void add(int x, int y)
    {
        System.out.println(x + y);
    }
    @Override
    public void divide(int x, int y) throws ArithmeticException
    {
        float xx = x;
        float yy = y;
        try 
        {
            float z = x / y;
            System.out.println(xx / yy);
        } 
        catch (ArithmeticException e) {
            System.out.println("Error"); 
            
        }
        
    }
    public static void main(String[] args)
    {
        Calculator myCalculator = new Calculator();
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        String stdin = in.next();
        int y = in.nextInt();
        in.close();
        switch (stdin)
        {
            case "+": myCalculator.add(x,y);
            break;
            case "/": myCalculator.divide(x,y);
            break;
        }

    }
    
}
    
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IExpressionEvaluator {
  
/**
* Takes a symbolic/numeric infix expression as input and converts it to
* postfix notation. There is no assumption on spaces between terms or the
* length of the term (e.g., two digits symbolic or numeric term)
*
* @param expression infix expression
* @return postfix expression
*/ 
public String infixToPostfix(String expression); 
  
/**
* Evaluate a postfix numeric expression, with a single space separator
* @param expression postfix expression
* @return the expression evaluated value
*/ 
public int evaluate(String expression);
}

public class Evaluator implements IExpressionEvaluator {
    @Override
    public String infixToPostfix(String expression) {
        if(expression == null || expression.length() == 0){
            System.out.println("Error");
            System.exit(0);
        }
        if(expression.charAt(0) == '+' ||
           expression.charAt(0) == '*' ||
           expression.charAt(0) == '/' ||
           expression.charAt(0) == '^' ||
           expression.charAt(0) == ')'){
            System.out.println("Error");
            System.exit(0);
        }
        if(expression.charAt(expression.length() - 1) == '+' ||
           expression.charAt(expression.length() - 1) == '-' ||
           expression.charAt(expression.length() - 1) == '*' ||
           expression.charAt(expression.length() - 1) == '/' ||
           expression.charAt(expression.length() - 1) == '^' ||
           expression.charAt(expression.length() - 1) == '('){
            System.out.println("Error");
            System.exit(0);
        }
        for (int i = 0; i < expression.length() - 1; i++) {
            if(expression.charAt(i) == '+' && expression.charAt(i + 1) == '+'){
                System.out.println("Error");
                System.exit(0);
            }
            if(expression.charAt(i) == '*' && expression.charAt(i + 1) == '*'){
                System.out.println("Error");
                System.exit(0);
            }
            if(expression.charAt(i) == '/' && expression.charAt(i + 1) == '/'){
                System.out.println("Error");
                System.exit(0);
            }
            if(expression.charAt(i) == '^' && expression.charAt(i + 1) == '^'){
                System.out.println("Error");
                System.exit(0);
            }
            if(expression.charAt(i) == '(' && expression.charAt(i + 1) == ')'){
                System.out.println("Error");
                System.exit(0);
            }
            if(Character.isLetter(expression.charAt(i)) && Character.isLetter(expression.charAt(i+1))){
                System.out.println("Error");
                System.exit(0);
            }
            if(Character.isLetter(expression.charAt(i)) && Character.isDigit(expression.charAt(i+1))){
                System.out.println("Error");
                System.exit(0);
            }
            if(Character.isDigit(expression.charAt(i)) && Character.isLetter(expression.charAt(i+1))){
                System.out.println("Error");
                System.exit(0);
            }
            if(Character.isLetter(expression.charAt(i)) && (expression.charAt(i+1) == '(')){
                System.out.println("Error");
                System.exit(0);
            }
            if(!Character.isLetterOrDigit(expression.charAt(i)) && (expression.charAt(i+1) == ')')){
                System.out.println("Error");
                System.exit(0);
            }
            if((expression.charAt(i) == '(') && !Character.isLetterOrDigit(expression.charAt(i+1))){
                System.out.println("Error");
                System.exit(0);
            }
        }
        int counter1 = 0, counter2 = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ')') {
                counter1++;
            }
            if (expression.charAt(i) == '(') {
                counter2++;
            }
        }
        if (counter1 != counter2) {
            System.out.println("Error");
            System.exit(0);
        }
        String postfix = new String("");
        MyStack operators = new MyStack();
        for(int i = 0; i < expression.length(); i++) {
            if( expression.charAt(i) != '+' && 
                expression.charAt(i) != '-' &&
                expression.charAt(i) != '*' && 
                expression.charAt(i) != '/' && 
                expression.charAt(i) != '^' &&
                expression.charAt(i) != '(' &&
                expression.charAt(i) != ')') {
            postfix += expression.charAt(i);
            continue;
            }
            else if(expression.charAt(i) == '('){
                operators.push(expression.charAt(i));
            }
            else if(expression.charAt(i) == ')'){
                while((char)operators.peek() != '('){
                    postfix += operators.pop();
                }
                operators.pop();
            }
            else if(operators.isEmpty() && (expression.charAt(i) == '-')){
                if(i>=1){
                    if(expression.charAt(i+1) == '-'){
                        operators.push('+');
                    }
                }
                else if(i == 0){
                    operators.push('-');
                }
            }
            else if(expression.charAt(i) == '-' && expression.charAt(i+1) == '-'){
                continue;
            }
            else if(expression.charAt(i) == '-' && expression.charAt(i-1) == '-'){
                if((char)operators.peek() == '+')
                    continue;
                if(Prec((char)operators.peek()) > Prec(expression.charAt(i)))
                    continue;
                operators.push('+'); 
            }
            else{
                while(!operators.isEmpty() && !((char)operators.peek() == '(') && Prec(expression.charAt(i)) <= Prec((char)operators.peek()))
                    postfix += operators.pop(); 
                operators.push(expression.charAt(i));
            }  
        }
        while (!operators.isEmpty()){  
                postfix += operators.pop();
                if(expression.charAt(0) == '-' && expression.charAt(1) == '-'){
                    break;
                }
        }
        return postfix;
    }
    static int Prec(char input)
    {
        switch (input) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '(':
            case ')':
                return 4;
        }
        return 0;
    }
    static Scanner sc = new Scanner(System.in);
    static String infix = sc.nextLine();
    MyStack eval = new MyStack();
    String aInputValue = sc.nextLine();
    String [] aValue = aInputValue.split("=");
    int a = Integer.parseInt(aValue[1]);
    String bInputValue = sc.nextLine();
    String [] bValue = bInputValue.split("=");
    int b = Integer.parseInt(bValue[1]);
    String cInputValue = sc.nextLine();
    String [] cValue = cInputValue.split("=");
    int c = Integer.parseInt(cValue[1]);
    @Override
    public int evaluate(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == 'a'){
                eval.push(a);
            }
            if(expression.charAt(i) == 'b'){
                eval.push(b);
            }
            if(expression.charAt(i) == 'c'){
                eval.push(c);
            }
            if(Character.isDigit(expression.charAt(i))){
                eval.push(Character.getNumericValue(expression.charAt(i)));
            }
            int x, y, result;
            if(expression.charAt(i) == '+'){
                if(eval.size() == 1){
                    result = (int) eval.pop();
                    eval.push(result);
                    break;
                }
                x = (int) eval.pop();
                y = (int) eval.pop();
                result = x + y;
                eval.push(result);
            }
            if(expression.charAt(i) == '-'){
                if(eval.size() == 1){
                    result = (int) eval.pop() * -1;
                    eval.push(result);
                    break;
                }
                x = (int) eval.pop();
                y = (int) eval.pop();
                result = y - x;
                eval.push(result);
            }
            if(expression.charAt(i) == '*'){
                if(eval.size() == 1){
                    result = (int) eval.pop();
                    eval.push(result);
                    break;
                }
                x = (int) eval.pop();
                y = (int) eval.pop();
                result = x * y;
                eval.push(result);
            }
            if(expression.charAt(i) == '/'){
                x = (int) eval.pop();
                y = (int) eval.pop();
                result = y / x;
                eval.push(result);
            }
            if(expression.charAt(i) == '^'){
                if(eval.size() == 1){
                    result = (int) eval.pop();
                    eval.push(result);
                    break;
                }
                x = (int) eval.pop();
                y = (int) eval.pop();
                result = (int)Math.pow(y, x);
                eval.push(result);
            }
        }   
        return (int)eval.peek();
    }
    public static void main(String[] args) {
        Evaluator evaluate = new Evaluator();
        String postfix = evaluate.infixToPostfix(infix);
        int result = evaluate.evaluate(postfix);
        System.out.println(postfix);
        System.out.print(result); 
    } 
}
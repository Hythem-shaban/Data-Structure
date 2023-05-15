import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver{

    private SingleLinkedList A = new SingleLinkedList();
    private SingleLinkedList B = new SingleLinkedList();
    private SingleLinkedList C = new SingleLinkedList();
    private SingleLinkedList R = new SingleLinkedList();

    @Override
    public void setPolynomial(char poly, int[][] terms){
        int length = terms.length;
        SingleLinkedList X = null;
        if (poly == 'A')
            X = A;
        else if (poly == 'B')
            X = B;
        else if (poly == 'C')
            X = C;
        else if (poly == 'R')
            X = R;
        else
            System.out.println("Error");
        if(length == 0 || terms == null)
            System.out.println("Error");
        if (X != null){
            for (int i = 0; i < length; i++){
                X.add(terms[i][0]);
            }
        }
    }
    @Override
    public String print(char poly) {
        StringBuffer outputPoly = new StringBuffer();
        SingleLinkedList X = null;
        if (poly == 'A')
            X = A;
        else if (poly == 'B')
            X = B;
        else if (poly == 'C')
            X = C;
        else if (poly == 'R')
            X = R;
        else{
            System.out.println("Error");
        }

        if(X.size() == 0)
            return null;
        if (X != null){
            for (int i = 0; i < X.size(); i++){
                if((int)X.get(i) == 0)
                    continue;
                if(outputPoly.length() == 0){
                    if((int)X.get(i) < 0){
                        outputPoly.append('-');
                        if((int)X.get(i) <= -1)
                            outputPoly.append((int)Math.abs((int)X.get(i)));
                        else if(((int)X.get(i) == -1) && ((X.size() - i) == 1))
                            outputPoly.append((int)Math.abs((int)X.get(i)));
                    }
                    else{
                        if((int)X.get(i) > 1)
                                outputPoly.append((int)Math.abs((int)X.get(i)));
                        else if(((int)X.get(i) == 1) && (X.size() - i == 1))
                            outputPoly.append((int)Math.abs((int)X.get(i)));
                    }       
                    if(X.size() - i  - 1> 0){
                        outputPoly.append('x');
                        if(X.size() - i - 1 > 1){
                            outputPoly.append('^');
                            outputPoly.append((int)Math.abs((int)(X.size() - i - 1)));
                        }
                    }
                }
                else{
                    if((int)X.get(i) < 0){
                        outputPoly.append('-');
                        if((int)X.get(i) < -1)
                            outputPoly.append((int)Math.abs((int)X.get(i)));
                        if(((int)X.get(i) == -1) && ((X.size() - i) == 1))
                            outputPoly.append((int)Math.abs((int)X.get(i)));
                    }
                    else{
                        outputPoly.append('+');
                        if((int)X.get(i) > 1)
                                outputPoly.append((int)Math.abs((int)X.get(i)));
                        if(((int)X.get(i) == 1) && (X.size() - i == 1))
                            outputPoly.append((int)Math.abs((int)X.get(i)));
                    }       
                    if(X.size() - i > 1){
                        outputPoly.append('x');
                        if(X.size() - i > 2){
                            outputPoly.append('^');
                            outputPoly.append((int)Math.abs((int)(X.size() - i - 1)));
                        }
                    }
                }
            }
            System.out.println(outputPoly);   
        }
        return X.toString();
    }
    @Override
    public void clearPolynomial(char poly) {
        if(poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R')
            System.out.println("Error");
        if(poly == 'A'){
            if(A.isEmpty())
                System.out.println("Error");
        }
        else if(poly == 'B'){
            if(B.isEmpty())
                System.out.println("Error");
        }
        else if(poly == 'C'){
            if(C.isEmpty())
                System.out.println("Error");
        }
        else if(poly == 'R'){
            if(R.isEmpty())
                System.out.println("Error");
        }
        else if(poly != 'A' && poly != 'B' && poly != 'C' && poly != 'R')
            System.out.println("Error");

        if(poly == 'A'){
            A.clear();
            A = new SingleLinkedList();
        }
        else if (poly == 'B'){
            B.clear();
            B = new SingleLinkedList();
        }
        else if (poly == 'C'){
            C.clear();
            B = new SingleLinkedList();
        }
        else if (poly == 'R'){
            R.clear();
            R = new SingleLinkedList();
        }
        else{
            System.out.println("Error");
        }
    }
    @Override
    public float evaluatePolynomial(char poly, float value) {
        SingleLinkedList X = null;
        float result = 0;
        if (poly == 'A' && !A.isEmpty()){
            X = A;
        }
        else if (poly == 'B' && !B.isEmpty()){
            X = B;
        }
        else if (poly == 'C' && !C.isEmpty()){
            X = C;
        }
        else if (poly == 'R' && !R.isEmpty()){
            X = R;
        }
        if(X != null){
            for(int i = 0; i < X.size(); i++){
                result = result + (int)X.get(i) * (float)Math.pow(value, X.size() - i - 1);
            }
        }
        else{
            System.out.println("Error");
        }

        System.out.println((int)result);
        return result;
    }
    @Override
    public int[][] add(char poly1, char poly2) {
        R.clear();
        SingleLinkedList P1 = null;
        SingleLinkedList P2 = null;
        if(poly1 == 'A' && !A.isEmpty()){
            P1 = A;
        }
        if (poly1 == 'B' && !B.isEmpty()){
            P1 = B;
        }
        if (poly1 == 'C' && !C.isEmpty()){
            P1 = C;
        }
        if(poly2 == 'A' && !A.isEmpty()){
            P2 = A;
        }
        if (poly2 == 'B' && !B.isEmpty()){
            P2 = B;
        }
        if (poly2 == 'C' && !C.isEmpty()){
            P2 = C;
        }
        int size = (P1.size() > P2.size()) ? P1.size() : P2.size();
        int[][] result = new int[size][1];
        for(int i = 0; i < size; i++){
            result[i][0] = 0;
        }
        for(int i = 0; i < P1.size(); i++){
            result[i][0] = (int)P1.get(i);
        }
        for(int i = 0; i < P2.size(); i++){
            result[i + (P1.size() - P2.size())][0] += (int)P2.get(i);
        }
        for(int i = 0; i < size; i++){
            R.add(i, result[i][0]);
        }
        print('R');
        return result;
    }
    @Override
    public int[][] subtract(char poly1, char poly2) {
        R.clear();
        SingleLinkedList P1 = null;
        SingleLinkedList P2 = null;
        if(poly1 == 'A' && !A.isEmpty()){
            P1 = A;
        }
        if (poly1 == 'B' && !B.isEmpty()){
            P1 = B;
        }
        if (poly1 == 'C' && !C.isEmpty()){
            P1 = C;
        }
        if(poly2 == 'A' && !A.isEmpty()){
            P2 = A;
        }
        if (poly2 == 'B' && !B.isEmpty()){
            P2 = B;
        }
        if (poly2 == 'C' && !C.isEmpty()){
            P2 = C;
        }
        int size = (P1.size() > P2.size()) ? P1.size() : P2.size();
        int[][] result = new int[size][1];
        for(int i = 0; i < size; i++){
            result[i][0] = 0;
        }
        for(int i = 0; i < P1.size(); i++){
            result[i][0] = (int)P1.get(i);
        }
        for(int i = 0; i < P2.size(); i++){
            result[i + (P1.size() - P2.size())][0] -= (int)P2.get(i);
        }
        for(int i = 0; i < size; i++){
            R.add(i, result[i][0]);
        }
        print('R');
        return result;
    }
    @Override
    public int[][] multiply(char poly1, char poly2) {
        R.clear();
        SingleLinkedList P1 = null;
        SingleLinkedList P2 = null;
        if(poly1 == 'A' && !A.isEmpty()){
            P1 = A;
        }
        if (poly1 == 'B' && !B.isEmpty()){
            P1 = B;
        }
        if (poly1 == 'C' && !C.isEmpty()){
            P1 = C;
        }
        if(poly2 == 'A' && !A.isEmpty()){
            P2 = A;
        }
        if (poly2 == 'B' && !B.isEmpty()){
            P2 = B;
        }
        if (poly2 == 'C' && !C.isEmpty()){
            P2 = C;
        }
        int size = P1.size() + P2.size() - 1;
        int[][] result = new int[size][1];
        for(int i = 0; i < size; i++){
            result[i][0] = 0;
        }
        for(int i = 0; i < P1.size(); i++){
            for(int j = 0; j < P2.size(); j++){
                result[i+j][0] += ((int)P1.get(i) * (int)P2.get(j));
            }
        }
        for(int i = 0; i < size; i++){
            R.add(i, result[i][0]);
        }
        print('R');
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PolynomialSolver polySolv = new PolynomialSolver();
        while (sc.hasNextLine()) {
            String operation = sc.nextLine();
            if (operation.equals("set")){
                String poly = sc.nextLine();
                if(poly.charAt(0) != 'A' && poly.charAt(0) != 'B' && poly.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                String list = sc.nextLine().replaceAll("\\[|\\]", "");
                if (list.length() == 0) {
                    System.out.println("Error");
                    break;
                }
                else{
                    String[] coefficients = list.split(",");
                    int[][] terms = new int[coefficients.length][1];
                    
                    for(int i = 0; i < coefficients.length; i++){
                            terms[i][0] = Integer.parseInt(coefficients[i]);
                    }
                    polySolv.setPolynomial(poly.charAt(0), terms);
                }
            }
            if (operation.equals("print")){
                String poly = sc.nextLine();
                if(poly.charAt(0) != 'A' && poly.charAt(0) != 'B' && poly.charAt(0) != 'C'  && poly.charAt(0) != 'R'){
                    System.out.println("Error");
                    break;
                }
                polySolv.print(poly.charAt(0));
            }
            if (operation.equals("add")){
                String poly1 = sc.nextLine();
                if(poly1.charAt(0) != 'A' && poly1.charAt(0) != 'B' && poly1.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                String poly2 = sc.nextLine();
                if(poly2.charAt(0) != 'A' && poly2.charAt(0) != 'B' && poly2.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                polySolv.add(poly1.charAt(0), poly2.charAt(0));
            }
            if (operation.equals("sub")){
                String poly1 = sc.nextLine();
                if(poly1.charAt(0) != 'A' && poly1.charAt(0) != 'B' && poly1.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                String poly2 = sc.nextLine();
                if(poly2.charAt(0) != 'A' && poly2.charAt(0) != 'B' && poly2.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                polySolv.subtract(poly1.charAt(0), poly2.charAt(0));
            }
            if (operation.equals("mult")){
                String poly1 = sc.nextLine();
                if(poly1.charAt(0) != 'A' && poly1.charAt(0) != 'B' && poly1.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                String poly2 = sc.nextLine();
                if(poly2.charAt(0) != 'A' && poly2.charAt(0) != 'B' && poly2.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                polySolv.multiply(poly1.charAt(0), poly2.charAt(0));
            }
            if (operation.equals("clear")){
                String poly = sc.nextLine();
                if(poly.charAt(0) != 'A' && poly.charAt(0) != 'B' && poly.charAt(0) != 'C'  && poly.charAt(0) != 'R'){
                    System.out.println("Error");
                    break;
                }
                polySolv.clearPolynomial(poly.charAt(0));
                System.out.println("[]");
            }
            if (operation.equals("eval")){
                String poly = sc.nextLine();
                if(poly.charAt(0) != 'A' && poly.charAt(0) != 'B' && poly.charAt(0) != 'C'){
                    System.out.println("Error");
                    break;
                }
                int value = sc.nextInt();
                polySolv.evaluatePolynomial(poly.charAt(0), value);
            }
            if(!operation.equals("set") && !operation.equals("print") && !operation.equals("clear") && !operation.equals("add") && !operation.equals("sub") && !operation.equals("mult") && !operation.equals("eval")){
                System.out.print("Error");
                break;
            }
        }
    }
}
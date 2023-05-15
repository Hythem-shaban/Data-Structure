import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {
  
  /*** Removes the element at the top of stack and returnsthat element.
  * @return top of stack element, or through exception if empty
  */
  
  public Object pop();
  
  /*** Get the element at the top of stack without removing it from stack.
  * @return top of stack element, or through exception if empty
  */
  
  public Object peek();
  
  /*** Pushes an item onto the top of this stack.
  * @param object to insert*
  */
  
  public void push(Object element);
  
  /*** Tests if this stack is empty
  * @return true if stack empty
  */
  public boolean isEmpty();
  
  public int size();
}


public class MyStack implements IStack {
  static class StackNode{
    private Object newElement;
    private StackNode newNext;
    public StackNode(Object element, StackNode next){
        newElement = element;
        newNext = next;
    }
    public Object getElement(){
        return newElement;
    }
    public StackNode getNext(){
        return newNext;
    }
    public void setElement(Object element){
        newElement = element;
    }
    public void setNext(StackNode next){
        newNext = next;
    }
  }
  private StackNode top;
  private int size;
  public MyStack(){
      top = null;
      size = 0;
  }
  public Object get(int index) {
    if (index >= size || index < 0) {
      System.out.println("Error");
    }
    if (size == 0) {
      System.out.println("Error");
    }
    StackNode current = top;
    for (int i = 0; i < index; i++) {
        current = current.getNext();
    }
    return current.getElement();
}
  @Override
  public Object pop() {
    if(isEmpty()){
      System.out.println("Error");
      System.exit(0);
    }
    Object temp = top.getElement();
    top = top.getNext();
    size--;
    return temp;
  }

  @Override
  public Object peek() {
    if(isEmpty()){
      System.out.println("Error");
      System.exit(0);
    }
    return top.getElement();
  }

  @Override
  public void push(Object element) {
    StackNode node = new StackNode(element, top);
    top = node;
    size++;
  }

  @Override
  public boolean isEmpty() {
    if (top == null)
      return true;
    return false;
  }

  @Override
  public int size() {
    return size;
  }
  public static void main(String[] args) {
    MyStack stack = new MyStack();
    Scanner sc = new Scanner(System.in);
    String sin = sc.nextLine().replaceAll("\\[|\\]", "");
    String[] s = sin.split(", ");
    int[] array = new int[s.length];
    if (s.length == 1 && s[0].isEmpty())
        array = new int[]{};
    else {
        for(int i = 0; i < s.length; ++i)
            array[i] = Integer.parseInt(s[i]);
    }
    for(int i = array.length - 1; i >= 0; i--){
        stack.push(array[i]);
    }
    String instruction = sc.next();
    if(instruction.equals("push"))
    {
      int data = sc.nextInt();
      stack.push(data);
      System.out.print("[");
      for(int i = 0; i < stack.size; i++) {
          System.out.print(stack.get(i));
          if(i != stack.size() - 1)
              System.out.print(", ");
      }
      System.out.println("]");
    }
    if(instruction.equals("pop"))
    {
      stack.pop();
      System.out.print("[");
      for(int i = 0; i < stack.size; i++) {
        System.out.print(stack.get(i));
        if(i != stack.size() - 1)
            System.out.print(", ");
      }
      System.out.println("]");
    }
    if(instruction.equals("isEmpty"))
    {
        if(stack.isEmpty() == true )
            System.out.println("True");
        else
            System.out.println("False");
    }
    if(instruction.equals("peek"))
    {
      System.out.println(stack.peek());
    }
    if(instruction.equals("size"))
    {
      System.out.println(stack.size());
    }
    if(!instruction.equals("push") && !instruction.equals("pop") && !instruction.equals("isEmpty") && !instruction.equals("size") && !instruction.equals("peek")){
      System.out.println("Error");
      System.exit(0);
    }
    if(!sc.hasNextLine()){
      sc.close();
      System.exit(0);
    }
  }
}

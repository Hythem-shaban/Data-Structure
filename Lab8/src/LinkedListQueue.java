import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
  /*** Inserts an item at the queue front.*/
  public void enqueue(Object item);
  /*** Removes the object at the queue rear and returnsit.*/
  public Object dequeue();
  /*** Tests if this queue is empty.*/
  public boolean isEmpty();
  /*** Returns the number of elements in the queue*/
  public int size();
}

public class LinkedListQueue implements IQueue {
  static class QueueNode{
    private Object newElement;
    private QueueNode newNext;
    public QueueNode(Object element, QueueNode next){
        newElement = element;
        newNext = next;
    }
    public Object getElement(){
        return newElement;
    }
    public QueueNode getNext(){
        return newNext;
    }
    public void setElement(Object element){
        newElement = element;
    }
    public void setNext(QueueNode next){
        newNext = next;
    }
  }
  private QueueNode head;
  private QueueNode tail;
  private int size;
  public LinkedListQueue(){
      head = null;
      size = 0;
  }
  public Object get(int index) {
    if (index >= size || index < 0) {
      System.out.println("Error");
    }
    if (size == 0) {
      System.out.println("Error");
    }
    QueueNode current = head;
    for (int i = 0; i < index; i++) {
        current = current.getNext();
    }
    return current.getElement();
}
  @Override
  public void enqueue(Object item) {
    QueueNode node = new QueueNode(item, tail);
    node.setElement(item);
    node.setNext(null);
    if(size() == 0) {
      head = node;
    }
    else {
      tail.setNext(node);
    }
    tail = node;
    size ++;
  }

  @Override
  public Object dequeue() {
    if (size == 0) {
      System.out.println("Error");
      System.exit(0);
    }
    Object temp = head.getElement();
    head = head.getNext();
    size --;
    if (size == 0) {
      tail = null;
    }
    return temp;
  }

  @Override
  public boolean isEmpty() {
    if (head == null)
      return true;
    return false;
  }

  @Override
  public int size() {
    return size;
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
    LinkedListQueue queue = new LinkedListQueue();
    for(int i = arr.length - 1; i >= 0; i--){
      queue.enqueue(arr[i]);
    }
    String instruction = sc.next();
    if(instruction.equals("enqueue"))
    {
      int item = sc.nextInt();
      queue.enqueue(item);
      System.out.print("[");
      for(int i = queue.size() - 1; i >= 0; i--) {
          System.out.print(queue.get(i));
          if(i != 0)
              System.out.print(", ");
      }
      System.out.println("]");
    }
    if(instruction.equals("dequeue"))
    {
      queue.dequeue();
      System.out.print("[");
      for(int i = queue.size() - 1; i >= 0; i--) {
          System.out.print(queue.get(i));
          if(i != 0)
              System.out.print(", ");
      }
      System.out.println("]");
    }
    if(instruction.equals("isEmpty"))
    {
        if(queue.isEmpty() == true )
            System.out.println("True");
        else
            System.out.println("False");
    }
    if(instruction.equals("size"))
    {
      System.out.println(queue.size());
    }
    if(!instruction.equals("enqueue") && !instruction.equals("dequeue") && !instruction.equals("isEmpty") && !instruction.equals("size")){
      System.out.println("Error");
      System.exit(0);
    }
    if(!sc.hasNextLine()){
      sc.close();
      System.exit(0);
    }
  }   
}

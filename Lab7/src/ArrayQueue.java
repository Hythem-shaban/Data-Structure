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

public class ArrayQueue implements IQueue {
  private int f, r, N;
  private Object[] Q;
  ArrayQueue(int n) {
    Q = new Object[n];
    f = 0;
    r = 0;
    N = n;
  }
  @Override
  public void enqueue(Object item) {
    if (size() == N - 1) {
      System.out.println("Error");
      System.exit(0);
    }
    Q[r] = (int) item;
    r = (r + 1) % N;
  }

  @Override
  public Object dequeue() {
    if (isEmpty()) {
      System.out.println("Error");
      System.exit(0);
    }
    Object temp = Q[f];
    Q[f] = null;
    f = (f + 1) % N;
    return temp;
  }

  @Override
  public boolean isEmpty() {
    return (f == r);
  }

  @Override
  public int size() {
    return (N - f + r) % N;
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
    int N = 100;
    ArrayQueue queue = new ArrayQueue(N);
    for(int i = arr.length - 1; i >= 0; i--){
      queue.enqueue(arr[i]);
    }
    String instruction = sc.next();
    if(instruction.equals("enqueue"))
    {
      int item = sc.nextInt();
      queue.enqueue(item);
      System.out.print("[");
      for(int i = queue.r - 1; i >= queue.f; i--) {
          System.out.print(queue.Q[i]);
          if(i != queue.f)
              System.out.print(", ");
      }
      System.out.println("]");
    }
    if(instruction.equals("dequeue"))
    {
      queue.dequeue();
      System.out.print("[");
      for(int i = queue.r - 1; i >= queue.f; i--) {
          System.out.print(queue.Q[i]);
          if(i != queue.f)
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

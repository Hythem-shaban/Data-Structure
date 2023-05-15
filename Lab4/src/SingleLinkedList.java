import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, Object element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(Object element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public Object get(int index);

/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, Object element);
/**
* Removes all of the elements from this list.
*/
public void clear();
/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public ILinkedList sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(Object o);
}


public class SingleLinkedList implements ILinkedList {
	/* Implement your linked list class here*/
    static class SLNode{
        private Object newElement;
        private SLNode newNext;
        public SLNode(Object element, SLNode next){
            newElement = element;
            newNext = next;
        }
        public Object getElement(){
            return newElement;
        }
        public SLNode getNext(){
            return newNext;
        }
        public void setElement(Object element){
            newElement = element;
        }
        public void setNext(SLNode next){
            newNext = next;
        }
    }
    private SLNode head;
    private int size;
    public SingleLinkedList(){
        head = null;
        size = 0;
    }
    @Override
    public void add(int index, Object element) {
        SLNode node = new SLNode(element, null);
        if (index > size || index < 0) {
            System.out.println("Error");
        }
        if (size == 0) {
            head = node;
            size++;
            return;
        }
        if (index == 0) {
            SLNode current = head;
            head = node;
            node.setNext(current);
            size++;
            return;
        }
        SLNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        node.setNext(current.getNext());
        current.setNext(node);
        size++;    
    }

    @Override
    public void add(Object element) {
        SLNode node = new SLNode(element, null);
        if (size == 0) {
            head = node;
            size++;
            return;
        }
        SLNode current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(node);
        size++;        
    }

    @Override
    public Object get(int index) {
        if (index >= size || index < 0) {
            System.out.println("Error");
        }
        if (size == 0) {
            System.out.println("Error");
        }
        SLNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    @Override
    public void set(int index, Object element) {
        if (index >= size || index < 0) {
            System.out.println("Error");
        }
        if (head == null) {
            System.out.println("Error");
        }
        SLNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setElement(element);
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public void remove(int index) {
        if (index >= size || index < 0) {
            System.out.println("Error");
        }
        if (size == 0) {
            System.out.println("Error");
        }
        SLNode current = head;
        if (index == 0) {
            head = current.getNext();
            size--;
            return;
        }
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        SLNode current2 = current.getNext();
        current.setNext(current2.getNext());
        current2.setNext(null);
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex > size || toIndex > size) {
            System.out.println("Error");
        }
        if (fromIndex < 0 || toIndex < 0 || fromIndex > toIndex) {
            System.out.println("Error");
        }
        if (size == 0) {
            System.out.println("Error");
        }
        SLNode current = head;
        for (int i = 0; i < fromIndex; i++) {
            current = current.getNext();
        }
        SingleLinkedList list = new SingleLinkedList();
        list.add(current.getElement());
        for (int i = fromIndex; i < toIndex; i++) {
            current = current.getNext();
            list.add(current.getElement());
        }
        return list;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            System.out.println("Error");
        }
        if (size == 0) {
            return false;
        }
        SLNode current = head;
        for (int i = 0; i < size; i++) {
            if (o.equals(current.getElement())) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[] array = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            array = new int[]{};
        else {
            for(int i = 0; i < s.length; ++i)
                array[i] = Integer.parseInt(s[i]);
        }
        for(int i = 0; i < array.length; ++i){
            list.add(array[i]);
        }
        String instruction = sc.next();
        if(instruction.equals("add"))
        {
            int data = sc.nextInt();
            list.add(data);
            System.out.print("[");
            for(int i = 0; i < list.size; i ++) {
                System.out.print(list.get(i));
                if(i != list.size() - 1)
                    System.out.print(", ");
            }
            System.out.print("]");
            
        }
        if(instruction.equals("addToIndex"))
        {
            int index = sc.nextInt();
            int data = sc.nextInt();
            if (index > list.size() || index < 0) {
                System.out.println("Error");
            }
            else{
                list.add(index, data);
                System.out.print("[");
                for(int i = 0; i < list.size; i ++){
                    System.out.print(list.get(i));
                    if(i != list.size() - 1)
                        System.out.print(", ");
                }
                System.out.print("]");
            }
            
        }
        if(instruction.equals("isEmpty"))
        {
            if(list.isEmpty() == true )
                System.out.println("True");
            else
                System.out.println("False");

        }
        if(instruction.equals("set"))
        {
            int index = sc.nextInt();
            int data = sc.nextInt();
            if (index >= list.size() || index < 0) {
                System.out.println("Error");
            }
            else{
                list.set(index, data);
                System.out.print("[");
                for(int i = 0; i < list.size; i ++){
                    System.out.print(list.get(i));
                    if(i != list.size() - 1)
                        System.out.print(", ");
                }
                System.out.print("]");
            }
            
        }
        if(instruction.equals("clear"))
        {
            list.clear();
            System.out.println("[]");
        }
        if(instruction.equals("size"))
        {
            System.out.println(list.size());
        }
        if(instruction.equals("remove"))
        {
            int index = sc.nextInt();
            if (index >= list.size() || index < 0 || list.size() == 0) {
                System.out.println("Error");
            }
            else{
                list.remove(index);
                System.out.print("[");
                for(int i = 0; i < list.size; i ++){
                    System.out.print(list.get(i));
                    if(i != list.size() - 1)
                        System.out.print(", ");
                }
                System.out.print("]");  
            }
        }
        if(instruction.equals("get"))
        {
            int index = sc.nextInt();
            if (index >= list.size() || index < 0) {
                System.out.println("Error");
            }
            else{
                list.get(index);
                System.out.println(list.get(index));
            }
        }
        if(instruction.equals("contains"))
        {
            int data = sc.nextInt();
            if(list.contains(data) == true )
                System.out.println("True");
            else
                System.out.println("False");
        }
        if(instruction.equals("sublist"))
        {
            int index1 = sc.nextInt();
            int index2 = sc.nextInt();
            if (index1 < 0 || index2 >= list.size() || index1 > index2 || list.size == 0) {
                System.out.println("Error");
            }
            else{
                list.sublist(index1, index2);
                System.out.print("[");
                for(int i = index1; i <= index2; i++){
                    System.out.print(list.get(i));
                    if(i != index2)
                        System.out.print(", ");
                }
                System.out.print("]");
            }
        }
    }
}

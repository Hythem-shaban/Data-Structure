import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



// Java program to implement
// a Singly Linked List
public class LinkedList {

	Node head; // head of list

	// Linked list Node.
	// Node is a static nested class
	// so main() can access it
	static class Node {

		int data;
		Node next;
        Node prev;
		// Constructor
		Node(int d)
		{
			data = d;
			next = null;
            prev =null;
		}
	}

	// **************INSERTION**************

	// Method to insert a new node
	public static LinkedList insert(LinkedList list,int data)
	{
		// Create a new node with given data
		Node new_node = new Node(data);

		// If the Linked List is empty,
		// then make the new node as head
		if (list.head == null) {
			list.head = new_node;
		}
		else {
			// Else traverse till the last node
			// and insert the new_node there
			Node last = list.head;
			while (last.next != null) {
				last = last.next;
			}

			// Insert the new_node at last node
            new_node.prev=last;
			last.next = new_node;
            new_node.next=null;
            
		}

		// Return the list by head
		return list;
	}


// Method to insert a new node
public static LinkedList insert_at_index(LinkedList list,int data,int index)
{
    // Create a new node with given data
    Node new_node = new Node(data);
    new_node.next = null;
    Node currNode = list.head;
    int counter=0;
    // If the Linked List is empty,
    // then make the new node as head
    if (index == 0) {
        currNode.prev=new_node;
        list.head = new_node;
        new_node.next=currNode;
        new_node.prev=null;
    }
    else {
        // Else traverse till the last node
        // and insert the new_node there
        //currNode = currNode.next;
        while (counter != index-1 ) {
           counter++;
           currNode=currNode.next;
        }

        // Insert the new_node at last node
        new_node.next=currNode.next;
        new_node.prev=currNode;
        currNode.next.prev=new_node;
        currNode.next=new_node;
        
        
    }

    // Return the list by head
    return list;
}





public static void element_at_index(LinkedList list,int index)
{
    
    Node currNode = list.head;
    int counter=0;
    while (counter != index ) {
        counter++;
        currNode=currNode.next;
     }

     System.out.println(currNode.data);
}

public static void get_index(LinkedList list,int index,int n)
{
    
    Node currNode = list.head;
    int counter=0;
    
    while (currNode != null && counter!=index && counter<n) {
        counter++;
        currNode=currNode.next;
     }
     if(counter==n || currNode==null)
     {
        System.out.println("Error");
     }
     else{
        System.out.println(currNode.data);
     }
     
}


public static void contains(LinkedList list,int the_data,int n)
{
    
    Node currNode = list.head;
    int counter=0;
    while (currNode != null && currNode.data!=the_data ) {
        counter++;
        currNode=currNode.next;
     }
     if(counter==0 && currNode == null)
     {
        System.out.println("Error");
     }
     if(counter<n)
     {
        System.out.println("True");
     }
     else
     {
        System.out.println("False");
     }
}


public static LinkedList set_element(LinkedList list,int index,int new_data)
{
    
    Node currNode = list.head;
    int counter=0;
    while (counter != index ) {
        counter++;
        currNode=currNode.next;
     }

     currNode.data=new_data;
     return list;
}



public static void IsEmpty(int n)
{
    
    if(n==0)
    System.out.println("True");
    else
    System.out.println("False");
}
public static void Size(int n)
{
    
    
    System.out.println(n);

}


	// **************TRAVERSAL**************

	// Method to print the LinkedList.
	public static void printList(LinkedList list,int n)
	{
		Node currNode = list.head;
        int counter =0;
		
        System.out.print("[");
		// Traverse through the LinkedList
        
		while (currNode != null && n-1!=0) {
			// Print the data at current node
            
			System.out.print(currNode.data + ", ");
            
			// Go to next node
			currNode = currNode.next;
            if(counter==n-2) break;
            counter++;
		}
		 System.out.print(currNode.data + "]");
	}

	

	// **************DELETION AT A POSITION**************

	// Method to delete a node in the LinkedList by POSITION
	public static LinkedList delete_at_index(LinkedList list, int index)
	{
		// Store head node
		Node currNode = list.head, prev = null;

		//
		// CASE 1:
		// If index is 0, then head node itself is to be
		// deleted

		if (index == 0 && currNode != null) {
			list.head = currNode.next; // Changed head
            currNode.next.prev=null;

			// Return the updated List
			return list;
		}

		//
		// CASE 2:
		// If the index is greater than 0 but less than the
		// size of LinkedList
		//
		// The counter
		int counter = 0;

		// Count for the index to be deleted,
		// keep track of the previous node
		// as it is needed to change currNode.next
		while (currNode != null) {

			if (counter == index) {
				// Since the currNode is the required
				// position Unlink currNode from linked list
				prev.next = currNode.next;
                

				break;
			}
			else {
				// If current position is not the index
				// continue to next node
				prev = currNode;
				currNode = currNode.next;
				counter++;
			}
		}

		// If the position element was found, it should be
		// at currNode Therefore the currNode shall not be
		// null
		//
		// CASE 3: The index is greater than the size of the
		// LinkedList
		//
		// In this case, the currNode should be null
		if (currNode == null) {
			// Display the message
			System.out.println("Error");
		}

		// return the List
		return list;
	}


    public static LinkedList Clear(LinkedList list)
{
    
    list.head=null;
    return list;
}


public static LinkedList sublist(LinkedList list,LinkedList sublist, int index1,int index2)
{
    
    Node currNode = list.head;
    for (int i=0 ; i<index1 ; i++)
    {
        currNode=currNode.next;
    }
    int iterations=index2-index1+1;
    
    for(int i = 0; i < iterations; ++i){
        sublist= insert(sublist,currNode.data);
        currNode=currNode.next;
    }

    // Return the list by head
    return sublist;
}


public static int size(LinkedList list)
{
    
    Node currNode = list.head;
    int counter=0;
    if (currNode==null) return 0;
    while (currNode != null ) {
        counter++;
        currNode=currNode.next;
     }
     return counter;
}


	// **************MAIN METHOD**************

	// method to create a Singly linked list with n nodes
	public static void main(String[] args)
	{
        /* Start with the empty list. */
		LinkedList list = new LinkedList();
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

        
            // ******INSERTION******
        //
        for(int i = 0; i < array.length; ++i){
            list= insert(list,array[i]);
        }

        String instruction = sc.next();
        if(instruction.equals("add"))
        {
            int data = sc.nextInt();
            insert(list,data);
            printList(list,array.length+1);
        }
        if(instruction.equals("addToIndex"))
        {
            int index = sc.nextInt();
            int data = sc.nextInt();
            if(index>array.length-1 || index<0)
            {
                System.out.println("Error");
            }
            else
            {
                insert_at_index(list,data,index);
                printList(list,array.length+1);
            }
            
        }
        if(instruction.equals("isEmpty"))
        {
           IsEmpty(array.length);
        }
        if(instruction.equals("set"))
        {
            int index = sc.nextInt();
            int data = sc.nextInt();
            if(index>array.length-1 || index<0)
            {
                System.out.println("Error");
            }
            else
            {
                set_element(list, index, data);
                printList(list,array.length);
            }
            
        }
        if(instruction.equals("clear"))
        {
            Clear(list);
            System.out.println("[]");
        }
        if(instruction.equals("size"))
        {
            int n= size(list);
            System.out.println(n);
        }
        if(instruction.equals("remove"))
        {
            int index = sc.nextInt();
            if(index>array.length-1 || index<0)
            {
                delete_at_index(list, index);
            }
            else
            {
                delete_at_index(list, index);
            printList(list,array.length-1);
            }
            
        }
        if(instruction.equals("get"))
        {
            int index = sc.nextInt();
            if(index>array.length-1 || index<0)
            {
                System.out.println("Error");
            }
            else
            {
                get_index(list, index ,array.length);
            }
        }
        if(instruction.equals("contains"))
        {
            int the_data = sc.nextInt();
            contains(list, the_data ,array.length);
 
        }
        if(instruction.equals("sublist"))
        {
            int index1 = sc.nextInt();
            int index2 = sc.nextInt();
            if(index2>array.length-1 || index1<0 || index1>index2 )
            {
                System.out.println("Error");
            }
            else
            {
                LinkedList sublist = new LinkedList();
                sublist(list,sublist, index1 , index2 );
                printList(sublist,index2-index1+1);
            }
        }
		
	}
}




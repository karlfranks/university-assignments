package uk.ac.aber.dcs.kpf.cs21120.assignment1;

public interface MyQueue {
	
	// Adds an Object to the back of the queue.
	public void push(String input);
	
	// Takes an Object from the front of the queue.
	public String pop() throws QueueEmptyException; 
	
	// Inspects the Object at the front of the queue.
	public String front() throws QueueEmptyException; 
	
	//Returns the size of the queue
	public int length();
	
	// Checks if the queue is empty
	public boolean isEmpty();
	
	// Remove all objects from the queue
	public void clear();
}
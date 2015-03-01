package uk.ac.aber.dcs.kpf.cs21120.assignment1;
//Code sourced from Queue example in lectures
public class QueueEmptyException extends RuntimeException {
public QueueEmptyException() { super("Attempt to access empty Queue");
} }

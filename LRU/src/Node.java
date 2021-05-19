
public class Node {
	
	//Simple Node class that acts as the Nodes for
	//the Doubly Linked List and acts as a bucket for the
	//our own implemented HashMap
	private int key;
	private int data;
	private Node prev;
	private Node next;
	
	public Node(int key, int data) {
		this.key = key;
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
}

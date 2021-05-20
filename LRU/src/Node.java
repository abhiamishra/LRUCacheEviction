
public class Node {
	
	//Simple Node class that acts as the Nodes for
	//the Doubly Linked List and acts as a bucket for the
	//our own implemented HashMap
	private int weight;
	private String name;
	private String author;
	private String genre;
	private String subgenre;
	private int height;
	private double price;
	private String publisher;
	private Node prev;
	private Node next;
	
	public Node(String key, int data) {
		this.name = key;
		this.weight = data;
	}
	public Node() {
		// TODO Auto-generated constructor stub
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int data) {
		this.weight = data;
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
	public String getName() {
		return name;
	}
	public void setName(String key) {
		this.name = key;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getSubgenre() {
		return subgenre;
	}
	public void setSubgenre(String subgenre) {
		this.subgenre = subgenre;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		return "Node [name=" + name + ", author=" + author + ", genre=" + genre + ", subgenre=" + subgenre + ", height="
				+ height + ", publisher=" + publisher + "]";
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}

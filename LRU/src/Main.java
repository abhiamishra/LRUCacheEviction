import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//Setting up our hard drive data by reading from a CSV
		File bookRead = new File("books.csv");
		GenericHashMap<String, Node> hardDriveLibrary = new GenericHashMap<>();
		if(bookRead.exists()) {
			Scanner bookScan = new Scanner(bookRead);
			String parsing = bookScan.nextLine();
			while(bookScan.hasNext() && bookScan.hasNextLine()) {
				parsing = bookScan.nextLine();
				//System.out.println(parsing);
				int[] count = new int[5];
				int counter=0;
				Node book = new Node();
				while(parsing != "") {
					//System.out.println(counter);
					int comma = parsing.indexOf(',');
					
					if(counter==0) {
						book.setName(parsing.substring(0,comma));
						//System.out.println(parsing.substring(0,comma));
						parsing = parsing.substring(comma+1);
					}
					else if(counter==1) {
						comma = parsing.indexOf('"');
						if(comma == -1) {
							comma = parsing.indexOf(',');
							book.setAuthor(parsing.substring(0,comma));
							//System.out.println(parsing.substring(0,comma));
							parsing = parsing.substring(comma+1);
						}
						else {
							int comma2 = parsing. indexOf('"', comma+1);
							book.setAuthor(parsing.substring(comma+1,comma2));
							//System.out.println(parsing.substring(comma+1,comma2));
							parsing = parsing.substring(comma2+2);
						}
					}
					else if(counter==2) {
						book.setGenre(parsing.substring(0,comma));
						//System.out.println(parsing.substring(0,comma));
						parsing = parsing.substring(comma+1);
					}
					else if(counter==3) {
						book.setSubgenre(parsing.substring(0, comma));
						//System.out.println(parsing.substring(0, comma));
						parsing = parsing.substring(comma+1);
					}
					else if(counter==4) {
						int height = 
								Integer.parseInt(parsing.substring(0, comma));
						book.setHeight(height);
						//System.out.println(height);
						parsing = parsing.substring(comma+1);
					}
					else if(counter==5) {
						book.setPublisher(parsing.substring(0));
						//System.out.println(parsing.substring(0));
						parsing = "";
					}
					counter++;
					
				}
				hardDriveLibrary.put(book.getName(), book);
			}
		}
		
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter whether you want to go to checkout (Y/N)");
		String input = userInput.nextLine();
		DoublyLinkedList cacheQueue = new DoublyLinkedList();
		LinkedList<Node> price = new LinkedList<>();
		HashMap<String, Node> cacheData = new HashMap<>(4);
		while(input.equals("Y")) {
			System.out.println("Enter the name of the book");
			String bookName = userInput.nextLine();
			
			Node bookGet = cacheData.get(bookName);
			System.out.println(bookGet);
			if(bookGet == null || bookGet.getName().compareTo(bookName)!=0) {
				//Here we have a Cache Miss
				if(cacheData.isFull()) {
					//If our cacheData hashMap is full, then we remove the 
					//last element from our linked list and then
					//accordingly, remove our Node from the hashMap
					Node removed = cacheQueue.dequeue();
					Node hashRemoved = cacheData.get(removed.getName());
					
					//Next, we create a Node that corresponds to the data accessed
					//by the for-loop. We then put this Node into the
					//hashMap and add it to our queue
					Node addNode = hardDriveLibrary.get(bookName);
					addNode.setWeight(addNode.getWeight()+1);
					cacheData.put(bookName, addNode);
					cacheQueue.enqueue(addNode);
					System.out.println("Getting the book from our back catalog since our recent catalog is FULL:");
					System.out.println(addNode);
				}
				else {
					//If it isn't full, we simply create a Node that corresponds to the data accessed
					//by the for-loop. We then put this Node into the
					//hashMap and add it to our queue
					Node addNode = hardDriveLibrary.get(bookName);
					System.out.println("Getting the book from our back catalog:");
					System.out.println(addNode);
					cacheData.put(bookName, addNode);
					cacheQueue.enqueue(addNode);
					
				}
			}
			else if(bookGet.getName().compareTo(bookName) == 0) {
				//Else, if the object that we got is equal to our internal data accessed
				//then we move the Node in our queue to the top to indicate that it 
				//was the most recently accessed.
				bookGet.setWeight(bookGet.getWeight()+1);
				cacheQueue.toHead(bookGet);
				cacheData.put(bookName, bookGet);
				System.out.println("Getting the book from our most recently used catalog: ");
				System.out.println(bookGet);
			}
			else {
				System.out.println("We don't have your book!");
			}
			System.out.println("Please enter whether you want to go to checkout (Y/N)");
			input = userInput.nextLine();
			
			if(cacheData.isFull()) {
				System.out.println("HRY");
			}
		}
		
		System.out.println("Thank you for coming!");
		//Printing out our cache data set (the last updated one)
		//from least recent to the most recent
		int count = 0;
		Node traversal = cacheQueue.getHead();
		while(count<10 && traversal != null) {
			Random a = new Random();
			traversal.setPrice(Math.abs(a.nextInt(90)));
			if(traversal.getWeight()>=0 && traversal.getWeight()<3) {
				traversal.setPrice(traversal.getPrice()*1);
			}
			else if(traversal.getWeight()>=3 && traversal.getWeight()<7) {
				traversal.setPrice(traversal.getPrice()*1.30);
			}
			else if(traversal.getWeight()>=7 && traversal.getWeight()<10) {
				traversal.setPrice(traversal.getPrice()*1.4);
			}
			else {
				traversal.setPrice(traversal.getPrice()*1.8);
			}
			System.out.println("The book " + traversal.getName() + " was visited " + traversal.getWeight() + " times and has had its price increased to " + traversal.getPrice());
			count++;
			traversal = traversal.getNext();
		}
		
		System.out.println();
	}
}

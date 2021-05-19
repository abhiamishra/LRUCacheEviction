
public class Main {

	public static void main(String[] args) {
		//Our internal data that works as the data stored in the hard-drive
		int[] internalData = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
		
		//Our two data structures that we need to implement
		//a LRU eviction in our cache data
		DoublyLinkedList cacheQueue = new DoublyLinkedList();
		HashMap<Integer, Node> cacheData = new HashMap<>();
		
		for(int i=0; i<internalData.length; i++) {
			//We starting going through our internal data and
			//check if a Node from the key (for loop counter) exists
			//and if it does, is it equal to the internal data at that point
			Node obj = cacheData.get(i);
			if(obj == null || obj.getData() != internalData[i]) {
				//Here we have a Cache Miss
				if(cacheData.isFull()) {
					//If our cacheData hashMap is full, then we remove the 
					//last element from our linked list and then
					//accordingly, remove our Node from the hashMap
					Node removed = cacheQueue.dequeue();
					Node hashRemoved = cacheData.get(removed.getKey());
					
					//Next, we create a Node that corresponds to the data accessed
					//by the for-loop. We then put this Node into the
					//hashMap and add it to our queue
					Node addNode = new Node(i, internalData[i]);
					cacheData.put(i, addNode);
					cacheQueue.enqueue(addNode);
				}
				else {
					//If it isn't full, we simply create a Node that corresponds to the data accessed
					//by the for-loop. We then put this Node into the
					//hashMap and add it to our queue
					Node addNode = new Node(i, internalData[i]);
					cacheData.put(i, addNode);
					cacheQueue.enqueue(addNode);
				}
			}
			else if(obj.getData() == internalData[i]) {
				//Else, if the object that we got is equal to our internal data accessed
				//then we move the Node in our queue to the top to indicate that it 
				//was the most recently accessed.
				cacheQueue.toHead(obj);
			}
		}
		
		
		//Printing out our cache data set (the last updated one)
		//from least recent to the most recent
		while(cacheQueue.getTail()!=null) {
			Node remove = cacheQueue.dequeue();
			if(remove != null) {
				System.out.print(remove.getData() + " ");
			}
		}
		
		System.out.println();
		System.out.println("Least Used -> Most Commonly Used");
	}
}

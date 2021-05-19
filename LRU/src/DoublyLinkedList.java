//Class for our DoublyLinkedList which has a Queue implementation
public class DoublyLinkedList {
	private Node head;
	private Node tail;
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
	public Node getTail() {
		return tail;
	}
	public void setTail(Node tail) {
		this.tail = tail;
	}
	
	public void enqueue(Node toAdd) {
		//An enqueue function that adds to the head of the linked list (pre-pending
		//to the list) so that we can easily add elements to the list
		if(head == null) {
			head = toAdd;
		}
		else {
			//Here we mend connections between two nodes - the one that we are going
			//to add and the head node of our linked list. Finally, we set our tail
			//pointer when/if we need to.
			
			//Finally we update our head pointer
			toAdd.setNext(head);
			head.setPrev(toAdd);
			if(tail == null) {
				tail = head;
			}
			head = toAdd;
		}
	}
	
	public Node dequeue() {
		//Our dequeue function which removes from the end of our linked list
		if(tail == null) {
			return null;
		}
		else {
			//When we are removing the Node, we update our connections
			//near the end of the list and then depending on if we have a before node,
			//we set it's next pointer to null. Finally, we set our new tail pointer and return
			//our now removed Node.
			Node toRemove = tail;
			Node before = toRemove.getPrev();
			if(before != null) {
				before.setNext(null);
			}
			
			toRemove.setPrev(null);
			tail = before;
			return toRemove;
		}
	}
	
	public void toHead(Node toMove) {
		//In this function, we are moving a node from the middle of the list to the
		//beginning of the list. Here, we get the previous and afterward node from the Node
		//we want move. We accordingly update the connections and then
		//using the enqueue function, we set the node to the head of the linked list.
		Node before = toMove.getPrev();
		Node after = toMove.getNext();
		
		before.setNext(toMove.getNext());
		after.setPrev(toMove.getPrev());
		
		toMove.setNext(null);
		toMove.setPrev(null);
		
		this.enqueue(toMove);
	}
}


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singular linked list class.
 * @param <T> a data.
 */
class SimpleList<T> implements Iterable<T> {

	/** 
	 * Class for the internal node: not visible to the outside. 
	 * @param <T> a data.
	 */
	private class Node<T> {
		
		/**
		 * The data value of this node.
		 */
		T value;
		
		/**
		 * The next node.
		 */
		Node<T> next;	// link to the next node
		
		/**
		 * Initializes value.
		 * @param value a data.
		 */
		public Node(T value){
			this.value = value;
		}
	}
	
	
	/**
	 * The head node of the list.
	 */
	private Node<T> head;  	
	
	/**
	 * The tail node of the list.
	 */
	private Node<T> tail;  	
    
	/**
	 * The size of the list.
	 */
	private int size;

	
	/**
	 * Constructor, initializes empty list.
	 */
	public SimpleList(){ 

		size = 0;
		head = null;
		tail = head;
		/* O(1)*/
	}
	
	/**
	 * returns the size.
	 * @return the number of nodes in the list.
	 */
	public int size(){
		
		return size; 
	}
    
	/**
	 * Adds a new node with inputted value to the end of the list.
	 * @param value the data.
	 */
	public void addLast(T value){
		
		if(value == null) {
			throw new IllegalArgumentException("Cannot add null value!");
		}
      
		
		/* adding first node */
		if(size == 0 || head == null){ 
			head = new Node<T>(value);
			tail = head;
			size++;
			return;
		}
      
		/*adding every other node*/
		Node<T> n = new Node<T>(value);
		tail.next = n;
		tail = tail.next;
		size++;
	
	}
	
	/**
	 * Removes the first element from the list.
	 * @return data value of node.
	 */
	public T removeFirst(){
		
		if(size == 0) {
			return null; 
		}
		
		Node<T> n = head;
		head = head.next;
		size--;
		return n.value;
		
	}

	/**
	 * Given a value, remove the first occurrence of that value.
	 * Return true if value removed. 
	 * Return false if value not present.
	 * @param value data of the node to find.
	 * @return boolean value.
	 */
	public boolean remove(T value){
		
		if(head == null){
        	return false;
        }
		
		boolean check = false;
		Node<T> p = null;
		Node<T> cur = head;
      
 
		
			
		while(cur.next != null) {  /* traverses list. */
			
			
			if( cur.value.equals(head.value) && cur.value.equals(value) ) { /* if it's the head node. */
				head = cur.next;
				size--;
				return true;
			}
			if( p != null && cur.value.equals(value)) {  /* if it's the middle nodes. */
				p.next = cur.next;
				size--;
				return true;
			}
			
			
			p  = cur;
			cur = cur.next;
			
		}
		
		if(size == 1 && cur.value.equals(value) ) { /* if the head = tail. */
			head = cur.next;
			tail = head;
			size = 0;
			return true;
		}
		if(cur.value.equals(value)  && p != null) { /* if it's the last node. */
			p  = tail;
			p.next = null;
			size--;
			return true;
		}
		
		return false;

	}
	
	
	/**
	 * Find the node with the specified value and return the value stored from the list.
	 * Return null if value is not present.
	 * @param value data of node to find.
	 * @return a node from the list or null.
	 */
	public T get(T value){
		
		if(value == null) {
			return null;
		}
		if(head == null) {
			return null;
		}
		
		int i = 0;
		Node<T> cur = head;
		
		while(cur.next != null) {     /* Traverses list.*/
			
			if(cur.value.equals(value) ) {
				return cur.value;
			}
			cur = cur.next;
		}
		
		if(size == 1 && cur.value.equals(value) ) {  /* if the head = tail.*/
			return cur.value;
		}
		if(cur.value.equals(value) ) {   /*last node.*/
			return cur.value;
		}

		return null; 
	}


	
	/**
	 * Return a basic iterator of T for SimpleList.
	 * @return an iterator.
	 */
	public Iterator<T> iterator(){

		return new Iterator<>(){
			
			/**
			 * Points to the head.
			 */
			private Node<T> current = head;
		
			/**
			 * Checks if the iteraor has iterated through the entire list.
			 * @return a boolean value.
			 */
			public boolean hasNext(){			
				return (current!=null);
			}
		    
			/**
			 * Returns the next element in the list.
			 * @return a node.
			 */
			public T next(){
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				T toReturn = current.value;
				current = current.next;
				return toReturn;
			}
		};
	}
	
	/**
	 * Prints the contents of the linked list, from head to tail.
	 * @return a string of the whole list.
	 */
	@Override
	public String toString(){
		
		StringBuilder s = new StringBuilder("[");
		Node<T> current = head;
		String prefix="";
		while (current!=null){
			s.append(prefix);
			s.append(current.value);
			prefix=",";
			current = current.next;
		}
		s.append("]");
		return s.toString();

	}
		
}
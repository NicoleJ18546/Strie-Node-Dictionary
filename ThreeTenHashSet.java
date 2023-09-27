// TO DO: add your implementation and JavaDocs.

import java.util.Iterator;
import java.util.Set;

/**
 * Implements a set using a hash table with seperate chaining. 
 * It does this by using an array of linked lists.
 * This is the underlying storage table of the project.
 * @param <T> a data.
 */
class ThreeTenHashSet<T> {
	
	/**
	 * The array of Simple List objects.
	 */
	private SimpleList<T>[] table;
	
	   /**
	 * The length of the array.
	 */
    private int capacity;
    
    /**
     * The current number of elements in the array.
     */
    private int size;
	
	
 /**
     * Create a hash table where the storage is with initLength. 
     * Initially the table is empty. 
     * We can assume initLength is >= 2.
     * @param initLength the intial length of the array.
     */
	@SuppressWarnings("unchecked")
	public ThreeTenHashSet(int initLength){
		
		capacity = initLength;
		size = 0; 
		table = (SimpleList<T>[]) new SimpleList[initLength];
	}
    
	/**
	 * Returns the storage length.
	 * @return the length of the array.
	 */
	public int capacity() {

		return capacity; 
	}
    
	/**
	 * returns the number of items in the array.
	 * @return the number of elements in the table.
	 */
	public int size() {
		
		return size;
	}
	
	/**
	 * Add an item to the set. 
	 * Return true if you successfully add value. 
	 * Return false if the value can not be added.
	 * If load of table is at or above 2.0, rehash() to double the length.
	 * @param value the data.
	 * @return a boolean value.
	 */
	public boolean add(T value) {
		
		if(value == null) {
			return false;
		}
		
		if(contains(value)) {
			return false;
		}
      
		if((double)size/capacity >= 2.0) {  /* check if rehash is needed */
			rehash(2*capacity);
		}
		
		int index = Math.abs(value.hashCode() % capacity); /*calculate the index */
		
        if(table[index] == null){
        	SimpleList<T> list = new SimpleList<T>();
			table[index] = list;
        }
      
		table[index].addLast(value);
		size++;
		return true;
		
	}
    
	/**
	 * Removes a value from the set. 
	 * Return true if you remove the item. 
	 * Return false if the item is not present.
	 * @param value the data.
	 * @return a boolean value.
	 */
	public boolean remove(T value) {
		
		if(value == null) {
			return false;
		}
		
		if(!contains(value)){
			return false;
        }
      
		int index  = Math.abs(value.hashCode() % capacity);   /*calculate the index */
		
        if(table[index] == null){
        	SimpleList<T> list = new SimpleList<T>();
			table[index] = list;
        }
        
		boolean val = table[index].remove(value);
		
		return val;

	}
	
	/**
	 * Checks if the value is already in the table. 
	 * Returns true if present, false otehrwise.
	 * @param value the data.
	 * @return a boolean value.
	 */
	public boolean contains(T value) {
		
		if(value == null) {
			return false;
		}
		
		int index = Math.abs(value.hashCode() % capacity); /*calculate the index */
      
  		if(table[index] != null && table[index].get(value) != null) {
			return true;
		}
  		
		return false; 
	}
	
	
	/**
	 * Return null if value is not present in set. 
	 * Return the item from the hash table, if it was found.
	 * @param value the data.
	 * @return the object the value is pointing to.
	 */
	public T get(T value) {
		
		if(value == null) {
			return null;
		}
		if(!contains(value)) {
			return null;
		}
		
		int index = Math.abs(value.hashCode() % capacity);
		if(table[index].get(value) != null) {
			return table[index].get(value);
		}
		
		return null;
	}
	
	/**
	 * Rehash to table size newCapacity. 
	 * If the new capacity is no greater than the current capacity, do not rehash and return false.
	 * Otherwise, return true after resizing.
	 * @param newCapacity the new length of the array.
	 * @return a boolean value.
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int newCapacity) {
		
		
		if(newCapacity <= this.capacity || newCapacity >= Integer.MAX_VALUE - 50) { /* check range of newCapacity. */
			return false;
		}
		
		SimpleList<T>[] newT = (SimpleList<T>[]) new SimpleList[newCapacity]; /* Make new array. */
		Iterator<T> iter;
		int index = 0;
		
		for(int i = 0; i < capacity; i++) {
			
			iter = table[i].iterator();  /* Creates an iterator for each list in the array. */
			T cur = iter.next();
			index = Math.abs(cur.hashCode() % newCapacity);  /* Calculates the index. */
			
            if(newT[index] == null){
            	SimpleList<T> list = new SimpleList<T>();
    			newT[index] = list;
            }
         
			newT[index].addLast(cur);
			
			while(iter.hasNext()) {
				cur = iter.next();
				index = Math.abs(cur.hashCode() % newCapacity);
				
				if(newT[index] == null){
					SimpleList<T> list = new SimpleList<T>();
		            newT[index] = list;
		        }
				newT[index].addLast(cur);
			}
			
			
		}
		
		table = newT;            
		capacity = newCapacity;  
		
		return true;
				
	}
	

	/**
	 * Returns a string of the contents of the hashtable.
	 * @return a String.
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (non-empty entries):\n");
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				s.append(i);
				s.append(" :");
				s.append(table[i]);
				s.append("\n");
			}
		}
		return s.toString().trim();
	}
	

	/**
	 * Returns a string of the contents of the hash table.
	 * @return a string value.
	 */
	public String toStringDebug() {
		StringBuilder s = new StringBuilder("ThreeTenHashSet (all entries):\n");
		for(int i = 0; i < table.length; i++) {
			s.append(i);
			s.append(" :");
			s.append(table[i]);
			s.append("\n");
		}
		return s.toString().trim();
	}

	
	/**
	 * Return all items in set as a list.
	 * @return a simpleList of T values.
	 */
	public SimpleList<T> allValues(){

		SimpleList<T> all = new SimpleList<>();
		for(int i = 0; i < table.length; i++) {
			if (table[i]!=null){
				for (T value: table[i])
					all.addLast(value);
			}
		}
		return all;
	}

}
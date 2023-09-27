
/**
 * Uses a HashMap to hold children nodes. 
 * Keys of the map can be any Character while values are the children nodes.
 * Each key in the map leads to a child node of this node.
 */
public class StrieNode{

	
	/**
	 * The hashmap of pair objects.
	 */
	private HashMap<Character, StrieNode> children; 

	/**
	 * Marks the end of a word.
	 */
	private boolean endMarker;  
	
	// OPTIONAL boolean flag that you can use.
	// It is completely optional to use this in your implementation.
	// We will NOT test its usage but it is provided for more flexibility.
	// Still, remember to write JavaDoc for it.
	
	/**
	 * Signifies the end of a chain.
	 */
	private boolean flag;  	
	
	/**
	 * Default length of the hashmap to start.
	 */
	private static final int INIT_MAP_LENGTH = 5; 

	/**
	 * Constructor. Initializes hashmap with intial map length.
	 */
	public StrieNode(){
		
		children = new HashMap<Character, StrieNode>(INIT_MAP_LENGTH);
	
	}
    
	/**
	 * Report number of children nodes.
	 * @return an int.
	 */
	public int getNumChildren(){
		
		return children.size(); 	
		
	}
    
	/**
	 * Return the storage of all children.
	 * @return a hashmap of pair objects.
	 */
	public HashMap<Character, StrieNode> getAllChildren(){

		return children;	
	}

	/**
	 * Sets the end marker to indicate this node is the end of a string/word.
	 */
	public void setEnd(){

		this.endMarker = true;
	}
	
	/**
	 * Unsets the end marker.
	 */
	public void unsetEnd(){

		this.endMarker = false;
	}
	
	/**
	 * Checks whether the current node is marked as the end of a string/word.
	 * @return a boolean value.
	 */
	public boolean isEnd(){

		return this.endMarker;
	}
	
	/**
	 * Returns true if node has a child node corresponding to ch. Else, returns false. 
	 * @param ch a character.
	 * @return a boolean value.
	 */
	public boolean containsChild(char ch){
		
		return children.contains(ch);
	}

	/**
	 * Returns the child node corresponding to ch, Otherwise, returns null if no such node.
	 * @param ch a character.
	 * @return a StrieNode.
	 */
	public StrieNode getChild(char ch){
		
		return children.getValue(ch); 
	}
    
	/**
	 * Set a child node corresponding to ch to node.
	 * If a node already exists, change the mapping of ch to the specified node.
	 * @param ch a character.
	 * @param node a StrieNode.
	 */
	public void putChild(char ch, StrieNode node){
	
		if(children.contains(ch)){
			
			children.update(ch, node);
		}
		
		else {
		
			children.add(ch, node);
		}
		
	}
	
	/**
	 * Remove child node corresponding to ch if a node is present. 
	 * Return true if a child was removed. 
	 * If no such child node, return false
	 * @param ch a Character.
	 * @return a boolean value.
	 */
	public boolean removeChild(char ch){

		return children.remove(ch); 
	}

	/**
	 * Set the optional flag to be true.
	 */
	public void setFlag(){

		flag = true;
	}
    
 /** 
	 * Set the optional flag to be false.
	 */
	public void unSetFlag(){

		flag = false;
	}
	
	/**
	 * Report the status of the optional flag.
	 * @return a boolean value.
	 */
	public boolean checkFlag(){

		return flag;	
	}

}


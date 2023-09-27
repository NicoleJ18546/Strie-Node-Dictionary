
/**
 * This class implements the strie data strcutrue. 
 * It supports basic strie operation such as inserting a word.
 * Removing a word, searching for a word, and more. 
 * It must use StrieNode objects for storing a node.
 */

public class Strie{
    
	/**
	 * The root of the strie.
	 */
	private StrieNode root;  
	
	/**
	 * The number of words represetned by the strie.
	 */
	private int numWords = 0; 
	
	/**
	 * Constructor. 
	 * Initialize root to be an empty node. Initially no words are in the strie.
	 */
	public Strie(){

		root = new StrieNode();

	}
	
	/**
	 * Returns number of words in the strie.
	 * @return an int.
	 */
	public int numWords(){
		
		return numWords; 
	}
	
	/**
	 * Returns the root of the strie.
	 * @return a StrieNode obj.
	 */
	public StrieNode getRoot(){
		
		return root; 
	
	}
    
	/**
	 * Insert word into the Strie.
	 * @param word a String.
	 */
	public void insert(String word){
		
		if(this.contains(word) || word == null) {
			return;
		}
		
		StrieNode cur = root;
			
		for( int i = 0; i< word.length(); i++){ /* traverses the characters of word. */
			
			/* if the character isn't in the Strie.*/
			if(!cur.containsChild(word.charAt(i)) ) {
					
				if(cur.checkFlag()) { /*Check if it's the end of chain.*/
					cur.unSetFlag();
				}
				
            
				cur.putChild(word.charAt(i),new StrieNode()); /* Adds the character into the strie. */
			}
		    
              
		    cur = cur.getChild(word.charAt(i)); /* goes to next StrieNode in the chain. */
		    	
		    
		    if(i == word.length()-1) { /* Last character of word.  */
					
		    	cur.setEnd();
					
		  /* If it's the end of the chain, set the flag. */
				if(cur.getNumChildren() == 0) {
			    	cur.setFlag();
			    }
			}
		}
		
	    numWords++;
      
	}


	/**
	 * Returns true if Strie contains the given word. Otherwise returns false.
	 * @param word a String.
	 * @return a boolean value.
	 */
	public boolean contains(String word){
		
		if(word == null || numWords == 0) {
			return false;
		}
		
		StrieNode cur = root;
		
		for( int i = 0; i< word.length(); i++){  /* Traverses each character of word. */
			if(!cur.containsChild(word.charAt(i)) || cur == null) {
				return false;
	    	}
			
			cur = cur.getChild(word.charAt(i));     /*goes to next letter.*/
			
			/*if its the last letter, need to check if it's a registered word, or just part of a word.*/
			if(cur != null && i == word.length()-1) {
			
				return cur.isEnd();
			}
	    	
	    }
	
		return false;	
	}
    
	/**
	 * Removes the given word from Strie. 
	 * If word is not present in strie, return false. 
	 * Otherwise, remove word and return true.
	 * @param word a String.
	 * @return a boolean value.
	 */
	public boolean remove(String word){
		
		if(!contains(word)) {
			return false;
		}
		
		
		
		int check = -1;
		StrieNode n = root;
		StrieNode cur = root;
			
		for( int i = 0; i< word.length(); i++){  /* Traverses each character of word.*/
			
			
			if(cur.containsChild(word.charAt(i)) ) {
				
				cur = cur.getChild(word.charAt(i)); 
				
				/* If there's a smaller word inside of this word, or a branch. */
				if(i != word.length() - 1 && (cur.isEnd() || cur.getNumChildren() > 1) ) { 
					
					check = i;
					n = cur;
				}
				
				if(i == word.length() - 1) {  /*At the last character of word.*/
					
					if(cur.checkFlag()) { /* Is the last node of the chain.*/
						if(check == -1) { 
					
							n = root;
							check = -2;
						}
					}
					else {  /*If the word isn't the end of the chain, then we cannot delete it.*/
						cur.unsetEnd();
						numWords--;
						return true;
					}

				}
			}
		        
		    
		   
		} /*End of for-loop. */
		
		
		if(check == -2 && n != null) {
         
			n.removeChild(word.charAt(0));
			numWords--;
			return true;
		}
		if(check >= 0 && n != null) {
			
			n.removeChild(word.charAt(check+1));
			if(n.getNumChildren() == 0) {
				n.setFlag();
			}
			numWords--;
			return true;
		}
		
		return false;
	
	}	
			
 /**
     * Performs a Breadth First Traversal of the Strie tree.
     * Returns a string of all characters encountered in the traversal.
     * If a Strie has no words, return an empty string.
     * A single space should be padded between characters.
     * @return a String value.
     */
	public String levelOrderTraversal(){
		
		if(numWords == 0 || root == null) {
			return "";
		}
		
		StrieNode cur = root;
		StringBuilder s = new StringBuilder("");
		SimpleList<StrieNode> q = new SimpleList<StrieNode>(); /*This is the queue.*/
		
		int n = 0;

		
		q.addLast(cur); /* Add root. */
		    
		do{                  /*Iterates through each level of the tree.  */                                   
			n = q.size();
		 
		        
		    do{             /* Goes through the nodes in the level. */
		        	
		        	
		        StrieNode p = q.removeFirst();
		        SimpleList<Character> list = p.getAllChildren().getKeys();
		        Character c = '\0';
		        int size = list.size();
		        	
		        for (int i = 0; i < size; i++) {  /* Goes through children of node. */
		            	
		            c = list.removeFirst();
		            	
		            if(c != '\0') {
		            		
		            	s.append(c + " ");
		            	q.addLast(p.getChild(c));
		            		
		            }
		        }
		            
		        n--;
		            
		    }while (n > 0);   
		}while (q.size() > 0); /* If there's anything left to go through. */
		     
		return s.substring(0,s.length()-1).toString(); /* Get rid of extra space at the end. */	
	}

    
	/**
	 * Returns a list of all the words found in the tree.
	 * @return a SimpleList of Strings.
	 */
	public SimpleList<String> getStrieWords(){
		

		if(numWords == 0 || root == null) {
			return null;
		}
		
		String s = "";
		SimpleList<String> strList = new SimpleList<String>();
		
		getStrieWordsHelper(' ',strList, s, root); /* Recursive method. */
		
		return strList;
	}
	
	
	   /**
	 * A recursive method that searchs for possible words and adding it to a SimpleList of Strings.
	 * @param ch a character.
	 * @param strList the string list.
	 * @param s a string.
	 * @param c a StrieNode.
	 */
    private void getStrieWordsHelper(Character ch, SimpleList<String> strList, String s, StrieNode c) { //preorder traversal: C L R
    	  
    	if(c == null) { /*stopping point.*/
    		return;
    	}
        if(c != root) { /*The root doesn't have a character.*/
        	s = s + ch;
        }
        if(c.isEnd()) { /* The end of a word, add it to list. */
        	strList.addLast(s);
        }
          
        if(!c.checkFlag()) { /* if it's not the end of the chain. */
        	  
        	SimpleList<Character> charL = c.getAllChildren().getKeys();
        	  
        	  
        	while(charL.size() > 0) {    /*adds from children from L to R. */
        		ch = charL.removeFirst();
        		getStrieWordsHelper(ch,strList,s, c.getChild(ch));
        	}
        }
    	
    }
	
}

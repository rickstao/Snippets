/**
 *Ruikang Tao
 *rtao6
 *Programming Assignment 3
 */

class List{
	private class Node{
		// inner class fields
		Object data;
		Node prev;
		Node next;
		
		// inner class constructor
		Node(Object data){
			this.data = data;
			prev = null;
			next = null;
		}
		
		// toString()
		public String toString(){
			return String.valueOf(data);
		}
	}

	// private fields
	private Node front;
	private Node back;
	private Node cursor;
	private int length;
	private int cursorIndex;
	
	// Constructor
	public List(){
		front = back = null;
		cursor = null;
		length = 0;
		cursorIndex = -1;
	}
	

	// Access functions:
	
	// length()
	// returns length of List
	int length(){
		return length;
	}
	
	// index()
	// If cursor is defined, returns index of the cursor element
	// otherwise return -1
	int index(){
		return cursorIndex;
	}
	
	// front()
	// Returns front element
	// Pre: length()>0
	Object front(){
		if(length <= 0){
			throw new RuntimeException("front() called on an Empty List.");
		}
		return front.data;
	}
	
	// back()
	// Returns back element 
	// Pre: length()>0
	Object back(){
		if(length <= 0){
			throw new RuntimeException("back() called on an Empty List.");
		}
		return back.data;
	}
	
	// get()
	// Returns cursor element
	// Pre: length()>0, index()>=0
	Object get(){
		if(length <= 0){
			throw new RuntimeException("get() called on an Empty List.");
		}
		if(this.index() <= -1){
			throw new RuntimeException("get() called on undefined cursor index.");
		}
		return cursor.data;
	}
	
	// equals()
	// Returns true if this List and L are the same int seq.
	public boolean equals(Object ha){

		List that = null;
		if(ha instanceof List){
			that = (List)ha;
			if(this.length == that.length){
				Node tmp1 = this.front;
				Node tmp2 = that.front;
				while(tmp1 != null && tmp2 != null){
					if(tmp1.data.equals(tmp2.data)){
						tmp1=tmp1.next;
						tmp2=tmp2.next;
					} else{
						return false;
					}
				}
				return true;
			} else{
				return false;
			}
		} else{
			return false;
		}
	}
	

	// Manipulation procedures:
	
	// clear()
	// Resets this List to its original empty state
	void clear(){
		Node front = null;
		Node back = null;
		cursor = null;
		length = 0;
		cursorIndex = -1;
	}
	
	// moveFront()
	// Places cursor under front element
	// Pre: length != 0
	void moveFront(){
		if(length() > 0){
			cursor = this.front;
			cursorIndex = 0;
		}
	}
	
	// moveBack()
	// Places cursor under back element
	// Pre: length != 0
	void moveBack(){
		if(length() > 0){
			cursor = this.back;
			cursorIndex = length-1;
		}
	}
	
	// movePrev()
	// Moves cursor one step toward front of list
	// cursor must be defined, does nothing otherwise
	// cursor falls off list when called on front
	void movePrev(){
		if(this.index() != -1 && this.index() > 0){
			cursor = cursor.prev;
			cursorIndex--;
		}else if(this.index() != -1 && this.index() == 0){
			cursor = null;
			cursorIndex = -1;
		}
	}
	
	// moveNext()
	// Moves cursor one step toward back of list
	// cursor must be defined, does nothing otherwise 
	// cursor falls of list when called on back
	void moveNext(){
		if(this.index() != -1 && cursorIndex < this.length()-1){
			cursor = cursor.next;
			cursorIndex++;
		}else if(this.index() != -1 && this.index() == this.length()-1){
			cursor = null;
			cursorIndex = -1;
		}
	}
	
	// prepend()
	// Insert new element into list before front element
	void prepend(Object data){
		Node N = new Node(data);
		if(length < 0){
			throw new RuntimeException("prepend() called on an Empty List.");
		}else if(length == 0){
			front = back = N;
			length ++;
		}else{
			front.prev = N;
			N.next = front;
			N.prev = null;
			front = N;
			length ++;
		}
		if(index() != -1){
			cursorIndex ++;
		}
	}
	
	// append()
	// Inserts new element into list after back element
	void append(Object data){
		Node N = new Node(data);
		// if List is empty 
		if(length == 0){
			front = back = N;
		}else{
			// if List non-empty, insert after back element
			N.prev = back;
			N.next = null;
			back.next = N;
			back = N;
		}
		length++;
	}
	
	// insertBefore()
	// Insert new element before cursor
	// Pre: length()>0, index()>=0
	void insertBefore(Object data){

		if (length() <= 0) {
			throw new RuntimeException ("Error: insertBefore() called on an Empty List.");
		} else if (index() < 0) {
			throw new RuntimeException ("Error: Cursor Undefined");
		}
		Node newNode = new Node (data);
		if (index() == 0) {
			prepend(data);
		} else {
			newNode.next = cursor;
			newNode.prev = cursor.prev;
			cursor.prev.next = newNode;
			cursor.prev = newNode;
			cursorIndex++;
			length++;
		}
	}
	
	// insertAfter()
	// Insert new element after cursor
	// Pre: length()>0, index()>=0
	void insertAfter(Object data){

		if (length() <= 0) {
			throw new RuntimeException ("Error: Empty List.");
		} else if (index() < 0) {
			throw new RuntimeException ("Error: Cursor Undefined.");
		}
		Node newNode = new Node (data);
		if (index() == length()-1) {
			append(data);
		} else {
			newNode.next  = cursor.next;
			newNode.prev = cursor;
			cursor.next = newNode;
			cursorIndex++;
			length++;
		}
	}
	
	// deleteFront()
	// Deletes the front element
	// Pre: length()>0
	void deleteFront(){
		if(length <= 0){
			throw new RuntimeException("Error: Empty List.");
		}
		if(this.length > 1){
			front = front.next;
			front.prev = null;
			cursorIndex--;
		}else{
			front = back = null;
			cursorIndex = -1;
		}
		length--;
	}
	
	// deleteBack()
	// Deletes the back element
	// Pre: length()>0
	void deleteBack(){
		if(length <= 0){
			throw new RuntimeException("Error: Empty List.");
		}
		if(this.length > 1 && this.index() != length-1){
			Node temp = back.prev;
			temp.next = null;
			back = temp;
		}else if(this.length() == 1){
			front = back = null;
			cursorIndex = -1;
		}else if(this.length > 1 && this.index() == length-1){
			back = back.prev;
			back.next = null;
			cursorIndex = -1;
		}
		length--;
	}
	
	// delete()
	// Deletes the cursor element, making cursor undef
	// Pre: length()>0, index()>=0
	void delete(){
		if (index() == -1) {
			throw new RuntimeException ("Error: delete() called on list with an undefined cursor.");
		}
		Node temp = cursor;
		if (cursor == back) {
			deleteBack();
		} else if (cursor == front) {
			deleteFront();
		} else{
			cursor.prev.next = cursor.next;
			cursor.next.prev = cursor.prev;
			cursor = null;
			temp = null;
			cursorIndex = -1;
			length--;
		}
	}
	
	
	// Other methods:
	
	// toString()
	// Overrides Object's toString method
	// Returns a String representation of this List
	public String toString(){
		Node N = front;
		String str = "";
		while(N != null){
			str += N.toString();
			N = N.next;
		}
		return str;
	}
}
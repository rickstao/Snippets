// Ruikang Tao
// rtao6
// PA2

#include <stdio.h>
#include <stdlib.h>
#include "List.h"

//structs
typedef struct NodeObj{
	int data;
	struct NodeObj* next;
	struct NodeObj* prev;
} NodeObj;

//Node type
typedef NodeObj* Node;

//ListObj type
typedef struct ListObj{
	Node front;
	Node back;
	Node cursor;
	int length;
	int curIndex;
} ListObj;

// Constructors-Destructors ---------------------------------------------------

// newNode()
// Returns reference to new Node object.
// Private.
Node newNode(int data){
	Node N = malloc(sizeof(NodeObj));
	N->data = data;
	N->next = NULL;
	N->prev = NULL;
	return(N);
}

// freeNode()
// Frees heap memory pointed to by *fN, sets *fN to NULL.
// Private.
void freeNode(Node* fN){
	if(fN != NULL && *fN != NULL){
		free(*fN);
		*fN = NULL;
	}
}

// newList()
// Returns reference to new empty List object
List newList(void){
	List L;
	L = malloc(sizeof(ListObj));
	L->front = NULL;
	L->back = NULL;
	L->length = 0;
	L->curIndex = -1;
	L->cursor = NULL;
	return L;
}

// freeList()
// Frees all heap memory associated with List *fL, and sets *fL to NULL
void freeList(List* fL){
	if(fL == NULL || *fL == NULL){
		return;
	}
	while(length(*fL) > 0){
		deleteBack(*fL);
	}
	free(*fL);
	*fL = NULL;
}

// Access functions -----------------------------------------------------------

// length()
// Returns the number of elements in this List.
int length(List L){
	if(L == NULL){
		printf("List Error: NULL List reference.\n");
		exit (1);
	}
	return L->length;
}

// getIndex()
// Returns the number of elements in this List.
// otherwise returns -1.
int index(List L){
	if(L == NULL){
		printf("List Error: NULL List reference.\n");
		exit (1);
	}
	return L->curIndex;
}

// front()
// Returns front element. Pre: length()>0
int front(List L){
	if(L == NULL){
		printf("List Error: NULL List reference.\n");
		exit (1);
	}
	if(L->length == 0) {
		printf("List Error: Empty List.\n");
		exit (1);		
	} else{
		return L->front->data;
	}
}

// back()
// Returns back element. Pre: length()>0
int back(List L){
	if(L == NULL){
		printf("List Error: NULL List reference.\n");
		exit (1);
	} else if(L->length == 0){
		printf("List Error: Empty List.\n");
		exit (1);		
	} else{
		return L->back->data;
	}
}

// get()
// Returns cursor element. Pre: length()>0, index()>=0
int get(List L){
	if(L == NULL){
		printf("List Error: NULL List reference.\n");
		exit (1);
	} else if(L->cursor == NULL){
		printf("List Error: Cursor undefined.\n");
		exit (1);		
	} else{
		return (L->cursor->data);
	}
}

// equals()
// Returns true if and only if this List and L are the same
// integer sequence. The states of the cursors in the two Lists
// are not used in determining equality.
int equals(List A, List B){
	int boo = 1;
	if(A == NULL || B == NULL){
		printf("List Error: calling equals() on NULL list reference.\n");
		exit(1);
	}
	if(A->length != B->length){
		return 0;
	}
	Node a = NULL;
	Node b = NULL;
	a = A->front;
	b = B->front;
	while(boo && a != NULL){
		boo = (a->data == b->data);
		a = a->next;
		b = b->next;
	}
	return boo;
}


// Manipulation procedures ----------------------------------------------------

// clear()
// Resets this List to its original empty state.
void clear(List L){
	Node p = L->front;
	while(p != NULL){
		Node l = p;
		p = p->next;
		freeNode(&l);
	}
	L->front = L->back = NULL;
	L->cursor = NULL;
	L->curIndex = -1;
	L->length = 0;
}

// moveFront()
// If List is non-empty, places the cursor under the front element,
// otherwise does nothing.
void moveFront(List L){
	if(L == NULL) {
		printf("List error: calling moveFront() on NULL List reference.\n");
        exit(1);
	}
	if(L->length > 0){
		L->cursor = L->front;
		L->curIndex = 0;
	}
}

// moveBack()
// If List is non-empty, places the cursor under the back element,
// otherwise does nothing.
void moveBack(List L){
	if(L == NULL) {
		printf("List error: calling moveBack() on NULL List reference.\n");
        exit(1);
	}
	if(L->length > 0){
		L->cursor = L->back;
		L->curIndex = L->length - 1;
	}
}

// movePrev()
// If cursor is defined and not at front, moves cursor one step toward
// front of this List, if cursor is defined and at front, cursor becomes
// undefined, if cursor is undefined does nothing.
void movePrev(List L){
	if (L == NULL){
		printf("List Error: calling movePrev() on NULL List reference.\n");
		exit (1);
	} else if(L->cursor == L->front || L->curIndex == -1){
		L->cursor = NULL;
		L->curIndex = -1;
	} else{
		L->cursor = L->cursor->prev;
		L->curIndex--;
	}
}

// moveNext()
// If cursor is defined and not at back, moves cursor one step toward
// back of this List, if cursor is defined and at back, cursor becomes
// undefined, if cursor is undefined does nothing.
void moveNext(List L){
	if (L == NULL){
		printf("List Error: calling moveNext() on NULL List reference.\n");
		exit (1);
	} else if(L->cursor == L->back || L->curIndex == -1){
		L->cursor = NULL;
		L->curIndex = -1;
	} else{
		L->cursor = L->cursor->next;
		L->curIndex++;
	}
}

// prepend()
// Insert new element into this List. If List is non-empty,
// insertion takes place before front element.
void prepend(List L, int data){
	Node n = newNode(data);
	if(L == NULL){
		printf("List Error: calling prepend() on NULL List reference.\n");
		exit (1);
	}
	if(L->length == 0){
		L->front = L->back = n;
	} else{
		L->front->prev = n;
		n->next = L->front;
		L->front = n;
	}
	L->length++;
	if(L->curIndex >= 0){
		L->curIndex++;
	}
}

// append()
// Insert new element into this List. If List is non-empty,
// insertion takes place after back element.
void append(List L, int data){
	Node n = newNode(data);
	if(L == NULL){
		printf("List Error: calling append() on NULL List reference.\n");
		exit (1);
	}
	if(L->length == 0){
		L->front = L->back = n;
	} else{
		L->back->next = n;
		n->prev = L->back;
		L->back = n;
	}
	L->length++;
	/*
	if(L->curIndex >= 0){
		L->curIndex++;
	}
	*/
}

// insertBefore()
// Insert new element before cursor. 
// Pre: length()>0, index()>=0
void insertBefore(List L, int data){
	Node n = newNode(data);
	if(L == NULL){
		printf("List Error: calling insertBefore() on NULL List reference.\n");
		exit (1);
	}
	if(L->length <= 0){
		printf("List Error: calling insertBefore() on an empty list.\n");
		exit (1);
	}
	if(L->cursor == NULL || L->curIndex < 0){
		printf("List Error: calling insertBefore() on undefined cursor.\n");
		exit (1);
	}
	if(L->cursor == L->front || L->curIndex == 0){
		prepend(L,data);
	} 
	else{
		n->next = L->cursor;
		n->prev = L->cursor->prev;
		L->cursor->prev->next = n;
		L->cursor->prev = n;
		L->length++;
		L->curIndex++;
	}
}

// insertAfter()
// Inserts new element after cursor.
// Pre: length()>0, index()>=0
void insertAfter(List L, int data){
	Node n = newNode(data);
	if(L == NULL){
		printf("List Error: calling insertBefore() on NULL List reference.\n");
		exit (1);
	}
	if(L->length <= 0){
		printf("List Error: calling insertBefore() on an empty list.\n");
		exit (1);
	}
	if(L->cursor == NULL || L->curIndex < 0){
		printf("List Error: calling insertBefore() on undefined cursor.\n");
		exit (1);
	}
	if(L->cursor == L->back || L->curIndex == length(L) - 1){
		append(L,data);
	}
	else{
		n->next = L->cursor->next;
		n->prev = L->cursor;
		L->cursor->next->prev = n;
		L->cursor->next = n;
		L->length++;
	}
}

// deleteFront()
// Deletes the front element. Pre: length()>0
void deleteFront(List L){
	if(L == NULL){
		printf("List Error: calling deleteFront() on NULL List reference.\n");
		exit (1);
	}
	if(L->length <= 0){
		printf("List Error: calling deleteFront() on an empty list.\n");
		exit (1);
	}
	Node n = L->front;
	if(L->length == 1){
		L->front = NULL;
	}
	else{
		Node temp = L->front->next;
		temp->prev = NULL;
		L->front = temp;
	}
	
	if(L->curIndex != -1){
		L->curIndex--;
	}
	
	L->length--;
	freeNode(&n);
}

// deleteBack()
// Deletes the back element. Pre: length()>0
void deleteBack(List L){
	if(L == NULL){
		printf("List Error: calling deleteBack() on NULL List reference.\n");
		exit (1);
	}
	if(L->length <= 0){
		printf("List Error: calling deleteBack() on an empty list.\n");
		exit (1);
	}
	Node n = L->back;
	if(L->length == 1){
		L->back = L->front = NULL;
		L->curIndex = -1;
	} else if(L->length >1 && L->curIndex == L->length - 1){
		L->back = L->back->prev;
		L->back->next = NULL;
		L->curIndex = -1;
	} else{
		Node temp = L->back->prev;
		temp->next = NULL;
		L->back = temp;
	}

	/*
	if(L->curIndex != -1){
		L->curIndex--;
	}
	*/
	
	L->length--;
	freeNode(&n);
}

// delete()
// Deletes cursor element, making cursor undefined.
// Pre: length()>0, index()>=0
void delete(List L){
	if(L == NULL){
		printf("List Error: calling delete() on NULL List reference.\n");
		exit (1);
	}
	if(L->length <= 0){
		printf("List Error: calling delete() on an empty list.\n");
		exit (1);
	}
	if (L->curIndex < 0){
		printf("List Error: undefined cursor.\n");
	}
	Node n = L->cursor;
	if (n == L->front){
		deleteFront(L);
	} else if(n == L->back){
		deleteBack(L);
	} else{
		Node before = L->cursor->prev;
		Node after = L->cursor->next;
		before->next = after;
		after->prev = before;
		L->curIndex = -1;
		L->length--;
		freeNode(&n);
	}
	L->cursor = NULL;
	L->curIndex = -1;
}

// printList()
// prints the elements in the list
void printList(FILE* out, List L){
	if(L == NULL){
		printf("List Error: calling printList() on NULL List reference.\n");
		exit (1);
	}
	Node p = NULL;
	for(p = L->front; p != NULL; p = p->next){
		fprintf(out, "%d ", p->data);
	}
}

// copyList()
// copies the element into a new list
List copyList(List L){
	if(L == NULL){
		printf("List Error: calling copyList() on NULL List reference.\n");
		exit (1);
	}
	List xin = newList();
	Node temp = L->front;
	while(temp != NULL){
		append(xin, temp->data);
		temp = temp->next;
	}
	xin->curIndex = -1;
	xin->cursor = NULL;
	L->curIndex = 0;
	return xin;
}
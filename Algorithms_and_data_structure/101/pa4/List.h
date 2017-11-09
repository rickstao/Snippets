// Ruikang Tao
// rtao6
// PA4

#ifndef _LIST_H_INCLUDE_
#define _LIST_H_INCLUDE_

#define NIL 0
//List Type
typedef struct  ListObj* List;
typedef struct NodeObj* Node;
//Constructors and Destructors

//newNode()
//Returns reference to new Node object.
Node newNode(int data);

//freeNode()
//Frees heap memory pointed to by *fN, sets *fN to NULL.
void freeNode(Node* fN);

//newList()
//Returns reference to new empty List object
List newList(void);

//freeList()
//Frees all heap memory associated with List *pL
void freeList(List* pL);

//Access Functions

//length()
//Returns the length of L
int length(List L);

//index()
//Returns the index of the cursor
int index(List L);

//front()
//Returns the front element
int front(List L);

//back()
//Returns the back element
int back(List L);

//get()
//Returns the cursor element
int get(List L);

//equals()
//Returns 1 if A and B are equal, else returns 0
int equals(List A, List B);

//Manipulation Procedures:

//clear()
//Resets this List to its original empty state.
void clear(List L);

//movePrev()
//Moves the cursor to the previous index
void movePrev(List L);

//moveNext()
//Moves the cursor to the next index
void moveNext(List L);

//prepend()
//Inserts data to the beginning of the list
void prepend(List L, int data);

//append()
//Inserts data the the end of the list
void append(List L, int data);

//insertBefore()
//Inserts data before the cursor
void insertBefore(List L, int data);

//insertAfter()
//Inserts data After the cursor
void insertAfter(List L, int data);

//deleteFront()
//Removes the element at the front of the list
void deleteFront(List L);

//deleteBack()
//Removes the element at the back of the list
void deleteBack(List L);

// moveFront()
// If List is non-empty, places the cursor under the front element,
// otherwise does nothing.
void moveFront(List L);

// moveBack()
// If List is non-empty, places the cursor under the back element,
// otherwise does nothing.
void moveBack(List L);

//delete()
//Deletes cursor element, making cursor undefined.
void delete(List L);

//printList()
//Prints each element of the list to the output file
void printList(FILE* out, List L);

//copyList()
//Returns a new list which is a copy of the list 
List copyList(List L);

#endif
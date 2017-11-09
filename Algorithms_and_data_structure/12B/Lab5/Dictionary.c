//-----------------------------------------------------------------------------
//   Dictionary.c
//   Implementation file for IntegerDictionary ADT
//-----------------------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"IntegerLinkedList.h"
#include"Dictionary.h"


// DictionaryObj
typedef struct DictionaryObj{
   LinkedList *L;
} DictionaryObj;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   D->L = malloc(sizeof(LinkedList));
   assert(D!=NULL);
   *(D->L) = newLinkedList();
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   freeLinkedList((*pD)->L);
   free((*pD)->L);
   free(*pD);
   *pD = NULL;
}

// insert node
void DictInsert(int num, Dictionary D)
{
	insert(num,*(D->L));
}

// find node
int DictFind(int number, Dictionary D){
	if(find(number, *(D->L))==NULL){
		return 0;
	}
	return 1;
}

// delete node
void DictDelete(int n, Dictionary D)
{
	delete(n, *(D->L));
}

// printDictionary()
// prints a text representation of S to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
   if( D->L==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   printLinkedList(out, *(D->L));
}
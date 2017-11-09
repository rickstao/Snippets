//-----------------------------------------------------------------------------
// IntegerDictionary.h
// Header file for the IntegerDictionary ADT
//-----------------------------------------------------------------------------

#ifndef _INTEGER_DICTIONARY_H_INCLUDE_
#define _INTEGER_DICTIONARY_H_INCLUDE_

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"IntegerLinkedList.h"

// Dictionary
// Exported reference type
typedef struct DictionaryObj* Dictionary;

	
// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void);

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD);


// printDictionary()
// prints a text representation of S to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D);

// insert()
// insert number into linked list
// pre: none
void DictInsert(int number, Dictionary D);

// find()
// find pointer to node containing number (read next code snippet for details), return //      null if none exists
// pre: none
int DictFind(int number, Dictionary D);

// delete()
// delete number from linked list
// pre: none
void DictDelete(int n, Dictionary D);

#endif
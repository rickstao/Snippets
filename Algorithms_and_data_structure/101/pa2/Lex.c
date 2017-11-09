// Ruikang Tao
// rtao6
// PA2

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"
#define MAX_LEN 160
typedef char *string;

// insertionSort()
// sorting algorithm that sorts the array
List insertionSort(char** Arr, int len){
	List newL = newList();
	if(len > 0){
		append(newL,0);
	}
	int i;
	for(i = 1; i<len; i++){
		char *temp = Arr[i];
		int j = i-1;
		moveFront(newL);
		while(index(newL) != j){
			moveNext(newL);
		}
		while(j >= 0 && strcmp(temp, Arr[get(newL)]) < 0){
			j--;
			movePrev(newL);
		}
		if(index(newL) == -1){
			prepend(newL, i);
		} else{
			insertAfter(newL, i);
		}
	}
	return newL;
}

// main program------------------------------------------------------
int main(int argc, char* argv[]){
	// delcare variables
	FILE *in, *out, *inAgain;
	char line[MAX_LEN];
	int count = 0;
	int haha = 0;
	int k;

	// open files to read and write
	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");
	// check valid input
	if(argc != 3){
		printf("Invalid number of input files.\n");
		exit(1);
	}
	if(in == NULL){
		printf("Invalid reading file.\n");
		exit(1);
	}
	if(out == NULL){
		printf("Invalid writing file.\n");
		exit(1);
	}

	// count the lines in input
	while(fgets(line, MAX_LEN, in) != NULL){
		count++;
	}
	fclose(in);

	//open again for reading
	inAgain = fopen(argv[1], "r");
	char **words = calloc(count, sizeof(char**));
	while(fgets(line, MAX_LEN, inAgain) != NULL){
		words[haha] = malloc(sizeof(char)*(strlen(line)+1));
		strcpy(words[haha],line);
		haha++;
	}

	// create a new sorted array
	List xin = insertionSort(words, count);

	// move the cursor to the beginning
	// for printing
	moveFront(xin);
    while(index(xin) >= 0) {
        fprintf(out, "%s", words[get(xin)]);
        moveNext(xin);
    }

    // free the memory
    for (k=0; k< count; k++) {
		free(words[k]);
	}
	free(words);
	freeList(&xin);

	// close files
    fclose(inAgain);
    fclose(out);
    
    return(0);
}